package com.didi.core.usecase.team

import com.didi.core.repository.team.TeamRepository

class GetTeam(private val teamRepository: TeamRepository) {
    suspend operator fun invoke(teamId: Int) = teamRepository.getTeam(teamId)
}