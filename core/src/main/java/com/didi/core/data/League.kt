package com.didi.core.data

data class League(
    val leagueId: Int,
    val name: String,
    val sportType: String //TODO enum class if not working only with "SOCCER"
)