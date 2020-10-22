package com.didi.lof.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.didi.lof.R
import com.didi.lof.presentation.presenter.TeamsPresenterImpl
import com.didi.lof.presentation.view.viewmodel.TeamsItemViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_teams.*

class TeamsActivity : AppCompatActivity(), TeamsView {

    private val teamsPresenter = TeamsPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)

        initTeams(4334) //French league
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
        Log.d("DEBUG", "size => $teamsViewModel.size")
    }

    override fun displayError(errorRes: Int) {
        Snackbar.make(teamsFrameLayout, errorRes, Snackbar.LENGTH_LONG).show()
        hideLoading()
    }
}