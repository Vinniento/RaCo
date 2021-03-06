package com.example.raco.ui.views.navigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.example.raco.R
import com.example.raco.ui.viewmodels.SharedViewModelUser
import timber.log.Timber

class LogoutFragment : Fragment() {

    private val _viewModel: SharedViewModelUser by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.hide()
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel.logOut()

    }

    override fun onResume() {
        super.onResume()

        Timber.i("onResume called")
    }

    override fun onStart() {
        super.onStart()
        //TODO backstack deleten
        FragmentManager.POP_BACK_STACK_INCLUSIVE
        Timber.i("onStart called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.i("onCreate called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStopCalled")
    }

}
