package com.sabinhantu.baseapp.activities.asker

import android.os.Bundle
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.SABBaseActivity
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.fragments.asker.AskAskerFragment
import com.sabinhantu.baseapp.fragments.asker.DonationsAskerFragment
import com.sabinhantu.baseapp.fragments.asker.ProfileAskerFragment
import com.sabinhantu.baseapp.helper.Constants

class HomeAskerActivity : SABBaseActivity() {

    lateinit var bottomNav: AHBottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_asker)

        // start with donations fragment
        onAddFragment(Constants.FragmentTags.TAG_FRAGMENT_DONATIONS_ASKER)
        bottomNav = findViewById(R.id.bottom_navigation_asker)
        setBottomNavigation()
    }

    override fun getFragmentContainer(): Int? {
        return R.id.fragments_container_home_asker
    }

    override fun getFragmentByTag(TAG: String): SABBaseFragment? {
        return when (TAG) {
            Constants.FragmentTags.TAG_FRAGMENT_DONATIONS_ASKER -> DonationsAskerFragment.newInstance()
            Constants.FragmentTags.TAG_FRAGMENT_ASK_ASKER -> AskAskerFragment.newInstance()
            Constants.FragmentTags.TAG_FRAGMENT_PROFILE_ASKER -> ProfileAskerFragment.newInstance()
            else -> null
        }
    }

    // Bottom Nav
    private fun setBottomNavigation() {
        val donationsItem = AHBottomNavigationItem("Donations", R.drawable.ic_view_headline_24, R.color.DarkGray)
        val askItem = AHBottomNavigationItem("Ask", R.drawable.ic_add_circle_24, R.color.DarkGray)
        val profileItem = AHBottomNavigationItem("Profile", R.drawable.ic_person_24, R.color.DarkGray)
        bottomNav.addItem(donationsItem)
        bottomNav.addItem(askItem)
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
                0 -> onReplaceFragment(Constants.FragmentTags.TAG_FRAGMENT_DONATIONS_ASKER)
                1 -> onReplaceFragment(Constants.FragmentTags.TAG_FRAGMENT_ASK_ASKER)
                2 -> onReplaceFragment(Constants.FragmentTags.TAG_FRAGMENT_PROFILE_ASKER)
            }
            return@setOnTabSelectedListener  true
        }

    }
}