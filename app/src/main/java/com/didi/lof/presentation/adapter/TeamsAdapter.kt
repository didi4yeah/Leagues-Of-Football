package com.didi.lof.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.didi.lof.R
import com.didi.lof.presentation.view.viewmodel.TeamsItemViewModel
import kotlinx.android.synthetic.main.item_team.view.*

class TeamsAdapter(
    private val teamsViewModel: List<TeamsItemViewModel>,
    private val listener: ListListener
) : RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {

    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TeamViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_team, parent, false)
        )

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val teamViewModel = teamsViewModel[position]
        holder.itemView.apply {
            teamViewModel.logoPicture?.let {
                logoImageView.load(it) {
                    placeholder(R.drawable.ic_football)
                    error(R.drawable.ic_football)
                }
            } ?: logoImageView.load(R.drawable.ic_football)
            setOnClickListener { listener.onItemTeamClick(teamViewModel.teamId) }
        }
    }

    override fun getItemCount(): Int = teamsViewModel.size
}