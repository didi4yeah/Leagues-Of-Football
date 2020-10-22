package com.didi.core.usecase.team

import com.didi.core.repository.team.TeamRepository

class GetTeamsFromLeague(private val teamRepository: TeamRepository) {
    suspend operator fun invoke(leagueId: Int) = teamRepository.getTeamsFromLeague(leagueId)
}