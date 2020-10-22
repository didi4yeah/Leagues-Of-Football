package com.didi.lof.presentation.presenter

import com.didi.core.data.Team
import com.didi.core.repository.Outcome
import com.didi.core.repository.team.TeamRepositoryError
import com.didi.lof.R
import com.didi.lof.framework.usecase.TeamUseCases
import com.didi.lof.presentation.presenter.contract.TeamsPresenter
import com.didi.lof.presentation.view.contract.TeamsView
import com.didi.lof.presentation.view.viewmodel.TeamsItemViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamsPresenterImpl @Inject constructor(
    private val view: TeamsView,
    private val teamUseCases: TeamUseCases
) : TeamsPresenter {

    private val job = Job()

    override fun presentTeams(leagueId: Int) {
        val scopeIO = CoroutineScope(job + Dispatchers.IO)
        scopeIO.launch {
            val outcome = teamUseCases.getTeamsFromLeague(leagueId)
            handleOutcome(outcome)
        }
    }

    private fun handleOutcome(outcome: Outcome<List<Team>, TeamRepositoryError>) {
        val scopeMain = CoroutineScope(job + Dispatchers.Main)
        when (outcome) {
            is Outcome.Success -> scopeMain.launch {
                with(outcome.data) {
                    val teamsViewModel = this
                        .sortedBy { it.name }
                        .map {
                            TeamsItemViewModel(
                                teamId = it.teamId,
                                logoPicture = it.logoPicture
                            )
                        }
                    view.displayTeams(teamsViewModel)
                }
            }
            is Outcome.Error -> scopeMain.launch {
                view.displayError(
                    when (outcome.error) {
                        //TODO BETTER to inject context.getResources() to use it
                        // (res.getString(R.string.xxx)) here since presentation
                        TeamRepositoryError.UnknownError -> R.string.error_network_service
                        TeamRepositoryError.NoTeamError -> R.string.error_network_service
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
    }
}