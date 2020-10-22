package com.didi.lof.framework.usecase

import com.didi.core.usecase.team.GetTeam
import com.didi.core.usecase.team.GetTeamsFromLeague

data class TeamUseCases(
    val getTeamsFromLeague: GetTeamsFromLeague,
    val getTeam: GetTeam
)