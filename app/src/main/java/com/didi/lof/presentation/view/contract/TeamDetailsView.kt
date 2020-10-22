package com.didi.lof.presentation.view.contract

import androidx.annotation.StringRes
import com.didi.lof.presentation.view.viewmodel.TeamDetailsViewModel

interface TeamDetailsView {
    fun displayTeam(teamDetailsViewModel: TeamDetailsViewModel)
    fun displayError(@StringRes errorRes: Int)
}