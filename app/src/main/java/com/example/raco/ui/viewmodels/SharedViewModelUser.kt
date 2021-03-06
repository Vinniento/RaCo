package com.example.raco.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.raco.data.api.user.UserRepo
import com.example.raco.models.PlayerResponse
import kotlinx.coroutines.*
import timber.log.Timber

class SharedViewModelUser : ViewModel() {
    private val _authRepository = UserRepo

    private val _loggedInUser = MutableLiveData<PlayerResponse>()
    val loggedInUser: LiveData<PlayerResponse>
        get() = _loggedInUser

    init {
        _loggedInUser.value = null
        Timber.i("SharedViewModelUser created")
    }

    private val _getUserJob = Job()


    override fun onCleared() {
        super.onCleared()
        Timber.i("SharedViewModelUser destroyed")
    }

    private val _errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable.message.toString())
    }

    fun getUser(email: String) {
        //only fetch user if doesn't exist - vl eh unnötig weil das schon gehandelt wird
        //if (_loggedInUser.value != null) {
        CoroutineScope(Dispatchers.Main + _getUserJob).launch(_errorHandler) {
            _loggedInUser.value = _authRepository.getUser(email)

            Timber.i("User = " + _loggedInUser.value.toString())
        }
        // }
    }

    fun logOut() {
        _loggedInUser.value = null
    }
}