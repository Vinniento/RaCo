package com.example.raco.utilities

class HelperClass {
    companion object {
        //TODO in eine HelperClass auslagern weils in login/resetPassword auch gebraucht wird
        fun isValidEmail(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        }

        fun isValidDate(training_date: String): Boolean {
            //TODO implement date format check
            return true
        }


    }
}