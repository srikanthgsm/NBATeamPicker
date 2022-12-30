package com.nbateampicker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.nbateampicker.VhealthApp
import com.nbateampicker.data.entity.*
import com.nbateampicker.data.repository.PublicServiceRepository
import com.nbateampicker.util.AbsentLiveData
import javax.inject.Inject

class TeamDetailsViewModel
@Inject constructor(
    pubRepo: PublicServiceRepository
) :
    ViewModel() {
    private val _nbaData = MutableLiveData<String>()

    val getNBATeamData: LiveData<Resource<NBATeamListData>> = _nbaData.switchMap {
        if (_nbaData == null) {
            AbsentLiveData.create()
        } else {
            pubRepo.getNBATeamRepo()
        }
    }

    fun setTeamIdValue(teamId : String?)
    {
        _nbaData.value = teamId
    }
}