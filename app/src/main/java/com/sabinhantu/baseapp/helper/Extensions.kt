package com.sabinhantu.baseapp.helper

import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import com.sabinhantu.baseapp.data.lifecycle.AutoDisposable
import io.reactivex.disposables.Disposable


fun Disposable.addTo(autoDisposable: AutoDisposable) {
    autoDisposable.add(this)
}


fun View.rotate(duration: Long = 1500): ObjectAnimator {
    val rotate = ObjectAnimator.ofFloat(this, "rotation", 0f, 360f)
    rotate.duration = duration
    rotate.repeatCount = Animation.INFINITE
    rotate.interpolator = LinearInterpolator()

    return rotate
}


fun String.logErrorMessage(TAG: String = "tag") {
    Log.e(TAG, this)
}
