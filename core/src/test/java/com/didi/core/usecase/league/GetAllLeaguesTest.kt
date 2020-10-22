package com.didi.core.usecase.league

import com.didi.core.repository.LeagueFixtures
import com.didi.core.repository.Outcome
import com.didi.core.repository.league.LeagueDataSource
import com.didi.core.repository.league.LeagueRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetAllLeaguesTest {

    @MockK
    lateinit var leagueDataSource: LeagueDataSource

    @InjectMockKs
    lateinit var repository: LeagueRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getAllLeagues UseCase When Only Soccer leagues Then should same list size`() {
        val sportType = "Soccer"

        coEvery { GetAllLeagues(repository).invoke(sportType) } returns Outcome.Success(LeagueFixtures.sampleSoccerLeagues())

        val outcome = runBlocking { GetAllLeagues(repository).invoke(sportType) }

        Assert.assertTrue((outcome as Outcome.Success).data.size == 2)
    }

    @Test
    fun `getAllLeagues UseCase When Multi Sports leagues Then should reduce list size to Soccer ones`() {
        val sportType = "Soccer"

        coEvery { GetAllLeagues(repository).invoke(sportType) } returns Outcome.Success(LeagueFixtures.sampleMultiSportsLeagues())

        val outcome = runBlocking { GetAllLeagues(repository).invoke(sportType) }

        Assert.assertTrue((outcome as Outcome.Success).data.size == 3)
    }
}