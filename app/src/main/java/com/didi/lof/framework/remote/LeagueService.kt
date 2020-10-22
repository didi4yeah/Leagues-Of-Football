package com.didi.lof.framework.remote

import com.didi.lof.framework.remote.data.league.LeaguesResponse
import retrofit2.http.GET

interface LeagueService {

    @GET("all_leagues.php")
    suspend fun getAllLeagues(): LeaguesResponse
}