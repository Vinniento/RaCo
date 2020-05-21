package com.example.raco.login

import android.content.Context
import android.os.Bundle
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
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
                binding.inputEmail.text.toString(),
                binding.inputPassword.text.toString()
            )
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        //TODO observe livedata

        //button_login.isEnabled = viewModel.isUserCredentialsValid("schauerv@gmail.com", "dfdfdfdfdfd")

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _drawerInterface = context as DrawerInterface
    }


    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }*/
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
