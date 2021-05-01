package com.example.mypa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypa.databinding.CalendarBinding
import com.example.mypa.databinding.EventBinding

class CalendarActivity : AppCompatActivity() {

    private lateinit var calendarActivity : CalendarBinding
    private lateinit var eventBinding : EventBinding
    private lateinit var eventAdapter: EventAdapter

    private lateinit var year: String
    private lateinit var month: String
    private lateinit var day: String
    private var date: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calendarActivity = CalendarBinding.inflate(layoutInflater)
        eventBinding = EventBinding.inflate(layoutInflater)
        setContentView(calendarActivity.root)

        lateinit var lista : MutableList<Event>
        var helper = MyDBHelper(applicationContext)

        calendarActivity.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            this.year = year.toString()
            this.month = (month + 1 ).toString()
            day = dayOfMonth.toString()
            date = day + "." + this.month + "." + this.year + "."

            lista = helper.getAllEvents(date)
            eventAdapter = EventAdapter(this,helper, lista)

            calendarActivity.rvCalendar.adapter = eventAdapter
            calendarActivity.rvCalendar.layoutManager = LinearLayoutManager(this)
        }

        calendarActivity.btnEvents.setOnClickListener {

            calendarActivity.popUp.isVisible = true
            calendarActivity.theDate.setText(date)
            lista = helper.getAllEvents(date)
            eventAdapter.setTasks(lista)
        }

        calendarActivity.addEvent.setOnClickListener {
            var event = calendarActivity.createEvent.text.toString()
            helper.insertEvent(date,event)
            lista = helper.getAllEvents(date)
            eventAdapter.setTasks(lista)
            calendarActivity.createEvent.setText("")
        }

        calendarActivity.popUpBack.setOnClickListener {
            calendarActivity.popUp.isVisible = false
        }

        calendarActivity.rvCalendar.setRecyclerListener {
            eventBinding.imgDelete.setOnClickListener {
                lista=eventAdapter.getTasks(calendarActivity.theDate.toString())
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



