package com.didi.lof.presentation.presenter

import com.didi.core.data.Team
import com.didi.core.repository.Outcome
import com.didi.core.usecase.GetTeamsFromLeague
import com.didi.lof.LofApplication
import com.didi.lof.framework.remote.TeamService
import com.didi.lof.presentation.view.TeamsView
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TeamsPresenterImplTest {

    @MockK
    lateinit var view: TeamsView

    lateinit var teamService: TeamService

    @InjectMockKs
    lateinit var presenter: TeamsPresenterImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        teamService = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TeamService::class.java)
    }

    //WIP test will be easier when injection or not xD
    @Test
    fun presentTeams() {
        val leagueId = 4334

        runBlocking { presenter.presentTeams(leagueId) }

        coVerify {
            view.displayTeams(
                listOf(sampleTeamsListItemViewModel(), sampleTeamsListItemViewModel())
            )
        }
    }
}