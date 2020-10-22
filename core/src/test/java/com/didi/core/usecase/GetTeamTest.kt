package com.didi.core.usecase

import com.didi.core.repository.Outcome
import com.didi.core.repository.TeamDataSource
import com.didi.core.repository.TeamFixtures.Companion.sampleTeamDetails
import com.didi.core.repository.TeamRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTeamTest {

    @MockK
    lateinit var teamDataSource: TeamDataSource

    @InjectMockKs
    lateinit var repository: TeamRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getTeam UseCase is invoked Then should call GetTeam from repo`() {
        val teamId = 133713

        coEvery { GetTeam(repository).invoke(teamId) } returns Outcome.Success(sampleTeamDetails())

        runBlocking { GetTeam(repository).invoke(teamId) }

        coVerify { repository.getTeam(teamId) }
    }
}