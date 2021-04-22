package com.example.mypa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        setContentView(calendarActivity.root)

        lateinit var lista : MutableList<Event>
        var helper = MyDBHelper(applicationContext)

        calendarActivity.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            this.year = year.toString()
            this.month = (month +1 ).toString()
            day = dayOfMonth.toString()
            date = day + "." + month + "." + year + "."

            lista = helper.getAllEvents(date)
            eventAdapter = EventAdapter(helper, lista)

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
            calendarActivity.popUp.isVisible = false
        }

        eventBinding.imgDelete.setOnClickListener{

            eventAdapter.delete(date)
            lista = helper.getAllEvents(date)
            eventAdapter.setTasks(lista)

        }


    }
}



