package com.didi.core.repository.league

import com.didi.core.data.League
import com.didi.core.repository.Outcome

interface LeagueDataSource {

    suspend fun getAllLeagues(sportName: String? = null): Outcome<List<League>, LeagueRepositoryError>
}

sealed class LeagueRepositoryError {
    object UnknownError : LeagueRepositoryError()
}