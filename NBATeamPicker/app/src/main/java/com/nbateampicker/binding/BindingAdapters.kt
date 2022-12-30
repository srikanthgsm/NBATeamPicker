package com.nbateampicker.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.nbateampicker.R
import com.bumptech.glide.Glide


/**
 * Data Binding adapters specific to the app.
 */
object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("imageResource")
    fun imageResource(view: ImageView, drawableId: Int) {
        view.setImageResource(drawableId)
    }
}
