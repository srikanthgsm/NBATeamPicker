package com.nbateampicker.data.api

import androidx.lifecycle.LiveData
import com.nbateampicker.data.entity.*
import retrofit2.http.*

/**
 * REST Public API access points
 */
interface PublicService {
    @GET("api/v1/teams/")
    fun getNBATeamAPI(): LiveData<ApiResponse<NBATeamListData>>

    @GET("api/v1/teams/{id}")
    fun getSelectedNBATeamAPI(): LiveData<ApiResponse<NBATeamListData>>

}