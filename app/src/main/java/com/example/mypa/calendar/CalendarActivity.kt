package com.example.mypa.calendar

import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypa.MainPageActivity
import com.example.mypa.MyDBHelper
import com.example.mypa.R
import com.example.mypa.databinding.CalendarBinding
import com.example.mypa.databinding.EventBinding
import com.example.mypa.notes.NotesActivity
import com.example.mypa.schedule.Schedule
import com.example.mypa.todo.ToDoActivity


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
        val helper = MyDBHelper(applicationContext)

        val current = Calendar.getInstance()
        val curryear = current.get(Calendar.YEAR).toString()
        val currmonth = (current.get(Calendar.MONTH) +1).toString()
        val currday = current.get(Calendar.DAY_OF_MONTH).toString()
        val currentdate = currday + "." + currmonth + "." + curryear + "."

       lista = helper.getAllEvents(currentdate)
        eventAdapter = EventAdapter(helper, lista)

        calendarActivity.rvCalendar.adapter = eventAdapter
        calendarActivity.rvCalendar.layoutManager = LinearLayoutManager(this)


        calendarActivity.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->

            this.year = year.toString()
            this.month = (month + 1 ).toString()
            day = dayOfMonth.toString()
            date = day + "." + this.month + "." + this.year + "."

            lista = helper.getAllEvents(date)
            eventAdapter = EventAdapter(helper, lista)

            calendarActivity.rvCalendar.adapter = eventAdapter
            calendarActivity.rvCalendar.layoutManager = LinearLayoutManager(this)
        }

        calendarActivity.btnEvents.setOnClickListener {

            if(date == ""){
                date = currentdate
            }


            calendarActivity.popUp.isVisible = true
            calendarActivity.theDate.setText(date)
            lista = helper.getAllEvents(date)
            eventAdapter.setTasks(lista)
        }

        calendarActivity.addEvent.setOnClickListener {
            val event = calendarActivity.createEvent.text.toString()
            if(event.isNotEmpty()) {
                helper.insertEvent(date, event)
                lista = helper.getAllEvents(date)
                eventAdapter.setTasks(lista)
                calendarActivity.createEvent.setText("")
            }
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



