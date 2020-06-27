package com.weaponoid.hachimichi.adapters

import android.hardware.Sensor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.weaponoid.hachimichi.R
import com.weaponoid.hachimichi.views.SensorsDirections
import kotlinx.android.synthetic.main.sensor_list_item.view.*

class SensorListAdapter(private val sensorList: List<Sensor>) :
    RecyclerView.Adapter<SensorListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.sensor_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = sensorList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val sensor = sensorList[position]
        val stringType = sensor.stringType.split(".")
        holder.itemView.sensor_name.text = sensor.name
        setSensorImage(holder.itemView.sensor_image,sensor.type)
        holder.itemView.sensor_vendor.text = "Vendor : ${sensor.vendor}"
        holder.itemView.sensor_type.text = "Type : ${stringType[2]}"
        holder.itemView.sensor_power.text = "Power : ${sensor.power}mW"

        holder.itemView.sensor.setOnClickListener {view->
            val action = SensorsDirections.sensorToData(sensor.type.toString())
            Navigation.findNavController(view).navigate(action)
        }








    }

    fun setSensorImage(sensorImage: ImageView,type:Int){

        when(type){
            18,19 -> {
                sensorImage.setImageResource(R.drawable.pedometer)
            }
            30,17 -> {
                sensorImage.setImageResource(R.drawable.motion)
            }
            1,10,35 -> {
                sensorImage.setImageResource(R.drawable.acceleration)
            }
            2,14 -> {
                sensorImage.setImageResource(R.drawable.magnet)
            }
            5 -> {
                sensorImage.setImageResource(R.drawable.light)
            }
            8 -> {
                sensorImage.setImageResource(R.drawable.proximity)
            }
            4,16 -> {
                sensorImage.setImageResource(R.drawable.gyro)
            }
            9 -> {
                sensorImage.setImageResource(R.drawable.gyro)
            }
            11,15,20,27 -> {
                sensorImage.setImageResource(R.drawable.rotate)
            }
            29 -> {
                sensorImage.setImageResource(R.drawable.wakeup)
            }
            22 -> {
                sensorImage.setImageResource(R.drawable.tilt)
            }





        }

    }
}