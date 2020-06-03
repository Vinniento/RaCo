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

    private val authRepository = UserRepo
    private lateinit var resultList: DefaultResponse

    //live and mutable data
    private val _firstname = MutableLiveData<String>()
    val firstname: LiveData<String>
        get() = _firstname
    private val _lastname = MutableLiveData<String>()
    val lastname: LiveData<String>
        get() = _lastname
    private val _inputEmail = MutableLiveData<String>()
    val inputEmail: LiveData<String>
        get() = _inputEmail

    private val _passwordFirst = MutableLiveData<String>()
    val inputPassword: LiveData<String>
        get() = _passwordFirst

    private val _toastMesageObserver = MutableLiveData<String>()
    val toastMessageObserver: LiveData<String>
        get() = _toastMesageObserver

    private val _passwordSecond = MutableLiveData<String>()
    val inputPasswordSecond: LiveData<String>
        get() = _passwordSecond


    private val _errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable.message.toString())
    }

    //TODO livedata so einbauen, dass man nicht die parameter braucht zum übergeben im RegisterFragment??
    fun createAccount(
        firstname: String,
        lastname: String,
        email: String,
        firstPassword: String,
        secondPassword: String
    ) {
        if (checkCredentialsValidity(email, firstPassword, secondPassword)) {
            //TODO register with User(firstname, lastname....) - gute Option?
            val registrationJob = Job()

            val coroutineScope = CoroutineScope(registrationJob + Dispatchers.Main)
            coroutineScope.launch(_errorHandler) {
                resultList = authRepository.register(firstname, lastname, email, firstPassword)

                Timber.i("Registration " + resultList.success)
            }
        } else {
            _toastMesageObserver.setValue("Wrong credentials. Maybe passwords don't match")
        }
    }


    private fun checkCredentialsValidity(email: String, pass1: String, pass2: String): Boolean {
        return pass1 == pass2 && HelperClass.isValidEmail(email)
    }


}