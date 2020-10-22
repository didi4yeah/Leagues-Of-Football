package com.didi.lof.presentation.presenter

import com.didi.core.data.TeamDetails
import com.didi.core.repository.Outcome
import com.didi.core.repository.team.TeamRepositoryError
import com.didi.lof.R
import com.didi.lof.framework.usecase.TeamUseCases
import com.didi.lof.presentation.presenter.contract.TeamDetailsPresenter
import com.didi.lof.presentation.view.contract.TeamDetailsView
import com.didi.lof.presentation.view.viewmodel.TeamDetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamDetailsPresenterImpl @Inject constructor(
    private val view: TeamDetailsView,
    private val teamUseCases: TeamUseCases,
) : TeamDetailsPresenter {

    private val job = Job()

    override fun presentTeam(teamId: Int) {
        val scopeIO = CoroutineScope(job + Dispatchers.IO)
        scopeIO.launch {
            val outcome = teamUseCases.getTeam(teamId)
            handleOutcome(outcome)
        }
    }

    private fun handleOutcome(outcome: Outcome<TeamDetails, TeamRepositoryError>) {
        val scopeMain = CoroutineScope(job + Dispatchers.Main)
        when (outcome) {
            is Outcome.Success -> scopeMain.launch {
                with(outcome.data) {
                    view.displayTeam(
                        TeamDetailsViewModel(
                            name = this.name,
                            description = this.description,
                            //Example for string manipulation in presenter
                            leagueCountryName = this.leagueName.plus(" - ").plus(this.countryName),
                            bannerPicture = this.bannerPicture
                        )
                    )
                }
            }
            is Outcome.Error -> scopeMain.launch {
                view.displayError(
                    when (outcome.error) {
                        TeamRepositoryError.UnknownError -> R.string.error_network_service
                        TeamRepositoryError.NoTeamError -> R.string.error_no_team
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
    }
}