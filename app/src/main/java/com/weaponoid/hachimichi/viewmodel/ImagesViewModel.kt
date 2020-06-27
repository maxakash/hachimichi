package com.weaponoid.hachimichi.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImagesViewModel : ViewModel() {


    val _imageUrl by lazy { MutableLiveData<String>() }
    val imageUrl: LiveData<String>
        get() = _imageUrl

    val _isFetching by lazy { MutableLiveData<String>() }
    val isFetching: LiveData<String>
        get() = _isFetching


    fun getImages() {

        _isFetching.value = "Fetching and loading first image"

        CoroutineScope(IO).launch {

            withContext(Main) {

                Firebase.database.getReference("image1").addValueEventListener(object :
                    ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        _imageUrl.value = dataSnapshot.value as String
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w(TAG, "Failed to read value.", error.toException())
                    }
                })
            }

            //delay of 5 seconds to fetch another image
            delay(5000)

            withContext(Main) {

                _isFetching.value = "Fetching and loading second image"

                Firebase.database.getReference("image2").addValueEventListener(object :
                    ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        //println(dataSnapshot.value)
                        _imageUrl.value = dataSnapshot.value as String
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w(TAG, "Failed to read value.", error.toException())
                    }
                })
            }
        }


    }


}