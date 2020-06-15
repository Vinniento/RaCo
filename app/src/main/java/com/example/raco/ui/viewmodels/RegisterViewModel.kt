package com.example.raco.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.raco.data.api.user.UserRepo
import com.example.raco.models.DefaultResponse
import com.example.raco.utilities.HelperClass
import kotlinx.coroutines.*
import timber.log.Timber

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val _authRepository = UserRepo
    private lateinit var _resultList: DefaultResponse
    private val _registrationJob = Job()

    private val _snackbarMessageObserver = MutableLiveData<String>()
    val snackbarMessageObserver: LiveData<String>
        get() = _snackbarMessageObserver

    private val _errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable.message.toString())
    }

    fun createAccount(
        firstname: String,
        lastname: String,
        email: String,
        firstPassword: String,
        secondPassword: String
    ) {
        if (checkCredentialsValidity(
                email,
                firstPassword,
                secondPassword
            ) && firstname.isNotBlank() && lastname.isNotBlank()
        ) {
            //TODO register with User(firstname, lastname....) - gute Option?

            val coroutineScope = CoroutineScope(_registrationJob + Dispatchers.Main)
            coroutineScope.launch(_errorHandler) {
                _resultList = _authRepository.register(firstname, lastname, email, firstPassword)

                _snackbarMessageObserver.value = _resultList.success

                Timber.i("Registration " + _resultList.success)
            }
        } else {
            _snackbarMessageObserver.setValue("Wrong credentials. Maybe passwords don't match")
        }
    }

    private fun checkCredentialsValidity(email: String, pass1: String, pass2: String): Boolean {
        return pass1 == pass2 && HelperClass.isValidEmail(email)
    }
}