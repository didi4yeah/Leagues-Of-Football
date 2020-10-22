package com.didi.lof.framework.remote

import com.didi.core.data.Team
import com.didi.core.data.TeamDetails
import com.didi.core.repository.Outcome
import com.didi.core.repository.TeamRepositoryError
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RemoteTeamDataSourceTest {

    @MockK
    private lateinit var teamDataSource: RemoteTeamDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getTeamsFromLeague_isFine() {
        val leagueId = 4334

        val expectedList = listOf(
            Team(
                teamId = 133713,
                name = "Lyon",
                logoPicture = "https://www.thesportsdb.com/images/media/team/logo/qutsut1420580047.png"
            ),
            Team(
                teamId = 133713,
                name = "Lyon",
                logoPicture = "https://www.thesportsdb.com/images/media/team/logo/qutsut1420580047.png"
            ),
            Team(
                teamId = 133713,
                name = "Lyon",
                logoPicture = "https://www.thesportsdb.com/images/media/team/logo/qutsut1420580047.png"
            )
        )

        coEvery { teamDataSource.getTeamsFromLeague(leagueId) } returns
                Outcome.Success(TeamFrameworkFixtures.sampleTeamList())

        val outcome = runBlocking { teamDataSource.getTeamsFromLeague(leagueId) }

        Assert.assertEquals(Outcome.Success(expectedList), outcome)
    }

    @Test
    fun `getTeamsFromLeague When wrong leagueId Should return error`() {
        val leagueId = -42

        coEvery { teamDataSource.getTeamsFromLeague(leagueId) } returns
                Outcome.Error(TeamRepositoryError.NoTeamError)

        val outcome = runBlocking { teamDataSource.getTeamsFromLeague(leagueId) }

        Assert.assertEquals(Outcome.Error(TeamRepositoryError.NoTeamError), outcome)
    }

    @Test
    fun getTeam_isFine() {
        val teamId = 133713

        val expectedTeam = TeamDetails(
            teamId = 133713,
            name = "Lyon",
            logoPicture = "https://www.thesportsdb.com/images/media/team/logo/qutsut1420580047.png",
            countryName = "France",
            description = "Lorem description",
            leagueName = "French Ligue 1",
            stadiumName = "Groupama Stadium",
            bannerPicture = "https://www.thesportsdb.com/images/media/team/banner/xwsrtu1420583914.jpg"
        )

        coEvery { teamDataSource.getTeam(teamId) } returns
                Outcome.Success(TeamFrameworkFixtures.sampleTeamDetails())

        val outcome = runBlocking { teamDataSource.getTeam(teamId) }

        Assert.assertEquals(Outcome.Success(expectedTeam), outcome)
    }

    @Test
    fun `getTeam When wrong teamId Should return error`() {
        val teamId = -42

        coEvery { teamDataSource.getTeam(teamId) } returns
                Outcome.Error(TeamRepositoryError.NoTeamError)

        val outcome = runBlocking { teamDataSource.getTeam(teamId) }

        Assert.assertEquals(Outcome.Error(TeamRepositoryError.NoTeamError), outcome)
    }
}