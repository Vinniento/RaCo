package com.example.raco.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.raco.utilities.HelperClass

class RegisterViewModel : ViewModel() {
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

    private val _passwordSecond = MutableLiveData<String>()
    val inputPasswordSecond: LiveData<String>
        get() = _passwordSecond


    //TODO livedata so einbauen, dass man nicht die parameter braucht zum übergeben im RegisterFragment
    fun createAccount(
        firstname: String,
        lastname: String,
        email: String,
        firstPassword: String,
        secondPassword: String
    ) {
        if (checkCredentialValidity(email, firstPassword, secondPassword)) {
            //TODO register with User(firstname, lastname....) - gute Option?
        }

    }

    fun checkCredentialValidity(email: String, pass1: String, pass2: String): Boolean {
        return pass1 == pass2 && HelperClass.isValidEmail(email)
    }


}