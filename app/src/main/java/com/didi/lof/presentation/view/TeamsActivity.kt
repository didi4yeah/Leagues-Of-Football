package com.didi.lof.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.didi.lof.R
import com.didi.lof.presentation.adapter.LeagueArrayAdapter
import com.didi.lof.presentation.adapter.ListListener
import com.didi.lof.presentation.adapter.TeamsAdapter
import com.didi.lof.presentation.presenter.contract.LeaguePresenter
import com.didi.lof.presentation.presenter.contract.TeamsPresenter
import com.didi.lof.presentation.view.contract.LeaguesView
import com.didi.lof.presentation.view.contract.TeamsView
import com.didi.lof.presentation.view.viewmodel.LeagueViewModel
import com.didi.lof.presentation.view.viewmodel.TeamsItemViewModel
import com.didi.lof.utils.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_teams.*
import javax.inject.Inject

@AndroidEntryPoint
class TeamsActivity : AppCompatActivity(), TeamsView, LeaguesView, ListListener {

    companion object IntentExtra {
        private const val EXTRA_TEAM_ID = "TEAM_ID"

        var Intent.teamId: Int
            get() = getIntExtra(EXTRA_TEAM_ID, 0)
            set(teamId) {
                putExtra(EXTRA_TEAM_ID, teamId)
            }

        const val SPORT_TYPE = "Soccer" //Since we are Leagues Of Football but easy to change sport (i.e. Basketball)
    }

    @Inject
    lateinit var teamsPresenter: TeamsPresenter

    @Inject
    lateinit var leaguePresenter: LeaguePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)

        initSearch()
    }

    override fun onDestroy() {
        super.onDestroy()
        teamsPresenter.onDestroy()
        leaguePresenter.onDestroy()
    }

    private fun initSearch() {
        leaguePresenter.presentLeagues(SPORT_TYPE)

        teamsAutoCompleteTextView.apply {
            threshold = 2

            setOnItemClickListener { parent, _, position, _ ->
                val item = parent.getItemAtPosition(position) as LeagueViewModel
                initTeams(item.leagueId)
            }
        }
    }

    private fun initTeams(leagueId: Int) {
        hideKeyboard()
        displayLoading()
        teamsPresenter.presentTeams(leagueId)
    }

    private fun displayLoading() {
        teamsProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        teamsProgressBar.visibility = View.GONE
    }

    override fun displayTeams(teamsViewModel: List<TeamsItemViewModel>) {
        hideLoading()
        teamsRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@TeamsActivity, 2)
            adapter = TeamsAdapter(teamsViewModel, this@TeamsActivity)
        }
    }

    override fun displayLeagues(leagues: List<LeagueViewModel>) {
        teamsAutoCompleteTextView.setAdapter(
            LeagueArrayAdapter(
                this, android.R.layout.simple_dropdown_item_1line, leagues
            )
        )
    }

    override fun displayError(errorRes: Int) {
        Snackbar.make(teamsFrameLayout, errorRes, Snackbar.LENGTH_LONG).show()
        hideLoading()
    }

    override fun onItemTeamClick(itemId: Int) {
        Intent(this, TeamDetailsActivity::class.java)
            .apply { teamId = itemId }
            .also { startActivity(it) }
    }
}