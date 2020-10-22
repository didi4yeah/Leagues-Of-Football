package com.didi.lof.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.didi.lof.R
import com.didi.lof.presentation.adapter.ListListener
import com.didi.lof.presentation.adapter.TeamsAdapter
import com.didi.lof.presentation.presenter.contract.TeamsPresenter
import com.didi.lof.presentation.view.contract.TeamsView
import com.didi.lof.presentation.view.viewmodel.TeamsItemViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_teams.*
import javax.inject.Inject

@AndroidEntryPoint
class TeamsActivity : AppCompatActivity(), TeamsView, ListListener {

    companion object IntentExtra {
        private const val EXTRA_TEAM_ID = "TEAM_ID"

        var Intent.teamId: Int
            get() = getIntExtra(EXTRA_TEAM_ID, 0)
            set(teamId) {
                putExtra(EXTRA_TEAM_ID, teamId)
            }
    }

    @Inject
    lateinit var teamsPresenter: TeamsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)

        initTeams(4334) //French league
    }

    override fun onDestroy() {
        super.onDestroy()
        teamsPresenter.onDestroy()
    }

    private fun initTeams(leagueId: Int) {
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