package com.didi.lof.presentation.view.contract

import androidx.annotation.StringRes
import com.didi.lof.presentation.view.viewmodel.LeagueViewModel

interface LeaguesView {
    fun displayLeagues(leagues: List<LeagueViewModel>)
    fun displayError(@StringRes errorRes: Int)
}