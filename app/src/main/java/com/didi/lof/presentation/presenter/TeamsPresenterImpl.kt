package com.didi.lof.presentation.presenter

import android.util.Log
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class TeamsPresenterImpl constructor(
    private val view: TeamsView
) : TeamsPresenter {

    private val job = Job()
    private val scopeMain = CoroutineScope(job + Dispatchers.Main)
    private val scopeIO = CoroutineScope(job + Dispatchers.IO)

    private var teamService: TeamService = LofApplication.retrofit.create(TeamService::class.java)

    private val repository = TeamRepository(RemoteTeamDataSource(teamService))

    //TODO Inject
    private val teamUseCases = TeamUseCases(
        GetTeamsFromLeague(repository),
        GetTeam(repository)
    )

    override fun presentTeams(leagueId: Int) {
        scopeIO.launch {
            val outcome = teamUseCases.getTeamsFromLeague(leagueId)
            Log.d("DEBUG","outcome => $outcome")
        }
    }


    override fun onDestroy() {
        job.cancel()
    }
}