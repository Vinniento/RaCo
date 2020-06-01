package com.example.raco.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.raco.R
import com.example.raco.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.fragment_register.*
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    //  private lateinit var drawerInterface: DrawerInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        val view: View = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        Timber.i("onCreateView called")

        binding.registerViewModel = viewModel
        //OnClicks
        //TODO hier evtl gleich ein ganzes user objekt übergeben?
        binding.buttonRegister.setOnClickListener {
            viewModel.createAccount(
                binding.registerFirstname.text.toString(),
                binding.registerLastname.text.toString(),
                binding.inputEmail.text.toString(),
                binding.registerPasswordOne.text.toString(),
                binding.registerPasswordTwo.text.toString()
            )

        }
        //Todo sowas hier oder in viewModel?
        val spinnerClubs: Spinner = spinnerClubs
        val spinnerCountries: Spinner = spinnerCountries
        //spinner_clubs.findViewById(R.id.spinner_clubs)
        // Create an ArrayAdapter using the string array and a default spinner layout
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.clubs_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinnerClubs.adapter = adapter
            }
        }
        this.context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.countries_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinnerCountries.adapter = adapter
            }
        }
        //  drawerInterface.closeDrawer()


    }


    /* override fun onAttach(context: Context) {
         super.onAttach(context)
         drawerInterface = context as DrawerInterface

     }*/
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
