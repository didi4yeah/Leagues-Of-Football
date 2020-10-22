package com.didi.core.usecase.league

import com.didi.core.data.League
import com.didi.core.repository.Outcome
import com.didi.core.repository.league.LeagueRepository
import com.didi.core.repository.league.LeagueRepositoryError

class GetAllLeagues(private val leagueRepository: LeagueRepository) {
    suspend operator fun invoke(sportName: String): Outcome<List<League>, LeagueRepositoryError> =
        leagueRepository.getAllLeagues(sportName).let {
            return if (it is Outcome.Success) {
                Outcome.Success(
                    it.data.filter { league -> league.sportType == sportName }
                )
            } else {
                it
            }
        }
}