package com.example.raco.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.raco.data.api.user.UserRepo
import com.example.raco.models.DefaultResponse
import com.example.raco.utilities.HelperClass
import kotlinx.coroutines.*
import timber.log.Timber

class AddPlayerViewModel : ViewModel() {
    private val _authRepository = UserRepo
    private lateinit var _resultList: DefaultResponse
    private val _addPlayerJob = Job()

    private val _snackbarMessageObserver = MutableLiveData<String>()
    val snackbarMessageObserver: LiveData<String>
        get() = _snackbarMessageObserver

    private val _errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable.message.toString())
    }

    fun addPlayer(
        playerFirstName: String,
        playerLastName: String
    ) {
        if (!(playerFirstName.isEmpty()&&playerLastName.isEmpty())) {

            val coroutineScope = CoroutineScope(_addPlayerJob + Dispatchers.IO)
            coroutineScope.launch(_errorHandler) {
                _resultList = _authRepository.addplayer(playerFirstName, playerLastName)

                _snackbarMessageObserver.value = _resultList.success

                Timber.i("Player added with " + _resultList.success)
            }
        } else {
            _snackbarMessageObserver.setValue("Fields are empty, please add a Name!")
        }
    }
}