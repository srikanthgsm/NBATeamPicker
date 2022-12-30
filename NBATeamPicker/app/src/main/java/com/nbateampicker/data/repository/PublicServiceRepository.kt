package com.nbateampicker.data.repository

import androidx.lifecycle.LiveData
import com.nbateampicker.data.api.PublicService
import com.nbateampicker.data.entity.NBATeamListData
import com.nbateampicker.data.entity.Resource
import javax.inject.Inject

class PublicServiceRepository @Inject constructor(
    private val service: PublicService
) {

    fun getNBATeamRepo(): LiveData<Resource<NBATeamListData>> {
        return object : NetworkBoundResource<NBATeamListData>() {
            override fun createCall() = service.getNBATeamAPI()
        }.asLiveData()
    }
}