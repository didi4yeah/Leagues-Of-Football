package com.didi.lof.framework.remote

import com.didi.core.data.Team
import com.didi.core.data.TeamDetails

class TeamFrameworkFixtures {

    companion object {
        fun sampleTeamDetails() = TeamDetails(
            teamId = 133713,
            name = "Lyon",
            logoPicture = "https://www.thesportsdb.com/images/media/team/logo/qutsut1420580047.png",
            countryName = "France",
            description = "Lorem description",
            leagueName = "French Ligue 1",
            stadiumName = "Groupama Stadium",
            bannerPicture = "https://www.thesportsdb.com/images/media/team/banner/xwsrtu1420583914.jpg"
        )

        private fun sampleTeam() = Team(
            teamId = 133713,
            name = "Lyon",
            logoPicture = "https://www.thesportsdb.com/images/media/team/logo/qutsut1420580047.png"
        )

        fun sampleTeamList() = listOf(sampleTeam(), sampleTeam(), sampleTeam())
    }
}

