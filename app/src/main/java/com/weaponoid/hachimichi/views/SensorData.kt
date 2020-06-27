package com.weaponoid.hachimichi.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.weaponoid.hachimichi.R
import com.weaponoid.hachimichi.viewmodel.SensorDataViewModel
import kotlinx.android.synthetic.main.sensor_data_fragment.*

class SensorData : BottomSheetDialogFragment(){


    private lateinit var viewModel: SensorDataViewModel

    private val sensorData = Observer<String> { sensorData ->
        sensor_Data.text = sensorData
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sensor_data_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.title = "Sensor Data"

        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.actionBar?.setDisplayShowHomeEnabled(true)

        viewModel = ViewModelProvider(this).get(SensorDataViewModel::class.java)
        viewModel.sensorData.observe(viewLifecycleOwner,sensorData)

        arguments?.let {
            val type = SensorDataArgs.fromBundle(it).sensorType.toInt()
            viewModel.getSensorData(requireContext(),type)
        }



    }

}