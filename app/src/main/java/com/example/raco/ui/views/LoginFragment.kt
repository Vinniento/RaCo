package com.example.raco.login

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.raco.R
import com.example.raco.databinding.FragmentLoginBinding
import com.example.raco.ui.viewmodels.SharedViewModelUser
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private lateinit var _viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    private val _sharedViewModel: SharedViewModelUser by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        Timber.i("onCreateView called")
        _viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = _viewModel
        // binding.setLifecycleOwner(this) TODO hereausfinden wieso er das nicht akzeptiert
//navigation onClicks
        binding.textCreateAccount.setOnClickListener {
            //  findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.textForgotPassword.setOnClickListener {
            // findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToResetPasswordFragment())
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }

        _viewModel.loginState.observe(this.viewLifecycleOwner, Observer {
            if (it.success == "valid") {
                _sharedViewModel.getUser("email@gmail.com")
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

            }
        })
        _viewModel.snackbarMessageObserver.observe(viewLifecycleOwner, Observer {
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                it, Snackbar.LENGTH_LONG
            ).show()
        })
//functional onClicks
        binding.buttonSignIn.setOnClickListener {
            //TODO hier binding.email oder nur email?
            _viewModel.login(
                inputEmail.text.toString(),
                inputPassword.text.toString()
            )
            Timber.i("login called")
        }

//TODO observe livedata
//button_login.isEnabled = viewModel.isUserCredentialsValid("schauerv@gmail.com", "dfdfdfdfdfd")

        //  setHasOptionsMenu(true)



        return binding.root
    }


    //TODO zum laufen bringen
    @Override
    fun changeWelcomeTextColour() {  // Text to set the TextView
        val mText = "Welcome to RaCo"

// to apply the foreground color span to substrings
        val mSpannableString = SpannableString(mText)

// color styles to apply on substrings
        val mGray = ForegroundColorSpan(Color.GRAY)
        val mRed = ForegroundColorSpan(Color.RED)
        val mGreen = ForegroundColorSpan(Color.GREEN)
        val mBlue = ForegroundColorSpan(Color.BLUE)

// applying the color styles to substrings
        mSpannableString.setSpan(mGray, 11, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        mSpannableString.setSpan(mRed, 12, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        mSpannableString.setSpan(mGreen, 13, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        mSpannableString.setSpan(mBlue, 14, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

// setting text to the textView

        binding.textWelcome.text = mSpannableString
    }


    override fun onResume() {
        super.onResume()
        changeWelcomeTextColour()

        Timber.i("onResume called")

    }

    override fun onStart() {
        super.onStart()
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
