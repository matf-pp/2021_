package com.example.mypa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.to_do.*

class ToDoActivity : AppCompatActivity() {

    private lateinit var ime: String

    private lateinit var todoAdapter: TodoAdapter
    private var unesi=false

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

            R.id.mToDo-> {
                setContentView(R.layout.to_do)
                true
            }
            R.id.mMain-> {
                setContentView(R.layout.main_page)
                true
            }
            /*R.id.mCal-> {
                setContentView(R.layout.calendar)
                true
            }
            R.id.mSch-> {
                setContentView(R.layout.schedule)
                true
            }
            R.id.mNotes-> {
                setContentView(R.layout.notes)
                true
            }
            R.id.mGrades-> {
                setContentView(R.layout.grades)
                true
            }*/

            else -> super.onOptionsItemSelected(item)

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.to_do)
        todoAdapter= TodoAdapter(mutableListOf())

        rvToDoItem.adapter=todoAdapter
        rvToDoItem.layoutManager = LinearLayoutManager(this)

        btAdd.setOnClickListener {

            val tekst= etTodoTitle.text.toString()
            if(tekst.isNotEmpty()){
                val todo= ToDo(tekst)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }

        btDeleteDone.setOnClickListener {
            todoAdapter.deleteDone()
        }

    }
}