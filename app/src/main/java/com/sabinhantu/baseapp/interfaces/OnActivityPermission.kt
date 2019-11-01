package com.sabinhantu.baseapp.interfaces

import com.sabinhantu.baseapp.fragments.SABBaseFragment

interface OnActivityPermission {
    fun requestPermission(requestedPermission: String,
                          requestCode: Int,
                          actionListener: OnRequestPermission?,
                          fragment: SABBaseFragment?)

    fun handlePermissionResult(permissions: Array<out String>,
                               grantResults: IntArray,
                               actionListener: OnRequestPermission?)
}