package com.didi.core.repository

import com.didi.core.data.Team
import com.didi.core.data.TeamDetails

interface TeamDataSource {

    suspend fun getTeamsFromLeague(leagueId: Int): Outcome<List<Team>, TeamRepositoryError>

    suspend fun getTeam(teamId: Int): Outcome<TeamDetails, TeamRepositoryError>
}

sealed class TeamRepositoryError {
    object UnknownError : TeamRepositoryError()
}