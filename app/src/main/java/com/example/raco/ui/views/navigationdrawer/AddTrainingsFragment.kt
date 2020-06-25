package com.example.raco.ui.views.navigationdrawer

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
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
import java.util.*

class AddTrainingsFragment : Fragment() {

    private lateinit var _binding: FragmentAddTrainingsBinding
    private lateinit var _viewModel: AddTrainingViewModel
    private lateinit var _datePickerDialog: DatePickerDialog
    private lateinit var _timePickerDialog: TimePickerDialog

    @RequiresApi(Build.VERSION_CODES.N)
    private val dateFormat = SimpleDateFormat("dd MMM, YYYY", Locale.GERMAN)

    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0

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

            _viewModel.addTraining(year, month, day, hour, minute)

        }
        _binding.addTrainingViewModel = _viewModel
        //OnClicks
        //TODO hier evtl gleich ein ganzes user objekt übergeben?
        _binding.trainingsDateTextview.setOnClickListener {
            val currentDate = Calendar.getInstance()

            _datePickerDialog =
                DatePickerDialog(
                    requireActivity(),
                    DatePickerDialog.OnDateSetListener { view, yearDialog, monthOfYear, dayOfMonth ->
                        //TODO date formaten auslagern in helper class function?
                        val selectedDate = Calendar.getInstance()
                        selectedDate.set(Calendar.YEAR, yearDialog)
                        selectedDate.set(Calendar.MONTH, monthOfYear)
                        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                        val date = dateFormat.format(selectedDate.time)

                        day = dayOfMonth
                        year = yearDialog
                        month = monthOfYear + 1
                        trainingsDateTextview.text = "Date: $date"

                    },
                    currentDate.get(Calendar.YEAR),
                    currentDate.get(Calendar.MONTH),
                    currentDate.get(Calendar.DAY_OF_MONTH)
                )

            _datePickerDialog.show()
        }

        _binding.trainingsTimeTextview.setOnClickListener {

            val currentTime: Calendar = Calendar.getInstance()
            hour = currentTime.get(Calendar.HOUR)
            minute = currentTime.get(Calendar.MINUTE)

            _timePickerDialog =
                TimePickerDialog(
                    requireActivity(),
                    TimePickerDialog.OnTimeSetListener { view, hourDialog, minuteDialog ->
                        val selectedTime = Calendar.getInstance()
                        selectedTime.set(Calendar.HOUR_OF_DAY, hourDialog)
                        selectedTime.set(Calendar.MINUTE, minuteDialog)
                        val timeFormat = SimpleDateFormat("HH:mm").format(selectedTime.time)
                        hour = hourDialog
                        minute = minuteDialog
                        trainingsTimeTextview.text =
                            "$timeFormat"
                    },
                    currentTime.get(Calendar.HOUR_OF_DAY),
                    currentTime.get(Calendar.MINUTE),
                    true
                )

            _timePickerDialog.show()
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
