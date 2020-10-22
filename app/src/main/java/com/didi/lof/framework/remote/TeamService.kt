package com.didi.lof.framework.remote

import com.didi.lof.framework.remote.data.team.TeamsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamService {

    @GET("lookup_all_teams.php")
    suspend fun getTeamsFromLeague(@Query("id") leagueId: Int): TeamsResponse

    @GET("lookupteam.php")
    suspend fun getTeam(@Query("id") teamId: Int): TeamsResponse
}