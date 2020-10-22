package com.didi.lof.presentation.view

import androidx.annotation.StringRes
import com.didi.lof.presentation.view.viewmodel.TeamsItemViewModel

interface TeamsView {
    fun displayTeams(teamsViewModel: List<TeamsItemViewModel>)
    fun displayError(@StringRes errorRes: Int)
}