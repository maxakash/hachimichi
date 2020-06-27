package com.weaponoid.hachimichi.views

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.firebase.FirebaseApp
import com.weaponoid.hachimichi.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)

        //used for setting bottom navigation view with jetpack navigation
        setupBottomNav()

    }


    private fun setupBottomNav() {

        val navigationController = Navigation.findNavController(this, R.id.fragment)
        bottomNavigationView.let { NavigationUI.setupWithNavController(it, navigationController) }

    }
}