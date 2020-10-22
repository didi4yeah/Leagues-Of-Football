package com.didi.core.data

sealed class TeamSealed(
    open val teamId: Int,
    open val name: String,
    open val logoPicture: String
)

data class Team(
    override val teamId: Int,
    override val name: String,
    override val logoPicture: String
): TeamSealed(teamId, name, logoPicture)

data class TeamDetails(
    override val teamId: Int,
    override val name: String,
    override val logoPicture: String,
    val countryName: String,
    val description: String,
    val leagueName: String,
    val stadiumName: String,
    val bannerPicture: String
): TeamSealed(teamId, name, logoPicture)