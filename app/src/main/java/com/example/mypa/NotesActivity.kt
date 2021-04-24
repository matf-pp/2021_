package com.example.mypa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypa.databinding.NoteBinding
import com.example.mypa.databinding.NotesBinding

class NotesActivity : AppCompatActivity() {

    private lateinit var notesActivity: NotesBinding
    private lateinit var noteActivity: NoteBinding
    private lateinit var noteAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notesActivity = NotesBinding.inflate(layoutInflater)
        noteActivity = NoteBinding.inflate(layoutInflater)
        setContentView(notesActivity.root)

        lateinit var lista : MutableList<Note>
        var helper = MyDBHelper(applicationContext)
        lista = helper.getAllNotes()
        noteAdapter = NotesAdapter(helper, lista)

        notesActivity.rvNotes.adapter = noteAdapter
        notesActivity.rvNotes.layoutManager = GridLayoutManager(this,3)


        notesActivity.btAddNote.setOnClickListener {
            val tekst = notesActivity.etNewNote.text.toString()
            if (tekst.isNotEmpty()) {
                helper.makeNotes(tekst)
                notesActivity.etNewNote.setText("")
                lista = helper.getAllNotes()
                noteAdapter.setTasks(lista)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if(id == R.id.mMain){
            val intent = Intent(this,MainPageActivity::class.java)
            startActivity(intent)
            return true
        } else if(id == R.id.mToDo){
            val intent = Intent(this,ToDoActivity::class.java)
            startActivity(intent)
            return true
        } else if(id == R.id.mCal){
            val intent = Intent(this,CalendarActivity::class.java)
            startActivity(intent)
            return true
        } else if(id == R.id.mNotes){
            val intent = Intent(this,NotesActivity::class.java)
            startActivity(intent)
        }
        else if(id == R.id.mSch){
            val intent = Intent(this,Schedule::class.java)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}