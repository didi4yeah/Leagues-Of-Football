package com.didi.lof.di

import com.didi.lof.framework.remote.LeagueService
import com.didi.lof.framework.remote.RemoteLeagueDataSource
import com.didi.lof.framework.remote.RemoteTeamDataSource
import com.didi.lof.framework.remote.TeamService
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(ActivityComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
        )
        .build()

    @Provides
    fun provideTeamService(retrofit: Retrofit): TeamService =
        retrofit.create(TeamService::class.java)

    @Provides
    fun provideLeagueService(retrofit: Retrofit): LeagueService =
        retrofit.create(LeagueService::class.java)

    @Provides
    fun provideRemoteTeamDataSource(
        teamService: TeamService
    ) = RemoteTeamDataSource(teamService)

    @Provides
    fun provideRemoteLeagueDataSource(
        leagueService: LeagueService
    ) = RemoteLeagueDataSource(leagueService)
}