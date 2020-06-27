package com.weaponoid.hachimichi.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.weaponoid.hachimichi.R

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 40f
        start()
    }

}

fun ImageView.loadSliderImage(uri: String?, progressDrawable: CircularProgressDrawable) {

    progressDrawable.setColorSchemeColors(context.getColor(R.color.colorAccent))
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .fitCenter()

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .into(this)

}