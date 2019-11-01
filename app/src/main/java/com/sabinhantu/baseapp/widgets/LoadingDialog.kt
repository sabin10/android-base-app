package com.sabinhantu.baseapp.widgets

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface

class LoadingDialog: Dialog {

    private var mContext: Context

    constructor(context: Context): super(context) {
        this.mContext = context
    }

    constructor(context: Context,
                cancelable: Boolean,
                cancelListener: DialogInterface.OnCancelListener?) :
            super(context, cancelable, cancelListener) {

        this.mContext = context
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val c = mContext
        if (c is Activity) {
            c.onBackPressed()
        }
    }
}