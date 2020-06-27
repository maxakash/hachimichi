package com.weaponoid.hachimichi.views

import android.hardware.Sensor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weaponoid.hachimichi.R
import com.weaponoid.hachimichi.adapters.SensorListAdapter
import com.weaponoid.hachimichi.viewmodel.SensorsViewModel
import kotlinx.android.synthetic.main.sensors_fragment.*

class Sensors : Fragment() {


    private lateinit var viewModel: SensorsViewModel

    private lateinit var listAdapter: SensorListAdapter

    private var sensorList: List<Sensor> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sensors_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SensorsViewModel::class.java)

        activity?.title = "Sensors"

        sensorList = viewModel.getSensors(requireContext())

        listAdapter = SensorListAdapter(sensorList)

        sensor_List.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

}