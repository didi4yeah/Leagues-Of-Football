package com.didi.lof.framework.usecase

import com.didi.core.usecase.GetTeam
import com.didi.core.usecase.GetTeamsFromLeague

data class TeamUseCases(
    val getTeamsFromLeague: GetTeamsFromLeague,
    val getTeam: GetTeam
)