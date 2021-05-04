package com.example.mypa.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypa.MainPageActivity
import com.example.mypa.MyDBHelper
import com.example.mypa.R
import com.example.mypa.calendar.CalendarActivity
import com.example.mypa.databinding.NotesBinding
import com.example.mypa.schedule.Schedule
import com.example.mypa.todo.ToDoActivity

class NotesActivity : AppCompatActivity() {

    private lateinit var notesActivity: NotesBinding
    private lateinit var noteAdapter: NotesAdapter
    lateinit var lista : MutableList<Note>
    lateinit var show : ViewGroup
    lateinit var title : String
    lateinit var cont : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notesActivity = NotesBinding.inflate(layoutInflater)
        setContentView(notesActivity.root)

        val helper = MyDBHelper(applicationContext)
        lista = helper.getAllNotes()
        noteAdapter = NotesAdapter(helper,this, lista)

        notesActivity.rvNotes.adapter = noteAdapter
        notesActivity.rvNotes.layoutManager = GridLayoutManager(applicationContext,2,LinearLayoutManager.VERTICAL,false)

        show = notesActivity.showUp

        notesActivity.btAddNote.setOnClickListener {
            notesActivity.popUp.isVisible=true
            lista=helper.getAllNotes()
            noteAdapter.setNotes(lista)
        }

        notesActivity.popUpBack.setOnClickListener {
            notesActivity.popUp.isVisible=false
            lista=helper.getAllNotes()
            noteAdapter.setNotes(lista)
            notesActivity.popUpEtTitle.setText("")
            notesActivity.popUpEtCont.setText("")
        }

        notesActivity.popUpImAdd.setOnClickListener {
            title = notesActivity.popUpEtTitle.text.toString()
            cont = notesActivity.popUpEtCont.text.toString()
            if(title.isNotEmpty() and cont.isNotEmpty()){
                helper.insertNote(title,cont)
                notesActivity.popUpEtTitle.setText("")
                notesActivity.popUpEtCont.setText("")
                notesActivity.popUp.isVisible=false
                lista = helper.getAllNotes()
                noteAdapter.setNotes(lista)
            }
        }

        notesActivity.rvNotes.setOnClickListener {
            notesActivity.showUp.isVisible = true
            val note : Note? = noteAdapter.getNote()
            if (note != null) {
                notesActivity.showTvTitl.setText(note.title)
            }
            if (note != null) {
                notesActivity.showEtPrikaz.setText(note.notes)
            }
        }

        notesActivity.showBtnBack.setOnClickListener {
            notesActivity.showUp.isVisible=false
            notesActivity.showTvTitl.setText("")
            notesActivity.showEtPrikaz.setText("")
            lista=helper.getAllNotes()
            noteAdapter.setNotes(lista)
        }

        notesActivity.showImSacuvaj.setOnClickListener {
            val notes = notesActivity.showEtPrikaz.text.toString()
            val note: Note? = noteAdapter.getNote()
            if (note != null) {
                helper.updateNote(note,notes)
            }

            lista = helper.getAllNotes()
            noteAdapter.setNotes(lista)

            notesActivity.showUp.isVisible = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if(id == R.id.mMain){
            val intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
            return true
        } else if(id == R.id.mToDo){
            val intent = Intent(this, ToDoActivity::class.java)
            startActivity(intent)
            return true
        } else if(id == R.id.mCal){
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
            return true
        } else if(id == R.id.mNotes){
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
            return true
        }
        else if(id == R.id.mSch){
            val intent = Intent(this, Schedule::class.java)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}