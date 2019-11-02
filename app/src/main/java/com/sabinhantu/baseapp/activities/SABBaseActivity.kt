package com.sabinhantu.baseapp.activities

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.data.lifecycle.AutoDisposable
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.interfaces.*
import com.sabinhantu.baseapp.widgets.LoadingDialog

abstract class SABBaseActivity: AppCompatActivity(),
                                OnActivityAlert,
                                OnActivityFragmentCommunication,
                                OnActivityPermission {

    private var mProgressDialog: LoadingDialog? = null
    protected var mFragmentManager: FragmentManager? = null

    protected val autoDisposable by lazy {
        AutoDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFragmentManager = this.supportFragmentManager
    }

    override fun onStart() {
        super.onStart()
        autoDisposable.bindTo(this.lifecycle)
    }

    override fun onBackPressed() {
        var fragNum = mFragmentManager?.backStackEntryCount ?: 0

        if (fragNum <= 1){
            finish()
            return
        }

        super.onBackPressed()
    }

    /**
     * Marker : Main Views Initialization
     * =============================================================================================
     */

    private fun initViews() {
        mProgressDialog = LoadingDialog(this)
    }

    private fun setupViews() {
        val pd = mProgressDialog
        if (pd != null) {
            pd.requestWindowFeature(Window.FEATURE_NO_TITLE)
            pd.setContentView(R.layout.item_progress)
            pd.setCancelable(false)

            pd.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    /**
     * Marker : Alerts
     * =============================================================================================
     */

    override fun showProgressDialog() {
        val pd = mProgressDialog
        if (pd != null && !pd.isShowing) {
            runOnUiThread { pd.show() }
        }
    }

    override fun hideProgressDialog() {
        val pd = mProgressDialog
        if (pd != null && pd.isShowing) {
            runOnUiThread { pd.dismiss() }
        }
    }

    override fun showAlert(
        message: String,
        isPositiveButton: Boolean,
        isNegativeButton: Boolean,
        positiveButtonText: String,
        negativeButtonText: String,
        actionListener: OnRequestAction?,
        isCancelable: Boolean
    ): AlertDialog? {
        hideProgressDialog()

        val b = AlertDialog.Builder(this)
            .setMessage(message)
            .setCancelable(isCancelable)

        var positiveText = getString(R.string.action_ok)
        actionListener?.let {
            if (isNegativeButton) {
                b?.setNegativeButton(negativeButtonText) { _, _ ->
                    it.requestNegativeAction()
                }
            }

            positiveText = positiveButtonText
        }

        if (isPositiveButton) {
            b?.setPositiveButton(positiveText) { _, _ ->
                actionListener?.requestPositiveAction()
            }
        }

        val mAlert = b?.create() ?: return null
        mAlert.show()

        return mAlert
    }

    /**
     * Marker : Toasts
     * =============================================================================================
     */

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Marker : Fragment Management
     * =============================================================================================
     */

    private enum class FragmentActions {
        ADD,
        REPLACE,
        REMOVE
    }

    override fun onAddFragment(TAG: String, bundle: Bundle?, animated: Boolean) {
        onCreateFragmentAction(TAG, FragmentActions.ADD, bundle, animated)
    }

    override fun onReplaceFragment(TAG: String, bundle: Bundle?, animated: Boolean) {
        onCreateFragmentAction(TAG, FragmentActions.REPLACE, bundle, animated)
    }

    override fun onRemoveFragment(TAG: String, animated: Boolean) {
        onCreateFragmentAction(TAG, FragmentActions.REMOVE, animated = animated)
    }

    override fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    /**
     * Marker : Fragment Actions
     * =============================================================================================
     */

    protected abstract fun getFragmentContainer(): Int?

    private fun onAddFragment(fragment: SABBaseFragment, TAG: String, animated: Boolean) {
        val fm = mFragmentManager ?: return
        val fragmentContainer = getFragmentContainer() ?: return

        // Is adding fragment to backstack
        val transaction = fm.beginTransaction()
            .add(
                fragmentContainer,
                fragment,
                TAG
            )
            .addToBackStack(TAG)

        if (animated) {
            transaction.setCustomAnimations(
                getFragmentTransitionAnimRightToLeft()[0],
                getFragmentTransitionAnimRightToLeft()[1],
                getFragmentTransitionAnimLeftToRight()[0],
                getFragmentTransitionAnimLeftToRight()[1]
            )
        }

        transaction.commit()
    }

    private fun onReplaceFragment(fragment: SABBaseFragment, TAG: String, animated: Boolean) {
        val fm = mFragmentManager ?: return
        val fragmentContainer = getFragmentContainer() ?: return

        // Is not adding fragment to backstack
        val transaction = fm.beginTransaction()
            .replace(
                fragmentContainer,
                fragment,
                TAG
            )

        if (animated) {
            transaction.setCustomAnimations(
                getFragmentTransitionAnimRightToLeft()[0],
                getFragmentTransitionAnimRightToLeft()[1],
                getFragmentTransitionAnimLeftToRight()[0],
                getFragmentTransitionAnimLeftToRight()[1]
            )
        }

        transaction.commit()
    }

    private fun onRemoveFragment(fragment: SABBaseFragment, animated: Boolean) {
        mFragmentManager?.popBackStack()
    }

    private fun onCreateFragmentAction(
        TAG: String,
        fragmentAction: FragmentActions,
        bundle: Bundle? = null,
        animated: Boolean
    ) {
        val fragment = getFragmentByTag(TAG) ?: return
        fragment.arguments = bundle

        when (fragmentAction) {
            FragmentActions.ADD -> onAddFragment(fragment, TAG, animated)
            FragmentActions.REPLACE -> onReplaceFragment(fragment, TAG, animated)
            FragmentActions.REMOVE -> onRemoveFragment(fragment, animated)
        }
    }

    protected abstract fun getFragmentByTag(TAG: String): SABBaseFragment?

    private fun getFragmentTransitionAnimRightToLeft(): IntArray =
        intArrayOf(R.anim.enter_from_right, R.anim.exit_to_left)

    private fun getFragmentTransitionAnimLeftToRight(): IntArray =
        intArrayOf(R.anim.enter_from_left, R.anim.exit_to_right)

    /**
     * Marker : Permissions
     * =============================================================================================
     */

    override fun requestPermission(
        requestedPermission: String,
        requestCode: Int,
        actionListener: OnRequestPermission?,
        fragment: SABBaseFragment?
    ){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            when(ContextCompat.checkSelfPermission(SABApplication.instance, requestedPermission)){
//                PackageManager.PERMISSION_GRANTED -> actionListener?.permissionGrantedAction()
//                PackageManager.PERMISSION_DENIED -> {
//                    fragment?.let {
//                        it.requestPermissions(arrayOf(requestedPermission), requestCode)
//                        return
//                    }
//                    requestPermissions(arrayOf(requestedPermission), requestCode)
//                }
//            }
//
//        } else {
//            actionListener?.permissionGrantedAction()
//        }
    }

    override fun handlePermissionResult(permissions: Array<out String>,
                                        grantResults: IntArray,
                                        actionListener: OnRequestPermission?) {
//        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            actionListener?.permissionGrantedAction()
//        } else {
//            actionListener?.permissionDeniedAction()
//        }
    }

    /**
     * Marker : Keyboard
     * =============================================================================================
     */

    fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // check if no view has focus
        currentFocus?.let {
            inputManager.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}