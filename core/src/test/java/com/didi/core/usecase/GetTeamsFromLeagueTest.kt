package com.didi.core.usecase

import com.didi.core.repository.Outcome
import com.didi.core.repository.TeamDataSource
import com.didi.core.repository.TeamFixtures
import com.didi.core.repository.TeamRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTeamsFromLeagueTest {

    lateinit var teamDataSource: TeamDataSource

    lateinit var repository: TeamRepository

    @Before
    fun setUp() {
        teamDataSource = mockk()
        repository = TeamRepository(teamDataSource)
    }

    @Test
    fun `getTeamFromLeague UseCase is invoked Then should call GetTeamsFromLeague from repo`() {
        val leagueId = 4334

        coEvery { GetTeamsFromLeague(repository).invoke(leagueId) } returns
                Outcome.Success(TeamFixtures.sampleTeamList())

        runBlocking { GetTeamsFromLeague(repository).invoke(leagueId) }

        coVerify { repository.getTeamsFromLeague(leagueId) }
    }
}