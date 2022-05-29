package com.assignment.demo.sampleapp.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.assignment.demo.sampleapp.R
import com.assignment.demo.sampleapp.datamodel.peoples.RandomResults
import com.assignment.demo.sampleapp.home.HomeFragment
import java.util.*

class PeopleListRecyclerViewAdapter(
    private val fragment: Fragment,
    private val peopleList: List<RandomResults>
) :
    RecyclerView.Adapter<PeopleListRecyclerViewAdapter.peopleListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PeopleListRecyclerViewAdapter.peopleListViewHolder {
        val view: View =
            LayoutInflater.from(fragment.context)
                .inflate(R.layout.row_recent_search, parent, false)
        return peopleListViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: PeopleListRecyclerViewAdapter.peopleListViewHolder,
        position: Int
    ) {
        val curr = peopleList[position]
        holder.name.text = "Name: ${curr.name.first}"
        holder.gender.text = "Gender: ${curr.gender.toUpperCase(Locale.getDefault())}"
//        holder.birthyear.text = "BirthYear: ${curr.birth_year.toUpperCase(Locale.getDefault())}"

        holder.layout.setOnClickListener(View.OnClickListener {
            (fragment as HomeFragment).onRowItemClicked(
                peopleList[position]
            )
        })
    }

    override fun getItemCount(): Int {
        return if (peopleList != null && !peopleList.isNullOrEmpty())
            peopleList.size
        else
            0;
    }

    class peopleListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tvStarName)
        var gender: TextView = itemView.findViewById(R.id.tvStarGender)
        var birthyear: TextView = itemView.findViewById(R.id.tvBirthYear)
        var layout: ConstraintLayout = itemView.findViewById(R.id.const_details)
    }
}