package com.example.raco.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.raco.R
import com.example.raco.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

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
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginViewModel = viewModel

//navigation onClicks
        binding.buttonGoToRegister.setOnClickListener {
            //  findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.buttonGoToForgotpassword.setOnClickListener {
            // findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToResetPasswordFragment())
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }
//functional onClicks
        binding.buttonLogin.setOnClickListener {
            //TODO hier binding.email oder nur email?
            viewModel.login(binding.email.text.toString(), binding.password.text.toString())
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        //TODO observe livedata

        //button_login.isEnabled = viewModel.isUserCredentialsValid("schauerv@gmail.com", "dfdfdfdfdfd")


        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }


    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }*/


}
