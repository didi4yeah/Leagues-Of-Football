package com.didi.core.repository

import com.didi.core.repository.TeamFixtures.Companion.sampleTeamDetails
import com.didi.core.repository.TeamFixtures.Companion.sampleTeamList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TeamRepositoryTest {

    @MockK
    lateinit var teamDataSource: TeamDataSource

    @InjectMockKs
    lateinit var repository: TeamRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getTeamsFromLeague() {
        val leagueId = 4334

        coEvery { repository.getTeamsFromLeague(leagueId) } returns Outcome.Success(sampleTeamList())

        runBlocking { repository.getTeamsFromLeague(leagueId) }

        coVerify { teamDataSource.getTeamsFromLeague(leagueId) }
    }

    @Test
    fun getTeam() {
        val teamId = 133713

        coEvery { repository.getTeam(teamId) } returns Outcome.Success(sampleTeamDetails())

        runBlocking { repository.getTeam(teamId) }

        coVerify { teamDataSource.getTeam(teamId) }
    }
}