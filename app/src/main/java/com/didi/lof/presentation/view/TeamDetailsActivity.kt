package com.didi.lof.presentation.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.didi.lof.R
import com.didi.lof.presentation.presenter.contract.TeamDetailsPresenter
import com.didi.lof.presentation.view.TeamsActivity.IntentExtra.teamId
import com.didi.lof.presentation.view.contract.TeamDetailsView
import com.didi.lof.presentation.view.viewmodel.TeamDetailsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_team_details.*
import javax.inject.Inject

@AndroidEntryPoint
class TeamDetailsActivity : AppCompatActivity(), TeamDetailsView {

    @Inject
    lateinit var presenter: TeamDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)

        initToolbar()
        initTeam(intent.teamId)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun displayLoading() {
        teamProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        teamProgressBar.visibility = View.GONE
    }

    private fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    private fun initTeam(teamId: Int) {
        displayLoading()
        presenter.presentTeam(teamId)
    }

    override fun displayTeam(teamDetailsViewModel: TeamDetailsViewModel) {
        hideLoading()
        title = teamDetailsViewModel.name
        teamLeagueTextView.text = teamDetailsViewModel.leagueCountryName
        teamDescTextView.text = teamDetailsViewModel.description
        bannerImageView.load(teamDetailsViewModel.bannerPicture)
    }

    override fun displayError(@StringRes errorRes: Int) {
        Snackbar.make(teamConstraintLayout, errorRes, Snackbar.LENGTH_LONG).show()
        hideLoading()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}