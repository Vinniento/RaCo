package com.example.raco.ui.views.navigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.raco.R
import com.example.raco.databinding.FragmentAddTrainingsBinding
import com.example.raco.ui.TrainingAdapter
import com.example.raco.ui.viewmodels.AddTrainingViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_trainings.*
import timber.log.Timber

class AddTrainingsFragment : Fragment() {

    private lateinit var _binding: FragmentAddTrainingsBinding
    private lateinit var _viewModel: AddTrainingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_trainings, container, false)


        val view: View = _binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel = ViewModelProvider(this).get(AddTrainingViewModel::class.java)
        Timber.i("onCreateView called")

        training_recyclerview.layoutManager = LinearLayoutManager(context)

        _viewModel.trainingObjectList.observe(viewLifecycleOwner, Observer {
            training_recyclerview.adapter = TrainingAdapter(_viewModel.trainingObjectList.value!!)

        })
        _binding.addTrainingViewModel = _viewModel
        //OnClicks
        //TODO hier evtl gleich ein ganzes user objekt übergeben?
        _binding.buttonAddTraining.setOnClickListener {
            _viewModel.addTraining(
                _binding.trainingsDateEditText.text.toString()

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
