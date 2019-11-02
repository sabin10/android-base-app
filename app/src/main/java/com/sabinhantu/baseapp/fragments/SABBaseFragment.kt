package com.sabinhantu.baseapp.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.sabinhantu.baseapp.data.lifecycle.AutoDisposable
import com.sabinhantu.baseapp.interfaces.*

abstract class SABBaseFragment: Fragment() {

    protected var mAlertCallback: OnActivityAlert? = null
    private var mHandleFragmentsCallback: OnActivityFragmentCommunication? = null
    protected var mPermissionCallback: OnActivityPermission? = null

    protected var mRequestAction: OnRequestAction? = null
    protected var mPermissionRequestAction: OnRequestPermission? = null

    protected val autoDisposable by lazy {
        AutoDisposable()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnActivityFragmentCommunication) {
            mHandleFragmentsCallback = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }

        if (context is OnActivityAlert) {
            mAlertCallback = context
        }

        if(context is OnActivityPermission){
            mPermissionCallback = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mHandleFragmentsCallback = null
        mAlertCallback = null
        mPermissionCallback = null
    }

    override fun onStart() {
        super.onStart()

        autoDisposable.bindTo(this.lifecycle)
    }

    override fun onStop() {
        super.onStop()
        hideProgressDialog()
    }

    /**
     * Marker : Alerts
     * =============================================================================================
     */
    protected fun showProgressDialog() =
        mAlertCallback?.showProgressDialog()

    protected fun hideProgressDialog() =
        mAlertCallback?.hideProgressDialog()


    /**
     * Marker : Permissions
     * =============================================================================================
     */

    protected fun requestPermission(requestedPermission: String,
                                    requestCode: Int,
                                    actionListener: OnRequestPermission?,
                                    fragment: SABBaseFragment?) =
        mPermissionCallback?.requestPermission(requestedPermission, requestCode, actionListener, fragment)

    protected fun handlePermissionResult(permissions: Array<out String>,
                                         grantResults: IntArray,
                                         actionListener: OnRequestPermission?){
        mPermissionCallback?.handlePermissionResult(permissions, grantResults, actionListener)
    }


    /**
     * Marker : Keyboard
     * =============================================================================================
     */
    fun hideKeyboardFrom(view: View) {
        val ctx = context ?: return
        val imm = ctx.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Marker : Fragment Management
     * =============================================================================================
     */

    protected fun onAddFragmentByTAG(TAG: String, bundle: Bundle? = null, animated: Boolean = false) =
        mHandleFragmentsCallback?.onAddFragment(TAG, bundle, animated)

    protected fun onReplaceFragmentByTAG(TAG: String, bundle: Bundle? = null, animated: Boolean = true) =
        mHandleFragmentsCallback?.onReplaceFragment(TAG, bundle, animated)

    protected fun onRemoveFragmentByTAG(TAG: String) =
        mHandleFragmentsCallback?.onRemoveFragment(TAG)

    protected fun setActionBarTitle(title: String) =
        mHandleFragmentsCallback?.setActionBarTitle(title)

}