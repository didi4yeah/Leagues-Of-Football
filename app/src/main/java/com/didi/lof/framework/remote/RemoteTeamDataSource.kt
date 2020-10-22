package com.didi.lof.framework.remote

import com.didi.core.data.Team
import com.didi.core.data.TeamDetails
import com.didi.core.repository.Outcome
import com.didi.core.repository.team.TeamDataSource
import com.didi.core.repository.team.TeamRepositoryError
import com.didi.lof.framework.remote.data.toTeam
import com.didi.lof.framework.remote.data.toTeamDetails

class RemoteTeamDataSource constructor(
    private val teamService: TeamService
) : TeamDataSource {

    override suspend fun getTeamsFromLeague(leagueId: Int): Outcome<List<Team>, TeamRepositoryError> {
        val teams: List<Team>
        try {
            teams = teamService
                .getTeamsFromLeague(leagueId)
                .teams
                .map { it.toTeam() }
        } catch (e: Exception) {
            return Outcome.Error(TeamRepositoryError.UnknownError)
        }
        return Outcome.Success(teams)
    }

    override suspend fun getTeam(teamId: Int): Outcome<TeamDetails, TeamRepositoryError> {
        val team: TeamDetails
        try {
            team = teamService.getTeam(teamId).teams.firstOrNull()?.toTeamDetails()
                ?: return Outcome.Error(TeamRepositoryError.NoTeamError)
        } catch (e: Exception) {
            return Outcome.Error(TeamRepositoryError.UnknownError)
        }
        return Outcome.Success(team)
    }
}