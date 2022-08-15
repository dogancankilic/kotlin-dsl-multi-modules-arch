package com.dogancan.core.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * @author dogancankilic
 * Created on 14.08.2022
 */

/**
 * Binding adapters
 */
@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(this)
}