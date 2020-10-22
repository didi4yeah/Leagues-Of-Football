package com.didi.lof.presentation.presenter.contract

interface TeamDetailsPresenter {
    fun presentTeam(teamId: Int)
    fun onDestroy()
}