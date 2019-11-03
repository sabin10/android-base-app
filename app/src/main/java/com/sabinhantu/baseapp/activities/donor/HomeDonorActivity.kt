package com.sabinhantu.baseapp.activities.donor

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.SABBaseActivity
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.fragments.donor.DonateDonorFragment
import com.sabinhantu.baseapp.fragments.donor.ProfileDonorFragment
import com.sabinhantu.baseapp.fragments.donor.RaisesDonorFragment
import com.sabinhantu.baseapp.helper.Constants

class HomeDonorActivity : SABBaseActivity() {

    lateinit var bottomNav: AHBottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_donor)

        // start with raises fragment
        onAddFragment(Constants.FragmentTags.TAG_FRAGMENT_RAISES_DONOR)
        bottomNav = findViewById(R.id.bottom_navigation_donor)
        setBottomNavigation()
    }

    override fun getFragmentContainer(): Int? {
        return R.id.fragments_container_home_donor
    }

    override fun getFragmentByTag(TAG: String): SABBaseFragment? {
        return when (TAG) {
            Constants.FragmentTags.TAG_FRAGMENT_RAISES_DONOR -> RaisesDonorFragment.newInstance()
            Constants.FragmentTags.TAG_FRAGMENT_DONATE_DONOR -> DonateDonorFragment.newInstance()
            Constants.FragmentTags.TAG_FRAGMENT_PROFILE_DONOR -> ProfileDonorFragment.newInstance()
            else -> null
        }

    }

    // Bottom Nav
    private fun setBottomNavigation() {
        val raisesItem = AHBottomNavigationItem("Requests", R.drawable.ic_view_headline_24, R.color.DarkGray)
        val donateItem = AHBottomNavigationItem("Donate", R.drawable.ic_add_circle_24, R.color.DarkGray)
        val profileItem = AHBottomNavigationItem("Profile", R.drawable.ic_person_24, R.color.DarkGray)
        bottomNav.addItem(raisesItem)
        bottomNav.addItem(donateItem)
        bottomNav.addItem(profileItem)

        bottomNav.setDefaultBackgroundColor(resources.getColor(R.color.White))

        bottomNav.accentColor = resources.getColor(R.color.Green)
        bottomNav.inactiveColor = resources.getColor(R.color.LightGray)
        bottomNav.isForceTint = true

        bottomNav.setOnTabSelectedListener { position, wasSelected ->
            // position = [0,1,2]
            if (wasSelected)
                return@setOnTabSelectedListener false
            when (position) {
                0 -> onReplaceFragment(Constants.FragmentTags.TAG_FRAGMENT_RAISES_DONOR)
                1 -> onReplaceFragment(Constants.FragmentTags.TAG_FRAGMENT_DONATE_DONOR)
                2 -> onReplaceFragment(Constants.FragmentTags.TAG_FRAGMENT_PROFILE_DONOR)
            }
            return@setOnTabSelectedListener  true
        }



    }


}
