package com.example.raco.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.raco.models.User

class LoggedInSharedViewModel : ViewModel() {
    val user: User = User("", "", "")
}
