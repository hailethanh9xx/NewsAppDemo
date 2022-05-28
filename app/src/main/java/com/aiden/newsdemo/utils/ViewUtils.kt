package com.aiden.newsdemo.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Created by Aiden ( hai Le Thanh ) on 27/05/2022.
 * aiden9xx@gmail.com
 */

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun Fragment.isConnectedToInternet() : Boolean{
    return NetworkUtils.getNetworkLiveData(requireContext()).value == true
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, message, duration).show()
}

fun View.animationHide() {
    animate()
        .alpha(1f)
        .setStartDelay(1000L)
        .setDuration(1000L)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                hide()
            }
        })
}