package com.sabinhantu.baseapp.activities.welcome

import android.os.Bundle
import android.widget.Toast
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.SABBaseActivity
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.fragments.welcome.LoginFragment
import com.sabinhantu.baseapp.helper.Constants
import com.sabinhantu.baseapp.interfaces.OnRequestPermission

class WelcomeActivity: SABBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.activity_welcome)

        when (intent.extras?.getString(Constants.FragmentTags.TAG_FRAGMENT)) {
            Constants.FragmentTags.TAG_FRAGMENT_LOGIN -> {
                onAddFragment(Constants.FragmentTags.TAG_FRAGMENT_LOGIN)
                showToast("sabiiin")

            }
        }

    }

    override fun getFragmentContainer(): Int? {
        return R.id.fragments_container_welcome
    }

    override fun getFragmentByTag(TAG: String): SABBaseFragment? = when (TAG) {
        Constants.FragmentTags.TAG_FRAGMENT_LOGIN -> LoginFragment.newInstance()
        else -> null
    }
}