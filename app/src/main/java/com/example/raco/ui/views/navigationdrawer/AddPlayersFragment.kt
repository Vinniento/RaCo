package com.example.raco.ui.views.navigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.raco.R
import com.example.raco.databinding.FragmentAddPlayerBinding
import com.example.raco.ui.viewmodels.AddPlayerViewModel
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class AddPlayersFragment : Fragment() {

    private lateinit var _binding: FragmentAddPlayerBinding
    private lateinit var _viewModel: AddPlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_player, container, false)
        val view: View = _binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel = ViewModelProvider(this).get(AddPlayerViewModel::class.java)
        Timber.i("onCreateView called")

        _binding.addPlayerViewModel = _viewModel
        //OnClicks
        //TODO hier evtl gleich ein ganzes user objekt übergeben?
        _binding.buttonAddPlayers.setOnClickListener {
            _viewModel.addPlayer(
                _binding.playerFirstName.text.toString(),
                _binding.playersLastName.text.toString(),
                _binding.playerEmail.text.toString()
            )
        }

        _viewModel.snackbarMessageObserver.observe(viewLifecycleOwner, Observer {
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                it, Snackbar.LENGTH_SHORT
            ).show()
        })
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
