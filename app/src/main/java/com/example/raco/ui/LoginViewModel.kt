package com.example.raco.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    override fun onCleared() {
        super.onCleared()
        Timber.i("LoginViewModel destroyed")
    }

    init {
        Timber.i("LoginViewModel created")
    }

    fun login(email: String, password: String) {
        // if (!email.isBlank() && !password.isBlank())

        //else
        /* Toast.makeText(
             this,
             "Both email and password have to be set",
             Toast.LENGTH_LONG
         ).show()
 }*/
    }

    fun isUserCredentialsValid(email: String, password: String): Boolean {

        return (
                android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                    .matches()
                        && password.length > 8)
    }


}