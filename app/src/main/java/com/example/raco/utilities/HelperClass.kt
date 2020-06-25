package com.example.raco.utilities

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class HelperClass {
    companion object {
        //TODO in eine HelperClass auslagern weils in login/resetPassword auch gebraucht wird
        fun isValidEmail(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun parseDateFromJson(training_date: String): String =
            LocalDate.parse(training_date).format(DateTimeFormatter.ofPattern("dd MMM, yyyy"))

        @RequiresApi(Build.VERSION_CODES.O)
        fun parseTimeFromJson(training_time: String): String =
            LocalTime.parse(training_time).format(DateTimeFormatter.ofPattern("HH:mm"))

    }
}