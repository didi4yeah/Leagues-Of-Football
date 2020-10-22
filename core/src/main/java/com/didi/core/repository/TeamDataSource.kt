package com.didi.core.repository

import com.didi.core.data.Team
import com.didi.core.data.TeamDetails

interface TeamDataSource {

    suspend fun getTeamsFromLeague(leagueId: Int): List<Team>

    suspend fun getTeam(teamId: Int): TeamDetails
}