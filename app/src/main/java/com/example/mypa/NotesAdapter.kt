package com.example.mypa

import android.content.Context
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypa.databinding.ItemTodoBinding
import com.example.mypa.databinding.NoteBinding

//import kotlinx.android.synthetic.main.item_todo.view.*


class NotesAdapter(private var myDB: MyDBHelper, private var notes: MutableList<Note> ) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var noteBinding = NoteBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.note,parent,false
            )
        )

    }

    fun setTasks(list : MutableList<Note>){
        notes = list
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var item: Note = notes[position]
        holder.noteBinding.tvNote.setText(item.title)


        holder.noteBinding.imgDeleteNote.setOnClickListener {
            var newPosition: Int = holder.adapterPosition
            notes.removeAt(newPosition)
            myDB.deleteNote(notes[newPosition].id)
            notes = myDB.getAllNotes()
            notifyItemRemoved(newPosition)
            notifyItemRangeChanged(newPosition, notes.size)
        }

    }



    override fun getItemCount(): Int {
        return notes.size
    }


}