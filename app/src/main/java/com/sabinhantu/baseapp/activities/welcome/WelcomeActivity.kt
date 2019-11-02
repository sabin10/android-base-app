package com.sabinhantu.baseapp.activities.welcome

import android.os.Bundle
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.SABBaseActivity
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.fragments.welcome.LoginAskerFragment
import com.sabinhantu.baseapp.fragments.welcome.LoginDonorFragment
import com.sabinhantu.baseapp.helper.Constants

class WelcomeActivity : SABBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.activity_welcome)

        when (intent.extras?.getString(Constants.FragmentTags.TAG_FRAGMENT)) {
            Constants.FragmentTags.TAG_FRAGMENT_LOGIN_DONOR -> {
                onReplaceFragment(Constants.FragmentTags.TAG_FRAGMENT_LOGIN_DONOR)

            }

            Constants.FragmentTags.TAG_FRAGMENT_LOGIN_ASKER -> {
                onReplaceFragment(Constants.FragmentTags.TAG_FRAGMENT_LOGIN_ASKER)
            }
        }

    }

    override fun getFragmentContainer(): Int? {
        return R.id.fragments_container_welcome
    }

    override fun getFragmentByTag(TAG: String): SABBaseFragment? {
        showToast(TAG)
        return when (TAG) {
            Constants.FragmentTags.TAG_FRAGMENT_LOGIN_DONOR -> LoginDonorFragment.newInstance()
            Constants.FragmentTags.TAG_FRAGMENT_LOGIN_ASKER -> LoginAskerFragment.newInstance()
            else -> null
        }
    }
}