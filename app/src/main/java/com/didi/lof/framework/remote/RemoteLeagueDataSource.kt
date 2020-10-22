package com.didi.lof.framework.remote

import com.didi.core.data.League
import com.didi.core.repository.Outcome
import com.didi.core.repository.league.LeagueDataSource
import com.didi.core.repository.league.LeagueRepositoryError
import com.didi.lof.framework.remote.data.toLeague

class RemoteLeagueDataSource(private val leagueService: LeagueService) : LeagueDataSource {

    override suspend fun getAllLeagues(sportName: String?): Outcome<List<League>, LeagueRepositoryError> {
        val leagues: List<League>
        try {
            leagues = leagueService
                .getAllLeagues()
                .leagues
                .map { it.toLeague() }
        } catch (e: Exception) {
            return Outcome.Error(LeagueRepositoryError.UnknownError)
        }
        return Outcome.Success(leagues)
    }
}