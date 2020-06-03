package com.example.raco.login

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.raco.DrawerInterface
import com.example.raco.R
import com.example.raco.databinding.FragmentLoginBinding
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private lateinit var _viewModel: LoginViewModel
    private lateinit var _drawerInterface: DrawerInterface
    private lateinit var binding: FragmentLoginBinding
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

//navigation onClicks
        binding.textCreateAccount.setOnClickListener {
            //  findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.textForgotPassword.setOnClickListener {
            // findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToResetPasswordFragment())
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }
//functional onClicks
        binding.buttonSignIn.setOnClickListener {
            //TODO hier binding.email oder nur email?
            _viewModel.login(
                "email@gmail.com",
                "password"
            )
            if (_viewModel.isLoginValid)
                Timber.i("ViewModel isLogin Valid: ${_viewModel.isLoginValid}")
            /* findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
             Toast.makeText(context, "Welcome username", Toast.LENGTH_LONG).show()*/
            /*}*/ /*else {
                Toast.makeText(context, "Wrong credentials", Toast.LENGTH_LONG).show()
            }*/
        }

        //TODO observe livedata
        //button_login.isEnabled = viewModel.isUserCredentialsValid("schauerv@gmail.com", "dfdfdfdfdfd")

        changeWelcomeTextColour()
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _drawerInterface = context as DrawerInterface
    }

    fun changeWelcomeTextColour() {  // Text to set the TextView
        val mText = "Welcome to RaCo"

        // to apply the foreground color span to substrings
        val mSpannableString = SpannableString(mText)

        // color styles to apply on substrings
        val mBlack = ForegroundColorSpan(Color.BLACK)
        val mRed = ForegroundColorSpan(Color.RED)
        val mGreen = ForegroundColorSpan(Color.GREEN)
        val mBlue = ForegroundColorSpan(Color.BLUE)

        // applying the color styles to substrings
        mSpannableString.setSpan(mBlack, 11, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        mSpannableString.setSpan(mRed, 12, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        mSpannableString.setSpan(mGreen, 13, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        mSpannableString.setSpan(mBlue, 14, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // setting text to the textView

        binding.textWelcome.text = mSpannableString


    }


    override fun onResume() {
        super.onResume()
        Timber.i("onResume called")
        _drawerInterface.closeDrawer()

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
