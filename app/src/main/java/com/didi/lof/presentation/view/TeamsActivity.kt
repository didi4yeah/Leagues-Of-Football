package com.didi.lof.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.didi.lof.R
import kotlinx.android.synthetic.main.activity_teams.*

class TeamsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)
    }

    private fun initTeams(leagueId: Int) {
        displayLoading()
    }

    private fun displayLoading() {
        teamsProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        teamsProgressBar.visibility = View.GONE
    }
}