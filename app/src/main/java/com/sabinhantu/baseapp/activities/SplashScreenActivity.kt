package com.sabinhantu.baseapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.welcome.WelcomeActivity
import com.sabinhantu.baseapp.data.lifecycle.SABAutoDisposable
import com.sabinhantu.baseapp.helper.Constants

class SplashScreenActivity: AppCompatActivity() {

    private val autoDisposable by lazy {
        SABAutoDisposable()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onStart() {
        super.onStart()
        autoDisposable.bindTo(this.lifecycle)
    }

    override fun onResume() {
        super.onResume()
        startAppSession()
    }

    private fun startAppSession() {
        goToWelcomeActivity(Constants.FragmentTags.TAG_FRAGMENT_LOGIN)

//        val sessionToken = SharedPreferencesHelper.getSessionToken(this);
//        when (sessionToken.isEmpty()) {
//            true -> goToWelcomeActivity(Constants.FragmentTags.TAG_FRAGMENT_LOGIN)
//            false -> {
//
//                getUserDetails()
//            }
//        }

    }

    /**
     * Marker : Navigation
     * =============================================================================================
     */

    private fun goToWelcomeActivity(fragment: String){
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.putExtra(Constants.FragmentTags.TAG_FRAGMENT, fragment)
        startActivity(intent)
        finish()
    }

    private fun goToHomeActivity() {
//        val intent = Intent(this@DVSplashScreenActivity, DVHomeActivity::class.java)
//        startActivity(intent)
//        finish()
    }
}