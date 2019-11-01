package com.sabinhantu.baseapp.interfaces

import android.os.Bundle

interface OnActivityFragmentCommunication {
    fun onAddFragment(TAG: String, bundle: Bundle? = null, animated: Boolean = false)
    fun onReplaceFragment(TAG: String, bundle: Bundle? = null, animated: Boolean = true)
    fun onRemoveFragment(TAG: String, animated: Boolean = true)
    fun setActionBarTitle(title: String)
}