package com.example.mypa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mypa.databinding.OneactivityitemBinding

class DayAdapter(private var days:MutableList<Day>) : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {

    class DayViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding=OneactivityitemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.oneactivityitem,parent,false)
        )
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {



    }

    override fun getItemCount(): Int {
        return days.size
    }

}