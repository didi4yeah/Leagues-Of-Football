package com.didi.lof.presentation.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.didi.lof.presentation.view.viewmodel.LeagueViewModel
import java.util.*

class LeagueArrayAdapter(
    activityContext: Context,
    @LayoutRes private val layoutResId: Int,
    private val leagues: List<LeagueViewModel>
) : ArrayAdapter<LeagueViewModel>(activityContext, layoutResId, leagues) {

    private var filteredLeagues = leagues.toMutableList()

    override fun getCount(): Int {
        return filteredLeagues.size
    }

    override fun getItem(position: Int): LeagueViewModel {
        return filteredLeagues[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View =
            convertView ?: (context as Activity).layoutInflater.inflate(layoutResId, parent, false)

        val leagueViewModel = getItem(position)
        val name = view.findViewById<View>(android.R.id.text1) as TextView
        name.text = leagueViewModel.name

        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun convertResultToString(resultValue: Any): String {
                return (resultValue as LeagueViewModel).name
            }

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()

                if (constraint != null) {
                    val filtered = leagues
                        .filter {
                            it.name.toLowerCase(Locale.getDefault())
                                .contains(constraint.toString().toLowerCase(Locale.getDefault()))
                        }

                    filterResults.values = filtered
                    filterResults.count = filtered.size
                }

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredLeagues.clear()
                if (results != null && results.count > 0) {
                    filteredLeagues = (results.values as Collection<LeagueViewModel>).toMutableList()
                    notifyDataSetChanged()
                } else if (constraint == null) {
                    filteredLeagues = leagues.toMutableList()
                    notifyDataSetInvalidated()
                }
            }
        }
    }
}