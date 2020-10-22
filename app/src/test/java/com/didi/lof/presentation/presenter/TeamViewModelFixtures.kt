package com.didi.lof.presentation.presenter

import com.didi.lof.presentation.view.viewmodel.TeamDetailsViewModel
import com.didi.lof.presentation.view.viewmodel.TeamsItemViewModel

fun sampleTeamsItemViewModel() = TeamsItemViewModel(
    teamId = 133713,
    logoPicture = "logo"
)

fun sampleTeamDetailsViewModel() = TeamDetailsViewModel(
    name = "Lyon",
    description = "Lorem description",
    leagueCountryName = "French Ligue 1 - France",
    bannerPicture = "url"
)