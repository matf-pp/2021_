package com.example.mypa


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypa.databinding.ToDoBinding

class ToDoActivity : AppCompatActivity() {

    private lateinit var todoActivity :ToDoBinding
    private lateinit var todoAdapter: TodoAdapter


    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todoActivity = ToDoBinding.inflate(layoutInflater)
        setContentView(todoActivity.root)

        lateinit var lista : MutableList<ToDo>
        var helper = MyDBHelper(applicationContext)
        lista = helper.getAll()
        todoAdapter = TodoAdapter(helper, lista)

        todoActivity.rvToDoItem.adapter = todoAdapter
        todoActivity.rvToDoItem.layoutManager = LinearLayoutManager(this)

        todoActivity.btAdd.setOnClickListener {
            val tekst= todoActivity.etTodoTitle.text.toString()
            if(tekst.isNotEmpty()){
                helper.insertTask(tekst,0)
                todoActivity.etTodoTitle.setText("")
                lista = helper.getAll()
                todoAdapter.setTasks(lista)
            }
        }

        todoActivity.btDeleteDone.setOnClickListener {
            lista = helper.getAll()
            todoAdapter.deleteTask(lista)
            lista = helper.getAll()
            todoAdapter.setTasks(lista)
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

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}