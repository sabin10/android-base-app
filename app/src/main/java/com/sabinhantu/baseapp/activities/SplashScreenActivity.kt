package com.sabinhantu.baseapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.welcome.WelcomeActivity
import com.sabinhantu.baseapp.data.lifecycle.AutoDisposable
import com.sabinhantu.baseapp.helper.Constants
import com.sabinhantu.baseapp.helper.addTo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashScreenActivity: AppCompatActivity() {

    private val autoDisposable by lazy {
        AutoDisposable()
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
        runLogoAnimation()
    }

    private fun startAppSession() {
        goToWelcomeActivity(Constants.FragmentTags.TAG_FRAGMENT_LOGIN_DONOR)

//        val sessionToken = SharedPreferencesHelper.getSessionToken(this);
//        when (sessionToken.isEmpty()) {
//            true -> goToWelcomeActivity(Constants.FragmentTags.TAG_FRAGMENT_LOGIN)
//            false -> {
//
//                getUserDetails()
//            }
//        }

    }


    private fun runLogoAnimation() {
//        val objectAnimator = imv_animation_loading.rotate(Constants.AnimationMoveSet.SPLASH_SCREEN_LOGO_DURATION)
//        val disposable =
//            Observable.just(objectAnimator)
//                .doOnDispose { objectAnimator.cancel() }
//                .subscribeBy(
//                    onNext = {
//                        objectAnimator.start()
//                    }
//                )

        Observable.timer(1500,TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                startAppSession()
            }
            .addTo(autoDisposable)
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