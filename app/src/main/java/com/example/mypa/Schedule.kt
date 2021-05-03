package com.example.mypa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import com.example.mypa.databinding.ScheduleBinding

class Schedule : AppCompatActivity() {

    private lateinit var schActivity: ScheduleBinding
    private lateinit var weekAdapter: WeekAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        schActivity = ScheduleBinding.inflate(layoutInflater)
        setContentView(schActivity.root)
        var helper = MyDBHelper(applicationContext)

        var week: Array<String> = resources.getStringArray(R.array.week)
        weekAdapter = WeekAdapter(helper,this, week)

        schActivity.lvWeek.adapter = weekAdapter

        schActivity.lvWeek.setOnItemClickListener { _, _, position, _ ->
            var dayName = week[position]
            val intent = Intent(this,DayActivity::class.java)
            startActivity(intent)
            helper.setDayOfWeek(position)
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
