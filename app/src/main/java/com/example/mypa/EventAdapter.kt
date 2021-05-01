package com.example.mypa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mypa.databinding.EventBinding


class EventAdapter(private var context: Context, private var myDB: MyDBHelper, private var events: MutableList<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {


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

    fun getTasks(date:String): MutableList<Event>{
        val pom = myDB.getAllEvents(date)
        return pom
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        var dogadjaj : Event = events[position]
        holder.eventBinding.tvEvent.setText(dogadjaj.event)

        var ev = events[position]

        holder.eventBinding.imgDelete.setOnClickListener {
            Toast.makeText(it.context,"Satro radi ${position}",Toast.LENGTH_SHORT).show()

            var date = ev.date
            myDB.deleteEvent(ev)
            events=myDB.getAllEvents(date)
            notifyDataSetChanged()

        }
    }

    fun deleteEv(pos:Int){
        var event:Event = events[pos]
        myDB.delete(event.id)
        events=myDB.getAllEvents(event.date)
        notifyItemRemoved(events.indexOf(event))
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return events.size
    }
}