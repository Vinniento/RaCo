package com.example.raco.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.raco.data.api.retrofit.AuthRepository
import com.example.raco.utilities.HelperClass
import kotlinx.coroutines.*
import timber.log.Timber

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val authRepository = AuthRepository

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

    private val _alertDialogMessageOberver = MutableLiveData<String>()
    val alertDialogMessageOverver: LiveData<String>
        get() = _alertDialogMessageOberver

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
        // if (checkCredentialsValidity(email, firstPassword, secondPassword)) {
        //TODO register with User(firstname, lastname....) - gute Option?

        val mainActivityJob = Job()

        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch(_errorHandler) {
            val resultList = authRepository.testIP()
            Timber.i("Teeeest IP == " + resultList.ip)
        }
    }

    private fun checkCredentialsValidity(email: String, pass1: String, pass2: String): Boolean {
        return pass1 == pass2 && HelperClass.isValidEmail(email)
    }


}