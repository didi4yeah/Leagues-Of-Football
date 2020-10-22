package com.didi.lof.presentation.presenter

import com.didi.core.data.Team
import com.didi.core.repository.Outcome
import com.didi.core.repository.TeamRepositoryError
import com.didi.lof.CoroutineTestRule
import com.didi.lof.R
import com.didi.lof.framework.usecase.TeamUseCases
import com.didi.lof.presentation.view.TeamsView
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TeamsPresenterImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    lateinit var view: TeamsView

    @MockK
    lateinit var teamUseCases: TeamUseCases

    @InjectMockKs
    lateinit var presenter: TeamsPresenterImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun presentTeams() {
        val leagueId = 4334

        coEvery { teamUseCases.getTeamsFromLeague(leagueId) } returns
                Outcome.Success(
                    listOf(
                        Team(
                            teamId = 133713,
                            name = "Lyon",
                            logoPicture = "logo"
                        ),
                        Team(
                            teamId = 133713,
                            name = "Lyon",
                            logoPicture = "logo"
                        )
                    )
                )

        runBlocking { presenter.presentTeams(leagueId) }

        coVerify {
            view.displayTeams(
                listOf(sampleTeamsListItemViewModel(), sampleTeamsListItemViewModel())
            )
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun presentTeams_whenError_shouldDisplayError() {
        val leagueId = 4334

        coEvery { teamUseCases.getTeamsFromLeague(leagueId) } returns
                Outcome.Error(TeamRepositoryError.UnknownError)

        runBlocking { presenter.presentTeams(leagueId) }

        coVerify {
            view.displayError(
                R.string.error_network_service
            )
        }
    }
}