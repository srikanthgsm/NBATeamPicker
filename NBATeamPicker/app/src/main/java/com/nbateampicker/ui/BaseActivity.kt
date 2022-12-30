package com.nbateampicker.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.nbateampicker.VhealthApp
import com.nbateampicker.receiver.ConnectionLiveData


open class BaseActivity : AppCompatActivity() {

    companion object {

        var mLastIntercationtime = System.currentTimeMillis()
    }

    var activityVisible = true


    lateinit var outageAlertDialog: Dialog

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        internetStateObserver()
    }

    private fun internetStateObserver() {
        // for network check
        val connectionLiveData = ConnectionLiveData(applicationContext)
        connectionLiveData.observe(this, Observer {
            VhealthApp.isOnline = it
        })
    }

    override fun onUserInteraction() {
        super.onUserInteraction()

        mLastIntercationtime = System.currentTimeMillis()
    }

    override fun onResume() {
        activityVisible = true
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        activityVisible = false
    }
}
