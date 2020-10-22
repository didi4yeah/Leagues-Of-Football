package com.didi.core.usecase

import com.didi.core.repository.TeamRepository

class GetTeam(private val teamRepository: TeamRepository) {
    suspend operator fun invoke(teamId: Int) = teamRepository.getTeam(teamId)
}