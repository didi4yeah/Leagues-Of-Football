package com.didi.lof.presentation.presenter.contract

interface LeaguePresenter {
    fun presentLeagues(filter: String)
    fun onDestroy()
}