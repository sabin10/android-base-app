package com.sabinhantu.baseapp.interfaces

import androidx.appcompat.app.AlertDialog
import com.sabinhantu.SABApplication
import com.sabinhantu.baseapp.R

interface OnActivityAlert {

    fun showProgressDialog()
    fun hideProgressDialog()

    fun showToast(message: String)

    fun showAlert(
        message: String,
        isPositiveButton: Boolean = true,
        isNegativeButton: Boolean = true,
        positiveButtonText: String = SABApplication.instance.getString(R.string.action_ok),
        negativeButtonText: String = SABApplication.instance.getString(R.string.action_cancel),
        actionListener: OnRequestAction? = null,
        isCancelable: Boolean = false
    ): AlertDialog?
}