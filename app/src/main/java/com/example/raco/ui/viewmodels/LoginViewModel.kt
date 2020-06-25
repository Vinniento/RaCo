package com.example.raco.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.raco.data.api.user.UserRepo
import com.example.raco.models.DefaultResponse
import com.example.raco.utilities.HelperClass
import kotlinx.coroutines.*
import timber.log.Timber

class LoginViewModel : ViewModel() {
    private val _authRepository = UserRepo
    private lateinit var _defaultResponse: DefaultResponse

    private val _loginState = MutableLiveData<DefaultResponse>()
    val loginState: LiveData<DefaultResponse>
        get() = _loginState

    private val _loginJob = Job()

    private val _snackbarMessageObserver = MutableLiveData<String>()
    val snackbarMessageObserver: LiveData<String>
        get() = _snackbarMessageObserver

    private val uiScope = CoroutineScope(Dispatchers.Main + _loginJob)
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

    private suspend fun getLoginData(email: String, password: String) {
        /*return withContext(Dispatchers.Main) {
            val successResponse = _authRepository.login(email, password)
            // Timber.i("Login Response: " + _defaultResponse.success)

            successResponse
        }*/
    }

    fun login(email: String, password: String) {
        if (checkUserCredentialsValidity(email, password)) {

            // _toastMessageObserver.value="Credentials valid"
            CoroutineScope(Dispatchers.Main + _loginJob).launch(_errorHandler) {
                val resultState = _authRepository.login(email, password)
                _loginState.value = resultState
                _snackbarMessageObserver.value = resultState.success
                Timber.i(resultState.success)
            }
        } else
            _snackbarMessageObserver.value = "Email or password are in invalid format"
    }

    private fun checkUserCredentialsValidity(inputEmail: String, inputPassword: String): Boolean {
        return (HelperClass.isValidEmail(inputEmail) && inputPassword.length >= 8)
    }

}