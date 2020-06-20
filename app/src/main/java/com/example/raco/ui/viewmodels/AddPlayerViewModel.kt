package com.example.raco.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.raco.data.api.user.UserRepo
import com.example.raco.models.DefaultResponse
import com.example.raco.models.PlayerResponse
import com.example.raco.utilities.HelperClass
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import timber.log.Timber

class AddPlayerViewModel : ViewModel() {
    private val _authRepository = UserRepo
    private lateinit var _resultList: DefaultResponse
    private val _addPlayerJob = Job()
    private val _coroutineScope = CoroutineScope(_addPlayerJob + Dispatchers.Main)

    private lateinit var _playerList: List<PlayerResponse>

    private val _playerObjectList = MutableLiveData<List<PlayerResponse>>()

    val playerObjectList: LiveData<List<PlayerResponse>>
        get() = _playerObjectList

    private val _snackbarMessageObserver = MutableLiveData<String>()
    val snackbarMessageObserver: LiveData<String>
        get() = _snackbarMessageObserver

    private val _errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable.message.toString())
    }

    init {
        getAllPlayers()
    }

    fun addPlayer(
        playerFirstName: String,
        playerLastName: String,
        playerEmail: String
    ) {
        if (playerFirstName.isNotBlank() && playerLastName.isNotBlank() && HelperClass.isValidEmail(
                playerEmail
            )
        ) {
            _coroutineScope.launch(_errorHandler) {
                _resultList = _authRepository.addPlayer(
                    playerFirstName,
                    playerLastName,
                    playerEmail
                )

                _snackbarMessageObserver.value = _resultList.success

                Timber.i("Player added: " + _resultList.success)
            }
        } else {
            _snackbarMessageObserver.setValue("Fields are empty, please add a Name!")
        }
        //TODO allgemein zu livedata ändern? Würde es dann getAllPlayer automatisch aufrufen?
        getAllPlayers()
    }

    fun getAllPlayers() {
        val gson = Gson()
        val arrayTutorialType = object : TypeToken<List<PlayerResponse>>() {}.type
        _coroutineScope.launch(_errorHandler) {
            _playerList = _authRepository.getAllPlayers()
            _playerObjectList.value = gson.fromJson(gson.toJson(_playerList), arrayTutorialType)

            //_playerObjectList.forEachIndexed { index, playerResponse -> Timber.i("Index = $index + ${playerResponse}") }

        }
    }
}