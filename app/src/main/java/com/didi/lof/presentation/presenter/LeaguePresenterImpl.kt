package com.didi.lof.presentation.presenter

import com.didi.core.data.League
import com.didi.core.repository.Outcome
import com.didi.core.repository.league.LeagueRepositoryError
import com.didi.lof.R
import com.didi.lof.framework.usecase.LeagueUseCases
import com.didi.lof.presentation.presenter.contract.LeaguePresenter
import com.didi.lof.presentation.view.contract.LeaguesView
import com.didi.lof.presentation.view.viewmodel.LeagueViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class LeaguePresenterImpl @Inject constructor(
    private val view: LeaguesView,
    private val leagueUseCases: LeagueUseCases,
) : LeaguePresenter {

    private val job = Job()

    override fun presentLeagues(filter: String) {
        val scopeIO = CoroutineScope(job + Dispatchers.IO)
        scopeIO.launch {
            val outcome = leagueUseCases.getAllLeagues(filter)
            handleOutcome(outcome)
        }
    }

    private fun handleOutcome(
        outcome: Outcome<List<League>, LeagueRepositoryError>
    ) {
        val scopeMain = CoroutineScope(job + Dispatchers.Main)
        when (outcome) {
            is Outcome.Success -> scopeMain.launch {
                with(outcome.data) {
                    view.displayLeagues(
                        this.map {
                            LeagueViewModel(
                                leagueId = it.leagueId, name = it.name
                            )
                        }
                    )
                }
            }
            is Outcome.Error -> scopeMain.launch {
                view.displayError(
                    when (outcome.error) {
                        LeagueRepositoryError.UnknownError -> R.string.error_network_service
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
    }
}