package com.didi.lof.presentation.presenter

import com.didi.core.data.TeamDetails
import com.didi.core.repository.Outcome
import com.didi.core.repository.team.TeamRepositoryError
import com.didi.lof.CoroutineTestRule
import com.didi.lof.R
import com.didi.lof.framework.usecase.TeamUseCases
import com.didi.lof.presentation.view.contract.TeamDetailsView
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

@ExperimentalCoroutinesApi
class TeamDetailsPresenterImplTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    lateinit var view: TeamDetailsView

    @MockK
    lateinit var teamUseCases: TeamUseCases

    @InjectMockKs
    lateinit var presenter: TeamDetailsPresenterImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun presentTeam() {
        val teamId = 133713

        coEvery { teamUseCases.getTeam(teamId) } returns
                Outcome.Success(
                    TeamDetails(
                        teamId = 133713,
                        name = "Lyon",
                        logoPicture = "https://www.thesportsdb.com/images/media/team/logo/qutsut1420580047.png",
                        countryName = "France",
                        description = "Lorem description",
                        leagueName = "French Ligue 1",
                        stadiumName = "Groupama Stadium",
                        bannerPicture = "url"
                    )
                )

        runBlocking { presenter.presentTeam(teamId) }

        coVerify {
            view.displayTeam(
                sampleTeamDetailsViewModel()
            )
        }
    }
    
    @Test
    fun presentTeam_whenError_shouldDisplayError() {
        val teamId = 4334

        coEvery { teamUseCases.getTeam(teamId) } returns
                Outcome.Error(TeamRepositoryError.NoTeamError)

        runBlocking { presenter.presentTeam(teamId) }

        coVerify {
            view.displayError(
                R.string.error_no_team
            )
        }
    }
}