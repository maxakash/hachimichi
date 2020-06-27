package com.weaponoid.hachimichi.viewmodel

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SensorDataViewModel : ViewModel(), SensorEventListener {

    private lateinit var mSensorManager: SensorManager

    private var type = 0

    private val _sensorData by lazy { MutableLiveData<String>() }

    val sensorData: LiveData<String>
        get() = _sensorData


    override fun onAccuracyChanged(p0: Sensor?, accuracy: Int) {
        println(accuracy)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null && event.values.isNotEmpty()) {

            when (type) {

                35, 4, 16, 17, 9, 10, 15, 22, 27, 1, 2, 20, 30 -> {
                    _sensorData.value =
                        "x: ${event.values[0]}\ny: ${event.values[1]}\nz: ${event.values[2]}"
                }
                14 -> {
                    _sensorData.value =
                        "x: ${event.values[0]}\ny: ${event.values[1]}\nz: ${event.values[2]}\n" +
                                "m: ${event.values[3]}"
                }
                3 -> {
                    _sensorData.value =
                        "x: ${event.values[0]} / azimuth\ny: ${event.values[1]} / pitch\nz: ${event.values[2]} / roll"
                }
                5, 8, 18, 19 -> {
                    _sensorData.value =
                        "${event.values[0]} "
                }

            }

        }
    }


    fun getSensorData(context: Context, type: Int) {

        this.type = type
        mSensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val mSensors = mSensorManager.getDefaultSensor(type)
        mSensorManager.registerListener(this, mSensors, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onCleared() {
        mSensorManager.unregisterListener(this)
        super.onCleared()

    }

}