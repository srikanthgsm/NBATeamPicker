package com.nbateampicker.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.nbateampicker.VhealthApp
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection

/**
 * Helper class to automatically inject fragments if they implement [Injectable].
 */
object AppInjector {
    fun init(app: VhealthApp) {

        val dagger = DaggerAppComponent.builder().application(app).build()
        dagger.inject(app)

        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                //inject here
                handleActivity(activity)
            }


            override fun onActivityStarted(activity: Activity) {
                //no need to inject here
            }

            override fun onActivityResumed(activity: Activity) {
                //no need to inject here
            }

            override fun onActivityPaused(activity: Activity) {
                //no need to inject here
            }

            override fun onActivityStopped(activity: Activity) {
                //no need to inject here
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                //no need to inject here
            }

            override fun onActivityDestroyed(activity: Activity) {
                //no need to inject here
            }
        })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasAndroidInjector) {
            AndroidInjection.inject(activity)
        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(
                    object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentCreated(
                            fm: FragmentManager,
                            f: Fragment,
                            savedInstanceState: Bundle?
                        ) {
                            if (f is Injectable) {
                                AndroidSupportInjection.inject(f)
                            }
                        }
                    }, true
                )
        }
    }
}