package com.example.raco.ui.viewmodels

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.raco.data.api.user.UserRepo
import com.example.raco.models.DefaultResponse
import com.example.raco.models.TrainingResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import timber.log.Timber
import java.time.format.DateTimeFormatter

class AddTrainingViewModel(application: Application) : AndroidViewModel(application) {
    private val _authRepository = UserRepo
    private lateinit var _resultList: DefaultResponse
    private val _addTrainingJob = Job()
    private val _coroutineScope = CoroutineScope(_addTrainingJob + Dispatchers.Main)

    private lateinit var _trainingList: List<TrainingResponse>

    private val _trainingObjectList = MutableLiveData<List<TrainingResponse>>()

    val trainingObjectList: LiveData<List<TrainingResponse>>
        get() = _trainingObjectList

    private val _snackbarMessageObserver = MutableLiveData<String>()
    val snackbarMessageObserver: LiveData<String>
        get() = _snackbarMessageObserver

    private val _errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable.message.toString())
    }

    init {
        getAllTrainings()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTraining(
        year: Int, month: Int, day: Int, hour: Int, minute: Int
    ) {
        //, , duration: Double
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        //TODO erstellen und validaten mit builder
        val date: String = "$year-$month-$day"
        val time: String = "$hour:$minute"
        // if (HelperClass.isValidDate(date) ) {
        _coroutineScope.launch(_errorHandler) {
            _resultList = _authRepository.addTraining(date, time, duration = 5.0)
            _snackbarMessageObserver.value = _resultList.success
            Timber.i("Training added: " + _resultList.success)
        }
        // } else {
        _snackbarMessageObserver.value = "Please add a date"

        getAllTrainings()
    }

    fun getAllTrainings() {
        val gson = Gson()
        val arrayTutorialType = object : TypeToken<List<TrainingResponse>>() {}.type
        _coroutineScope.launch(_errorHandler) {
            _trainingList = _authRepository.getAllTrainings()
            _trainingObjectList.value = gson.fromJson(gson.toJson(_trainingList), arrayTutorialType)

        }
    }
}