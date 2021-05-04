package com.example.mypa

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mypa.calendar.Event
import com.example.mypa.notes.Note
import com.example.mypa.schedule.Day
import com.example.mypa.todo.ToDo

private lateinit var danUNedelji:Integer

class MyDBHelper(context: Context) : SQLiteOpenHelper(context,"DataBase", null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS TODO(ID INTEGER PRIMARY KEY AUTOINCREMENT,ITEM TEXT,CHECKED INT)")
        db?.execSQL("CREATE TABLE IF NOT EXISTS REG(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, LASTNAME TEXT, EMAIL TEXT,BIRTH TEXT)")
        db?.execSQL("CREATE TABLE IF NOT EXISTS EVENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, EVENT TEXT, CLICKED INT)")
        db?.execSQL("CREATE TABLE IF NOT EXISTS NOTES(ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, NOTE TEXT)")
        db?.execSQL("CREATE TABLE IF NOT EXISTS DAYS(ID INTEGER PRIMARY KEY AUTOINCREMENT,DAY INTEGER, TITLE TEXT, TIME TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun updateNote(not: Note, cont:String){
        val db=this.writableDatabase
        val cv = ContentValues()
        cv.put("TITLE",not.title)
        cv.put("NOTE",cont)
        db.update("NOTES",cv,"ID=?", arrayOf(not.id.toString()))
        db.close()
    }

    fun insertDay(title: String, time: String){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("TITLE",title)
        cv.put("TIME",time)
        cv.put("DAY", danUNedelji.toInt())

        db.insert("DAYS",null,cv)
        db.close()
    }

    fun insertEvent(date: String, event: String){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("DATE",date)
        cv.put("EVENT",event)
        cv.put("CLICKED",0)

        db.insert("EVENTS",null,cv)
        db.close()
    }


    fun insertData(name: String, lastname: String, email: String, birth:String){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("NAME",name)
        cv.put("LASTNAME",lastname)
        cv.put("EMAIL", email)
        cv.put("BIRTH",birth)

        db.insert("REG",null,cv)
        db.close()
    }

    fun insertTask(item:String,checked:Int){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("ITEM",item)
        cv.put("CHECKED",checked)

        db.insert("TODO",null,cv)
        db.close()
    }

    fun insertNote(title:String,cont:String){
        val db= this.writableDatabase
        val cv = ContentValues()
        cv.put("TITLE",title)
        cv.put("NOTE",cont)

        db.insert("NOTES",null,cv)
        db.close()
    }


    fun updateTask(id:Integer, checked: Int){
        val db=this.writableDatabase
        val cv = ContentValues()
        cv.put("CHECKED",checked)
        db.update("TODO",cv,"ID=?", arrayOf(id.toString()))
        db.close()
    }

    fun delete(id: Integer){
        val db = this.writableDatabase
        db.delete("EVENTS","ID=?", arrayOf(id.toString()))
        db.close()
    }


    fun deleteEvent(ev: Event){
        val db=this.writableDatabase
        db.delete("EVENTS","ID=?", arrayOf(ev.id.toString()))
        db.close()
    }

    fun setDayOfWeek(dan:Int){
        danUNedelji= Integer(dan)
    }

    fun deleteDay(dan: Day){
        val db= this.writableDatabase
        db.delete("DAYS","ID=?", arrayOf(dan.id.toString()))
        db.close()
    }

    fun deleteNote(not: Note) {
        val db = this.writableDatabase
        db.delete("NOTES","ID=?", arrayOf(not.id.toString()))
        db.close()
    }

    fun deleteTask(id:Integer){
        val db=this.writableDatabase
        db.delete("TODO","ID=?", arrayOf(id.toString()))
        db.close()
    }


    fun getAll(): MutableList<ToDo> {
        var db = this.writableDatabase
        var cursor: Cursor? = null
        var lista = mutableListOf<ToDo>()
        lateinit var id : Integer
        lateinit var title : String
        var checked : Int
        lateinit var task : ToDo

        db.beginTransaction()
        try {
            cursor = db.query("TODO",null,null,null,null,null,null,null)
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do {
                        id = Integer(cursor.getInt(cursor.getColumnIndex("ID")))
                        title = cursor.getString(cursor.getColumnIndex("ITEM"))
                        checked = cursor.getInt(cursor.getColumnIndex("CHECKED"))
                        task = ToDo(id,title,checked)
                        lista.add(task)
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            db.endTransaction()
            if (cursor != null) {
                cursor.close()
            }
        }
        db.close()
        return lista
    }

    fun getAllEvents(datum: String): MutableList<Event> {
        val db = this.readableDatabase
        var cursor: Cursor? = null
        var lista = mutableListOf<Event>()
        lateinit var id : Integer
        lateinit var date : String
        lateinit var event : String
        lateinit var dog : Event

        db.beginTransaction()
        try {
            cursor = db.query("EVENTS",null,null,null,null,null,null,null)
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do {
                        id = Integer(cursor.getInt(cursor.getColumnIndex("ID")))
                        date=cursor.getString(cursor.getColumnIndex("DATE"))
                        event=cursor.getString(cursor.getColumnIndex("EVENT"))
                        dog = Event(id,date,event)

                        if(dog.date == datum)
                            lista.add(dog)
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            db.endTransaction()
            if (cursor != null) {
                cursor.close()
            }
        }
        db.close()
        return lista
    }

    @SuppressLint("UseValueOf")
    fun getAllOnDay(): MutableList<Day> {
        val db = this.readableDatabase
        var cursor: Cursor? = null
        var lista = mutableListOf<Day>()
        lateinit var id : Integer
        lateinit var title : String
        lateinit var time : String
        lateinit var task : ToDo
        lateinit var dan : Integer
        db.beginTransaction()
        try {
            cursor = db.query("DAYS",null,null,null,null,null,null,null)
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do {
                        dan=Integer(cursor.getString(cursor.getColumnIndex("DAY")))
                        if(dan.toInt() == danUNedelji.toInt()) {
                            title=cursor.getString(cursor.getColumnIndex("TITLE"))
                            time=cursor.getString(cursor.getColumnIndex("TIME"))
                            id= Integer(cursor.getString(cursor.getColumnIndex("ID")))
                            lista.add(Day(id, dan, title, time))
                        }
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            db.endTransaction()
            if (cursor != null) {
                cursor.close()
            }
        }
        db.close()
        if(!lista.isEmpty())
            lista = lista.sortedBy{ it.time } as MutableList<Day>

        return lista
    }

    @SuppressLint("UseValueOf")
    fun getAllNotes(): MutableList<Note> {
        val db = this.readableDatabase
        var cursor: Cursor? = null
        val lista = mutableListOf<Note>()
        lateinit var id : Integer
        lateinit var title : String
        lateinit var notes : String
        lateinit var note : Note

        db.beginTransaction()
        try {
            cursor = db.query("NOTES",null,null,null,null,null,null,null)
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do {
                        id = Integer(cursor.getInt(cursor.getColumnIndex("ID")))
                        title=cursor.getString(cursor.getColumnIndex("TITLE"))
                        notes=cursor.getString(cursor.getColumnIndex("NOTE"))
                        note = Note(id,title,notes)

                        lista.add(note)
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            db.endTransaction()
            if (cursor != null) {
                cursor.close()
            }
        }
        db.close()
        return lista
    }
}