package com.example.raco.ui.views.navigationdrawer

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
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
    private lateinit var _datePickerDialog: DatePickerDialog
    var day = 0
    var month: Int = 0
    var year: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_trainings, container, false)

        val view: View = _binding.root
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel = ViewModelProvider(this).get(AddTrainingViewModel::class.java)
        Timber.i("onCreateView called")

        training_recyclerview.layoutManager = LinearLayoutManager(context)

        _viewModel.trainingObjectList.observe(viewLifecycleOwner, Observer {
            training_recyclerview.adapter = TrainingAdapter(_viewModel.trainingObjectList.value!!)

        })
        _binding.buttonAddTraining.setOnClickListener {

            _viewModel.addTraining(year, month, day)

        }
        _binding.addTrainingViewModel = _viewModel
        //OnClicks
        //TODO hier evtl gleich ein ganzes user objekt übergeben?
        _binding.trainingsDateTextview.setOnClickListener {

            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            _datePickerDialog =
                DatePickerDialog(
                    requireActivity(),
                    DatePickerDialog.OnDateSetListener { view, yearDialog, monthOfYear, dayOfMonth ->

                        day = dayOfMonth
                        year = yearDialog
                        month = monthOfYear
                        trainingsDateTextview.text = "$yearDialog-$month-$day"

                    },
                    year,
                    month,
                    day
                )

            _datePickerDialog.show()
        }
        /*_viewModel.addTraining(
            _binding.trainingsDateEditText.text.toString()

        )*/


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
