package com.example.raco.login

import android.R
import android.app.AlertDialog
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


    private val _errorHandler = CoroutineExceptionHandler { _, exception ->
        AlertDialog.Builder(application.applicationContext).setTitle("Error")
            .setMessage(exception.message)
            .setPositiveButton(R.string.ok) { _, _ -> }
            .setIcon(R.drawable.ic_dialog_alert).show()
    }

    //TODO livedata so einbauen, dass man nicht die parameter braucht zum übergeben im RegisterFragment
    fun createAccount(
        firstname: String,
        lastname: String,
        email: String,
        firstPassword: String,
        secondPassword: String
    ) {
        /* val service = AuthRetrofitFactory.makeRetrofitService()
         CoroutineScope(Dispatchers.IO).launch {
             val response = service.test()
             withContext(Dispatchers.Main) {
                 try {
                     /* if (response.success == "") {
                          //Do something with response e.g show to the UI.
                      } else {
                          Toast.makeText(
                              getApplication(),
                              "Error: ${response.error}",
                              Toast.LENGTH_LONG
                          ).show()
                      }*/
                     Timber.i(response.toString())
                 } catch (e: HttpException) {
                     Toast.makeText(
                         getApplication(),
                         "Exception ${e.message}",
                         Toast.LENGTH_LONG
                     ).show()
                 } catch (e: Throwable) {
                     Toast.makeText(getApplication(), "something went wrong", Toast.LENGTH_LONG)
                         .show()
                 }
             }
         }*/
        // if (checkCredentialsValidity(email, firstPassword, secondPassword)) {
        //TODO register with User(firstname, lastname....) - gute Option?
        val mainActivityJob = Job()

        //3 the Coroutine runs using the Main (UI) dispatcher
        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch(_errorHandler) {
            //4
            val resultList =
                authRepository.test()//.register(email, firstname, lastname, firstPassword)
            Timber.i(resultList.toString())
            // repoList.adapter = RepoListAdapter(resultList)
        }

        //}

    }

    private fun checkCredentialsValidity(email: String, pass1: String, pass2: String): Boolean {
        return pass1 == pass2 && HelperClass.isValidEmail(email)
    }


}