package com.didi.lof.di

import android.app.Activity
import com.didi.core.repository.TeamDataSource
import com.didi.core.repository.TeamRepository
import com.didi.core.usecase.GetTeam
import com.didi.core.usecase.GetTeamsFromLeague
import com.didi.lof.framework.remote.RemoteTeamDataSource
import com.didi.lof.framework.usecase.TeamUseCases
import com.didi.lof.presentation.presenter.TeamsPresenter
import com.didi.lof.presentation.presenter.TeamsPresenterImpl
import com.didi.lof.presentation.view.TeamsActivity
import com.didi.lof.presentation.view.TeamsView
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class TeamsBindingModule {

    @Binds
    abstract fun bindTeamsActivity(activity: TeamsActivity): TeamsView

    @Binds
    abstract fun bindTeamsPresenter(teamsPresenterImpl: TeamsPresenterImpl): TeamsPresenter

    @Binds
    abstract fun bindTeamDataSource(remoteTeamDataSource: RemoteTeamDataSource): TeamDataSource
}

@InstallIn(ActivityComponent::class)
@Module
object TeamsPresentationModule {

    @Provides
    fun provideActivity(activity: Activity): TeamsActivity {
        return activity as TeamsActivity
    }

    @Provides
    fun provideTeamsPresenterImpl(
        view: TeamsView,
        teamUseCases: TeamUseCases
    ) = TeamsPresenterImpl(view, teamUseCases)
}

@Module
@InstallIn(ActivityComponent::class)
object TeamRepositoryModule {
    @Provides
    fun provideTeamRepository(
        teamDataSource: TeamDataSource
    ) = TeamRepository(teamDataSource)

    @Provides
    fun provideTeamUseCases(repository: TeamRepository) = TeamUseCases(
        GetTeamsFromLeague(repository),
        GetTeam(repository)
    )
}