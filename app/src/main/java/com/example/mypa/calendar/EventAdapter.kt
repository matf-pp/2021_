package com.example.mypa.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mypa.MyDBHelper
import com.example.mypa.R
import com.example.mypa.databinding.EventBinding


class EventAdapter(private var myDB: MyDBHelper, private var events: MutableList<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {


    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventBinding = EventBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.event, parent, false
                )
        )
    }

    fun setTasks(list : MutableList<Event>){
        events = list
        notifyDataSetChanged()
    }

    fun getTasks(date:String): MutableList<Event>{
        val pom = myDB.getAllEvents(date)
        return pom
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val dogadjaj : Event = events[position]
        holder.eventBinding.tvEvent.setText(dogadjaj.event)

        holder.eventBinding.imgDelete.setOnClickListener {

            val date = dogadjaj.date
            myDB.deleteEvent(dogadjaj)
            events=myDB.getAllEvents(date)
            notifyDataSetChanged()

        }
    }

    override fun getItemCount(): Int {
       return events.size
    }
}