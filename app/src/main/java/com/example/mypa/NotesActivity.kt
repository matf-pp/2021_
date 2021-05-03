package com.example.mypa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypa.databinding.ItemNoteBinding
import com.example.mypa.databinding.NotesBinding

class NotesActivity : AppCompatActivity()/*, NotesAdapter.OnNoteListener*/ {

    private lateinit var notesActivity: NotesBinding
    private lateinit var noteAdapter: NotesAdapter
    lateinit var lista : MutableList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notesActivity = NotesBinding.inflate(layoutInflater)
        var noteActivity = ItemNoteBinding.inflate(layoutInflater)
        setContentView(notesActivity.root)

        var helper = MyDBHelper(applicationContext)
        lista = helper.getAllNotes()
        noteAdapter = NotesAdapter(helper, lista)

        notesActivity.rvNotes.adapter = noteAdapter
        notesActivity.rvNotes.layoutManager = GridLayoutManager(applicationContext,2,LinearLayoutManager.VERTICAL,false)

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
            var title = notesActivity.popUpEtTitle.text.toString()
            var cont = notesActivity.popUpEtCont.text.toString()
            if(title.isNotEmpty() and cont.isNotEmpty()){
                helper.insertNote(title,cont)
                notesActivity.popUpEtTitle.setText("")
                notesActivity.popUpEtCont.setText("")
                notesActivity.popUp.isVisible=false
                lista = helper.getAllNotes()
                noteAdapter.setNotes(lista)
            }
        }

        //TODO RecyclerListener registruje samo dugmice i uvek se otvori polje kad kliknem na kanticu umesto van nje


        notesActivity.showBtnBack.setOnClickListener {
            notesActivity.showUp.isVisible=false
            notesActivity.showTvTitl.setText("")
            notesActivity.showEtPrikaz.setText("")
            lista=helper.getAllNotes()
            noteAdapter.setNotes(lista)
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
            return true
        }
        else if(id == R.id.mSch){
            val intent = Intent(this,Schedule::class.java)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}