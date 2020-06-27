package com.weaponoid.hachimichi.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.weaponoid.hachimichi.R
import com.weaponoid.hachimichi.util.getProgressDrawable
import com.weaponoid.hachimichi.util.loadSliderImage
import com.weaponoid.hachimichi.viewmodel.ImagesViewModel
import kotlinx.android.synthetic.main.images_fragment.*

class Images : Fragment() {


    private lateinit var viewModel: ImagesViewModel

    private val imageUrlObserver = Observer<String> { imageUrl ->
        imageView.loadSliderImage(imageUrl, getProgressDrawable(requireContext()))
    }

    private val isFetching = Observer<String> { isFetching ->
        showToast(isFetching)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.images_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ImagesViewModel::class.java)
        activity?.title = "Images"
        viewModel.imageUrl.observe(viewLifecycleOwner, imageUrlObserver)
        viewModel.isFetching.observe(viewLifecycleOwner, isFetching)
        viewModel.getImages()

    }

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}