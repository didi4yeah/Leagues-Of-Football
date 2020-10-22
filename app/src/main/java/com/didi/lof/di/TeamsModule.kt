package com.didi.lof.di

import android.app.Activity
import com.didi.core.repository.team.TeamDataSource
import com.didi.core.repository.team.TeamRepository
import com.didi.core.usecase.team.GetTeam
import com.didi.core.usecase.team.GetTeamsFromLeague
import com.didi.lof.framework.remote.RemoteTeamDataSource
import com.didi.lof.framework.usecase.TeamUseCases
import com.didi.lof.presentation.presenter.TeamDetailsPresenterImpl
import com.didi.lof.presentation.presenter.TeamsPresenterImpl
import com.didi.lof.presentation.presenter.contract.TeamDetailsPresenter
import com.didi.lof.presentation.presenter.contract.TeamsPresenter
import com.didi.lof.presentation.view.TeamDetailsActivity
import com.didi.lof.presentation.view.TeamsActivity
import com.didi.lof.presentation.view.contract.TeamDetailsView
import com.didi.lof.presentation.view.contract.TeamsView
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

@Module
@InstallIn(ActivityComponent::class)
abstract class TeamDetailsBindingModule {

    @Binds
    abstract fun bindTeamDetailsActivity(activity: TeamDetailsActivity): TeamDetailsView

    @Binds
    abstract fun bindTeamDetailsPresenter(teamDetailsPresenterImpl: TeamDetailsPresenterImpl): TeamDetailsPresenter
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

@InstallIn(ActivityComponent::class)
@Module
object TeamDetailsPresentationModule {

    @Provides
    fun provideActivity(activity: Activity): TeamDetailsActivity {
        return activity as TeamDetailsActivity
    }

    @Provides
    fun provideTeamDetailsPresenterImpl(
        view: TeamDetailsView,
        teamUseCases: TeamUseCases
    ) = TeamDetailsPresenterImpl(view, teamUseCases)
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