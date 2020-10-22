package com.didi.lof.presentation.presenter

import com.didi.core.data.Team
import com.didi.core.repository.Outcome
import com.didi.core.repository.TeamRepository
import com.didi.core.repository.TeamRepositoryError
import com.didi.core.usecase.GetTeam
import com.didi.core.usecase.GetTeamsFromLeague
import com.didi.lof.LofApplication
import com.didi.lof.R
import com.didi.lof.framework.remote.RemoteTeamDataSource
import com.didi.lof.framework.remote.TeamService
import com.didi.lof.framework.usecase.TeamUseCases
import com.didi.lof.presentation.view.TeamsView
import com.didi.lof.presentation.view.viewmodel.TeamsItemViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TeamsPresenterImpl constructor(
    private val view: TeamsView
) : TeamsPresenter {

    private val job = Job()
    private val scopeMain = CoroutineScope(job + Dispatchers.Main)
    private val scopeIO = CoroutineScope(job + Dispatchers.IO)

    private val teamService = LofApplication.retrofit.create(TeamService::class.java)

    private val repository = TeamRepository(RemoteTeamDataSource(teamService))

    //TODO Inject
    private val teamUseCases = TeamUseCases(
        GetTeamsFromLeague(repository),
        GetTeam(repository)
    )

    override fun presentTeams(leagueId: Int) {
        scopeIO.launch {
            val outcome = teamUseCases.getTeamsFromLeague(leagueId)
            handleOutcome(outcome)
        }
    }

    private fun handleOutcome(outcome: Outcome<List<Team>, TeamRepositoryError>) {
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