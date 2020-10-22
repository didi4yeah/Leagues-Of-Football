package com.didi.lof.framework.remote.data

import com.didi.core.data.Team
import com.didi.core.data.TeamDetails

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