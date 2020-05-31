package com.example.raco.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel : ViewModel() {

    private val _inputEmail = MutableLiveData<String>()
    val inputEmail: LiveData<String>
        get() = _inputEmail
    private val _password = MutableLiveData<String>()
    val inputPassword: LiveData<String>
        get() = _password

    override fun onCleared() {
        super.onCleared()
        Timber.i("LoginViewModel destroyed")
    }

    init {
        Timber.i("LoginViewModel created")
    }

    fun login(inputEmail: String, inputPassword: String) {

    }

    fun checkUserCredentialsValidity(inputEmail: String, inputPassword: String): String {

        return if (android.util.Patterns.EMAIL_ADDRESS.matcher(inputEmail)
                .matches()
            && inputPassword.length > 8
        ) {
            "valid"
        } else
            "invalid"
    }
}