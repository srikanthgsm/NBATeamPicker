package com.nbateampicker

import android.app.Application
import android.content.Context
import com.nbateampicker.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class VhealthApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    companion object {

        lateinit var context: Context

        var isOnline: Boolean = true

    }

    override fun onCreate() {
        super.onCreate()
        context = baseContext
        AppInjector.init(this)

    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}
