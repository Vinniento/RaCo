package com.example.raco.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.raco.R
import com.example.raco.databinding.FragmentResetpasswordBinding
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ResetPasswordFragment : Fragment() {
    private lateinit var viewModel: ResetPasswordViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentResetpasswordBinding>(
                inflater,
                R.layout.fragment_resetpassword,
                container,
                false
            )
        Timber.i("onCreateView called")

        viewModel = ViewModelProvider(this).get(ResetPasswordViewModel::class.java)
        binding.buttonResetPassword.setOnClickListener {
            viewModel.resetPassword()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume called")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStopCalled")

    }

}

