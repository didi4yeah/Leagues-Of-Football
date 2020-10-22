package com.didi.core.repository

import com.didi.core.data.League

class LeagueFixtures {

    companion object {

        private fun sampleSoccerLeague() = League(
            leagueId = 4334,
            name = "French Ligue 1",
            sportType = "Soccer"
        )

        private fun sampleNotSoccerLeague() = League(
            leagueId = 4388,
            name = "NBA G League",
            sportType = "Basketball"
        )

        fun sampleSoccerLeagues() = listOf(
            sampleSoccerLeague(), sampleSoccerLeague()
        )

        fun sampleMultiSportsLeagues() = listOf(
            sampleNotSoccerLeague(), sampleSoccerLeague(), sampleNotSoccerLeague(), sampleSoccerLeague(), sampleSoccerLeague()
        )
    }
}

