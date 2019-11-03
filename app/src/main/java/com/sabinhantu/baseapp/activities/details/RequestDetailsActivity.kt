package com.sabinhantu.baseapp.activities.details

import android.os.Bundle
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.SABBaseActivity
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.fragments.details.RequestDetailsFragment
import com.sabinhantu.baseapp.helper.Constants

class RequestDetailsActivity : SABBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.activity_request_details)

        val bundleExtra = intent?.getBundleExtra("bundle")
//        "bundleExtra=$bundleExtra".logErrorMessage()

        onReplaceFragment(Constants.FragmentTags.TAG_FRAGMENT_REQUEST_DETAILS,bundleExtra)
    }
    override fun getFragmentContainer(): Int? = R.id.fragments_container_request

    override fun getFragmentByTag(TAG: String): SABBaseFragment? {
        return when (TAG) {
            Constants.FragmentTags.TAG_FRAGMENT_REQUEST_DETAILS -> RequestDetailsFragment.newInstance()
            else -> null
        }
    }
}