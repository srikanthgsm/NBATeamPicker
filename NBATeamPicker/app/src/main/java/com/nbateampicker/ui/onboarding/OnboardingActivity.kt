package com.nbateampicker.ui.onboarding

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nbateampicker.R
import com.nbateampicker.VhealthApp
import com.nbateampicker.data.entity.Status
import com.nbateampicker.data.entity.TeamDetail
import com.nbateampicker.receiver.ConnectionLiveData
import com.nbateampicker.ui.BaseActivity
import com.nbateampicker.viewmodel.TeamDetailsViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.onboarding.*
import javax.inject.Inject


class OnboardingActivity : BaseActivity(), HasAndroidInjector {

    var nbaTeamList: MutableList<TeamDetail> = ArrayList()
    var nbaTeamDisplayList: MutableList<String> = ArrayList()

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: TeamDetailsViewModel by viewModels {
        viewModelFactory
    }

    var nbaTeamNameList: MutableList<String?> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.onboarding)

//        val binding = DataBindingUtil.inflate<OnboardingBinding>(
//            LayoutInflater.from(this), R.layout.onboarding, null, false
//        )

        networkCheck()

        initServiceCall()
        if(VhealthApp.isOnline)
            viewModel.setTeamIdValue(null)

        nbaSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                tvTeamNameTxt.text = nbaTeamList[position].full_name
                tvTeamCityTxt.text = nbaTeamList[position].city
                tvTeamConferenceTxt.text = nbaTeamList[position].conference
                tvTeamDivisionTxt.text = nbaTeamList[position].division
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

    }

    private fun initSpinner() {
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            nbaTeamNameList!!
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        nbaSpinner.adapter = adapter
    }

    private fun initServiceCall(){
        viewModel.getNBATeamData.observe(
            this@OnboardingActivity,
            Observer {
                when (it.status) {
                    Status.LOADING -> {
                        displayProgress.visibility = View.VISIBLE
                        detailLayout.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        displayProgress.visibility = View.GONE
                        detailLayout.visibility = View.VISIBLE
                        Log.e("Response","SUCCESS")
                        Log.e("Response",""+ it.data?.data?.get(1)?.name)
                        nbaTeamList.clear()
                        nbaTeamList = it.data?.data!!
                        for((position, nbaList) in nbaTeamList.withIndex()){
                            nbaTeamNameList.add(nbaTeamList[position].full_name)
                        }
                        initSpinner()
                    }
                    Status.ERROR -> {
                        displayProgress.visibility = View.GONE
                        detailLayout.visibility = View.VISIBLE
                        Log.e("Response","ERROR")
                        Log.e("Response",""+it.error?.httpMessage)

                        Toast.makeText(this@OnboardingActivity,
                            "Error: " +it.error?.httpMessage, Toast.LENGTH_SHORT).show()

                    }
                }
            })
    }

    private fun networkCheck() {
        // for network check
        val connectionLiveData = ConnectionLiveData(applicationContext)
        connectionLiveData.observe(this, Observer {
            VhealthApp.isOnline = it
        })
    }
    override fun onResume() {
        super.onResume()
    }
}
