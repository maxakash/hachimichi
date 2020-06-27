package com.weaponoid.hachimichi.viewmodel

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.lifecycle.ViewModel

class SensorsViewModel : ViewModel() {

    private lateinit var mSensorManager: SensorManager

    val sensorTypes = hashMapOf<String, Sensor>()

    fun getSensors(context: Context): List<Sensor> {

        mSensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val list: List<Sensor> = mSensorManager.getSensorList(Sensor.TYPE_ALL)

        list.forEach {

            if (!sensorTypes.containsKey(it.stringType)) {
                sensorTypes[it.stringType] = it
            }
        }

        val deviceSensors: ArrayList<Sensor> = arrayListOf()

        for (key in sensorTypes.keys) {
            sensorTypes[key]?.let { deviceSensors.add(it) }
        }

        return deviceSensors
    }


}
