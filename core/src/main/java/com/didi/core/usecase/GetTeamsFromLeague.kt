package com.didi.core.usecase

import com.didi.core.repository.TeamRepository

class GetTeamsFromLeague(private val teamRepository: TeamRepository) {
    suspend operator fun invoke(leagueId: Int) = teamRepository.getTeamsFromLeague(leagueId)
}