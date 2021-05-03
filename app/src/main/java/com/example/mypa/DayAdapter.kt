package com.example.mypa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mypa.databinding.OneactivityitemBinding
import java.util.*

class DayAdapter(private var myDB: MyDBHelper,private var days:MutableList<Day>) : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {

    class DayViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding=OneactivityitemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.oneactivityitem,parent,false)
        )
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.binding.tvAct.setText(days[position].title)
        holder.binding.tvTime.setText(days[position].time)

       holder.binding.dayDel.setOnClickListener{
            myDB.deleteDay(days[position])
            days = myDB.getAllOnDay()
           // days.sortBy { it.time }
            notifyDataSetChanged()
        }

    }

    fun setActivities(){
        days=myDB.getAllOnDay()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return days.size
    }

}