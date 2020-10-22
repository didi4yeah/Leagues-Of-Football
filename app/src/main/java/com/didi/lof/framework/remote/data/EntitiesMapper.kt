package com.didi.lof.framework.remote.data

import com.didi.core.data.League
import com.didi.core.data.Team
import com.didi.core.data.TeamDetails
import com.didi.lof.framework.remote.data.league.LeagueEntity
import com.didi.lof.framework.remote.data.team.TeamEntity

fun TeamEntity.toTeam() = Team(
    teamId = this.idTeam.toInt(),
    name = this.strTeam,
    logoPicture = this.strTeamBadge
)

fun TeamEntity.toTeamDetails() = TeamDetails(
    teamId = this.idTeam.toInt(),
    name = this.strTeam,
    logoPicture = this.strTeamBadge,
    countryName = this.strCountry,
    description = this.strDescriptionEN,
    leagueName = this.strLeague,
    stadiumName = this.strStadium,
    bannerPicture = this.strTeamBanner
)

fun LeagueEntity.toLeague() = League(
    leagueId = this.idLeague.toInt(),
    name = this.strLeague,
    sportType = this.strSport //TODO enum class if not working only with "SOCCER"
)