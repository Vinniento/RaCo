package com.example.raco.utilities

import android.text.TextUtils

class HelperClass {
    companion object {
        //TODO in eine HelperClass auslagern weils in login/resetPassword auch gebraucht wird
        fun isValidEmail(email: String): Boolean {
            return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        }
    }
}