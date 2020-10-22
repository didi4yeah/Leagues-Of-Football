package com.didi.core.repository.league

class LeagueRepository(private val leagueDataSource: LeagueDataSource) {

    suspend fun getAllLeagues(sportName: String)
            = leagueDataSource.getAllLeagues(sportName)
}