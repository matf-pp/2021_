package com.example.mypa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mypa.calendar.CalendarActivity
import com.example.mypa.databinding.MainPageBinding
import com.example.mypa.notes.NotesActivity
import com.example.mypa.schedule.Schedule
import com.example.mypa.todo.ToDoActivity


class MainPageActivity : AppCompatActivity() {

    private lateinit var mainBinding : MainPageBinding
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = MainPageBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val helper = MyDBHelper(applicationContext)
        val db = helper.readableDatabase

        db.beginTransaction()

        val cursor = db.rawQuery("select name from reg  ",null)

        try{
            if (cursor.moveToFirst()) {
                do {
                    name = cursor.getString(cursor.getColumnIndex("NAME"))
                } while (cursor.moveToNext());
            }
        } finally {
            db.endTransaction()
            cursor.close()
        }

        mainBinding.name.setText( name)
        mainBinding.btnToDo.setOnClickListener {
            val intent = Intent(this, ToDoActivity::class.java)
            startActivity(intent)
        }

        mainBinding.btnCalendar.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        mainBinding.btnSchedule.setOnClickListener {
            val intent = Intent(this, Schedule::class.java)
            startActivity(intent)
        }

        mainBinding.btnNotes.setOnClickListener {
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }
    }
}