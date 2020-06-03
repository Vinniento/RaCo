package com.example.raco.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.raco.data.api.user.UserRepo
import com.example.raco.models.DefaultResponse
import kotlinx.coroutines.*
import timber.log.Timber

class LoginViewModel : ViewModel() {
    private val _authRepository = UserRepo
    var isLoginValid: Boolean = false
    private lateinit var _defaultResponse: DefaultResponse

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

    private val _errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable.message.toString())
    }

    fun login(email: String, password: String) {
        val loginJob = Job()
        val coroutineScope = CoroutineScope(loginJob + Dispatchers.Main)
        coroutineScope.launch(_errorHandler) {
            _defaultResponse = _authRepository.login(email, password)
            // Timber.i("Login Response: " + _defaultResponse.success)
        }
        isLoginValid = _defaultResponse.success == "valid"
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