package com.didi.core.repository.team

class TeamRepository constructor(private val teamDataSource: TeamDataSource) {

    suspend fun getTeamsFromLeague(leagueId: Int) =
        teamDataSource.getTeamsFromLeague(leagueId)

    suspend fun getTeam(teamId: Int) =
        teamDataSource.getTeam(teamId)
}