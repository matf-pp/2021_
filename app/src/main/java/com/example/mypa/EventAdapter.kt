package com.example.mypa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mypa.databinding.EventBinding

class EventAdapter(private var myDB: MyDBHelper, private var events: MutableList<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {


    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventBinding = EventBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.EventViewHolder {
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

    fun delete(date:String){
        myDB.deleteEvent()
        events = myDB.getAllEvents(date)
        this.setTasks(events)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
            var dogadjaj : Event = events[position]
            holder.eventBinding.tvEvent.setText(dogadjaj.event)
            holder.eventBinding.imgDelete.setOnClickListener {
                dogadjaj.isChecked = 1
                myDB.updateEvent(dogadjaj.id,dogadjaj.isChecked)
            }
    }

    override fun getItemCount(): Int {
       return events.size
    }
}