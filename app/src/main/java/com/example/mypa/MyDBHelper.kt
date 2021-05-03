package com.example.mypa

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

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

    fun makeNotes(title: String){
        var db = this.writableDatabase
        val cv = ContentValues()
        cv.put("TITLE",title)
        cv.put("NOTE","")

        db.insert("NOTES",null,cv)
        db.close()
    }

    fun updateNote(not:Note,cont:String){
        var db=this.writableDatabase
        var cv = ContentValues()
        cv.put("TITLE",not.title)
        cv.put("NOTE",cont)
        db.update("NOTES",cv,"ID=?", arrayOf(not.id.toString()))
        db.close()
    }

    fun getDayOfWeek():Integer{
        return danUNedelji
    }


    fun insertDay(title: String, time: String){
        var db = this.writableDatabase
        val cv = ContentValues()
        cv.put("TITLE",title)
        cv.put("TIME",time)
        cv.put("DAY", danUNedelji.toInt())

        db.insert("DAYS",null,cv)
        db.close()
    }

    fun insertEvent(date: String, event: String){
        var db = this.writableDatabase
        val cv = ContentValues()
        cv.put("DATE",date)
        cv.put("EVENT",event)
        cv.put("CLICKED",0)

        db.insert("EVENTS",null,cv)
        db.close()
    }


    fun insertData(name: String, lastname: String, email: String, birth:String){
        var db = this.writableDatabase
        val cv = ContentValues()
        cv.put("NAME",name)
        cv.put("LASTNAME",lastname)
        cv.put("EMAIL", email)
        cv.put("BIRTH",birth)

        db.insert("REG",null,cv)
        db.close()
    }

    fun insertTask(item:String,checked:Int){
        var db = this.writableDatabase
        val cv = ContentValues()
        cv.put("ITEM",item)
        cv.put("CHECKED",checked)

        db.insert("TODO",null,cv)
        db.close()
    }

    fun insertNote(title:String,cont:String){
        var db= this.writableDatabase
        val cv = ContentValues()
        cv.put("TITLE",title)
        cv.put("NOTE",cont)

        db.insert("NOTES",null,cv)
        db.close()
    }


    fun updateTask(id:Integer, checked: Int){
        var db=this.writableDatabase
        var cv = ContentValues()
        cv.put("CHECKED",checked)
        db.update("TODO",cv,"ID=?", arrayOf(id.toString()))
        db.close()
    }

    fun updateEvent(id:Integer, checked: Int){
        var db=this.writableDatabase
        var cv = ContentValues()
        cv.put("CLICKED",checked)
        db.update("EVENTS",cv,"ID=?", arrayOf(id.toString()))
        db.close()
    }

    fun delete(id: Integer){
        var db = this.writableDatabase
        db.delete("EVENTS","ID=?", arrayOf(id.toString()))
        db.close()
    }


    fun deleteEvent(ev:Event){
        var db=this.writableDatabase
        db.delete("EVENTS","ID=?", arrayOf(ev.id.toString()))
        db.close()
    }

    fun setDayOfWeek(dan:Int){
        danUNedelji= Integer(dan)
    }

    fun deleteDay(dan:Day){
        var db= this.writableDatabase
        db.delete("DAYS","ID=?", arrayOf(dan.id.toString()))
        db.close()
    }

    fun deleteNote(not:Note) {
        var db = this.writableDatabase
        db.delete("NOTES","ID=?", arrayOf(not.id.toString()))
        db.close()
    }

    fun deleteTask(id:Integer){
        var db=this.writableDatabase
        db.delete("TODO","ID=?", arrayOf(id.toString()))
        db.close()
    }

    fun deleteTasks(){
        var db = this.writableDatabase
        var cursor: Cursor? = null

        db.beginTransaction()
        try {
            cursor = db.query("TODO",null,null,null,null,null,null,null)
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do {
                        if((cursor.getInt(cursor.getColumnIndex("CHECKED"))) == 1)
                            deleteTask(Integer(cursor.getInt(cursor.getColumnIndex("ID"))))
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
    }


    fun getAll(): MutableList<ToDo> {
        var db = this.writableDatabase
        var cursor: Cursor? = null
        var lista = mutableListOf<ToDo>()

        db.beginTransaction()
        try {
            cursor = db.query("TODO",null,null,null,null,null,null,null)
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do {
                        val id = Integer(cursor.getInt(cursor.getColumnIndex("ID")))
                        val title = cursor.getString(cursor.getColumnIndex("ITEM"))
                        val checked = cursor.getInt(cursor.getColumnIndex("CHECKED"))
                        var task: ToDo = ToDo(id,title,checked)
                        //task.id=Integer(cursor.getInt(cursor.getColumnIndex("ID")))
                        //task.title=cursor.getString(cursor.getColumnIndex("ITEM"))
                        //task.isChecked=cursor.getInt(cursor.getColumnIndex("CHECKED"))
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

    fun getAllEvents(date: String): MutableList<Event> {
        var db = this.readableDatabase
        var cursor: Cursor? = null
        var lista = mutableListOf<Event>()

        db.beginTransaction()
        try {
            cursor = db.query("EVENTS",null,null,null,null,null,null,null)
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do {
                        val id = Integer(cursor.getInt(cursor.getColumnIndex("ID")))
                        val date=cursor.getString(cursor.getColumnIndex("DATE"))
                        val event=cursor.getString(cursor.getColumnIndex("EVENT"))
                        var dog : Event  = Event(id,date,event)

                        if(dog.date == date)
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

    fun getAllOnDay(): MutableList<Day> {
        var db = this.readableDatabase
        var cursor: Cursor? = null
        var lista = mutableListOf<Day>()

        db.beginTransaction()
        try {
            cursor = db.query("DAYS",null,null,null,null,null,null,null)
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do {
                        var dan=Integer(cursor.getString(cursor.getColumnIndex("DAY")))
                        if(dan.toInt() == danUNedelji.toInt()) {
                            var title=cursor.getString(cursor.getColumnIndex("TITLE"))
                            var time=cursor.getString(cursor.getColumnIndex("TIME"))
                            var id= Integer(cursor.getString(cursor.getColumnIndex("ID")))
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


    fun getEventId(id:Integer):Event?{
        var cursor:Cursor? = null
        var db=this.readableDatabase
        cursor=db.rawQuery("SELECT * FROM EVENTS WHERE ID = ?", arrayOf(id.toString()))
        lateinit var ev:Event
        if(cursor!=null){
            ev.id=id
            ev.date=cursor.getString(cursor.getColumnIndex("DATE"))
            ev.event=cursor.getString(cursor.getColumnIndex("EVENT"))
            ev.isChecked=cursor.getInt(cursor.getColumnIndex("CLICKED"))
            db.close()
            return ev
        }
        db.close()
        return null
    }

    fun getAllNotes(): MutableList<Note> {
        var db = this.readableDatabase
        var cursor: Cursor? = null
        var lista = mutableListOf<Note>()

        db.beginTransaction()
        try {
            cursor = db.query("NOTES",null,null,null,null,null,null,null)
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do {
                        val id = Integer(cursor.getInt(cursor.getColumnIndex("ID")))
                        val title=cursor.getString(cursor.getColumnIndex("TITLE"))
                        val notes=cursor.getString(cursor.getColumnIndex("NOTE"))
                        var note: Note = Note(id,title,notes)

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