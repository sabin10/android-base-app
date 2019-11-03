package com.sabinhantu.baseapp.activities.details

import android.os.Bundle
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.SABBaseActivity
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.fragments.details.DonationDetailsFragment
import com.sabinhantu.baseapp.helper.Constants.FragmentTags.TAG_FRAGMENT_DONATION_DETAILS
import com.sabinhantu.baseapp.helper.logErrorMessage

class DonationDetailsActivity : SABBaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.activity_donation_details)

        val bundleExtra = intent?.getBundleExtra("bundle")
//        "bundleExtra=$bundleExtra".logErrorMessage()

        onReplaceFragment(TAG_FRAGMENT_DONATION_DETAILS,bundleExtra)
    }

    override fun getFragmentContainer(): Int? = R.id.fragments_container_donation

    override fun getFragmentByTag(TAG: String): SABBaseFragment? {
        return when(TAG) {
            TAG_FRAGMENT_DONATION_DETAILS -> DonationDetailsFragment.newInstance()
            else -> null
        }
    }

}