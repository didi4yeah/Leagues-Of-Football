package com.didi.lof.di

import com.didi.core.repository.league.LeagueDataSource
import com.didi.core.repository.league.LeagueRepository
import com.didi.core.usecase.league.GetAllLeagues
import com.didi.lof.framework.remote.RemoteLeagueDataSource
import com.didi.lof.framework.usecase.LeagueUseCases
import com.didi.lof.presentation.presenter.LeaguePresenterImpl
import com.didi.lof.presentation.presenter.contract.LeaguePresenter
import com.didi.lof.presentation.view.TeamsActivity
import com.didi.lof.presentation.view.contract.LeaguesView
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class LeagueModule {

    @Binds
    abstract fun bindTeamsActivity(activity: TeamsActivity): LeaguesView

    @Binds
    abstract fun bindLeaguePresenter(leaguePresenterImpl: LeaguePresenterImpl): LeaguePresenter

    @Binds
    abstract fun bindLeagueDataSource(remoteLeagueDataSource: RemoteLeagueDataSource): LeagueDataSource
}

@InstallIn(ActivityComponent::class)
@Module
object LeaguePresentationModule {

    @Provides
    fun provideLeaguePresenterImpl(
        view: LeaguesView,
        leagueUseCases: LeagueUseCases
    ) = LeaguePresenterImpl(view, leagueUseCases)
}


@Module
@InstallIn(ActivityComponent::class)
object LeagueRepositoryModule {
    @Provides
    fun provideLeagueRepository(
        leagueDataSource: LeagueDataSource
    ) = LeagueRepository(leagueDataSource)

    @Provides
    fun provideLeagueUseCases(repository: LeagueRepository) = LeagueUseCases(
        GetAllLeagues(repository)
    )
}