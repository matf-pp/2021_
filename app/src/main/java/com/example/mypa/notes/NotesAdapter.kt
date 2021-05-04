package com.example.mypa.notes
import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.mypa.MyDBHelper
import com.example.mypa.R
import com.example.mypa.databinding.ItemNoteBinding
import kotlin.properties.Delegates

class NotesAdapter(private var myDB: MyDBHelper, private var context: Context, private var notes: MutableList<Note>) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){

    lateinit var lista :MutableList<Note>
    var pos by Delegates.notNull<Int>()
    private var mNote : Note? = null
    var mActivity : NotesActivity = context as NotesActivity

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding= ItemNoteBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.item_note,parent,false
            )
        )
    }

    fun setNotes(list : MutableList<Note>){
        notes = list
        notifyDataSetChanged()
    }

    fun getNum():Int{
        return pos
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var item: Note = notes[position]
        holder.binding.tvNotee.setText(item.title)
        pos = position


        holder.binding.tvNotee.setOnClickListener{
            mNote=item
        }

        holder.binding.btnDelete.setOnClickListener {
            myDB.deleteNote(item)
            notes = myDB.getAllNotes()
            notifyDataSetChanged()
        }

        holder.binding.btnShow.setOnClickListener {

            mActivity.show.isVisible = true
            mNote=notes[position]
            var tv : TextView = mActivity.show.findViewById(R.id.showTvTitl)
            var et : EditText = mActivity.show.findViewById(R.id.showEtPrikaz)
            tv.setText(item.title)
            et.setText(item.notes)

            notifyDataSetChanged()

        }
    }

    fun getNote(): Note?{
        if (mNote!=null)
            return mNote as Note
        else
            return null
    }

    fun updateNote(not: Note, tekst:Editable?){
        myDB.updateNote(not,tekst.toString())
        notes=myDB.getAllNotes()
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}