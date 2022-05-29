package com.assignment.demo.starwarsapp.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.assignment.demo.starwarsapp.R
import com.assignment.demo.starwarsapp.db.model.starwarsRoomDataModel
import com.assignment.demo.starwarsapp.home.HomeFragment

class RecentSearchRecyclerViewAdapter(
    private val fragment: Fragment,
    private val starwarsRoomDataModels: List<starwarsRoomDataModel>
) :
    RecyclerView.Adapter<RecentSearchRecyclerViewAdapter.starwarsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentSearchRecyclerViewAdapter.starwarsViewHolder {
        val view: View =
            LayoutInflater.from(fragment.context)
                .inflate(R.layout.row_recent_search, parent, false)
        return starwarsViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: RecentSearchRecyclerViewAdapter.starwarsViewHolder,
        position: Int
    ) {
        val curr = starwarsRoomDataModels[position]
        holder.status.text = """${curr.areaName}, ${curr.country}"""
        holder.layout.setOnClickListener(View.OnClickListener { (fragment as HomeFragment).onRowItemClicked("curr","search") })
    }

    override fun getItemCount(): Int {
        return if (starwarsRoomDataModels != null && !starwarsRoomDataModels.isNullOrEmpty())
            starwarsRoomDataModels.size
        else
            0;
    }


    class starwarsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var status: TextView = itemView.findViewById(R.id.tvStarName)
        var layout: ConstraintLayout = itemView.findViewById(R.id.const_detials)



    }
}