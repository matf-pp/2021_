package com.example.mypa

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mypa.databinding.MainPageBinding


class MainPageActivity : AppCompatActivity() {

    private lateinit var mainBinding : MainPageBinding
    private var ime: String = "ime"
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = MainPageBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase

        db.beginTransaction()

        var cursor: Cursor = db.rawQuery("select name from reg  ",null)

        try{
            if(cursor!=null) {
                if (cursor.moveToFirst()) {
                    do {
                        name = cursor.getString(cursor.getColumnIndex("NAME"))
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            db.endTransaction()
            if (cursor != null) {
                cursor.close()
            }
        }
        
        val sharedPref : SharedPreferences = getSharedPreferences("quote",Context.MODE_PRIVATE)

    /*
        TODO: citat namestiti i slika treba

        mainBinding.quote.setOnClickListener {
            val tekst = mainBinding.quote.text.toString()
            val editor = sharedPref.edit()
            editor.putString("quote", tekst)
            editor.apply()
        }
*/

        mainBinding.name.setText( name)
        mainBinding.quote.setText(sharedPref.getString("quote",""))
        mainBinding.btnToDo.setOnClickListener {
            val intent = Intent(this,ToDoActivity::class.java)
            startActivity(intent)
        }

        mainBinding.btnCalendar.setOnClickListener {
            val intent = Intent(this,CalendarActivity::class.java)
            startActivity(intent)
        }

        mainBinding.btnSchedule.setOnClickListener {
            val intent = Intent(this,Schedule::class.java)
            startActivity(intent)
        }

        mainBinding.btnNotes.setOnClickListener {
            val intent = Intent(this,NotesActivity::class.java)
            startActivity(intent)
        }

    }

}