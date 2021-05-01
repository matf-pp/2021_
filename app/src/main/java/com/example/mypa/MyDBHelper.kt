package com.example.mypa

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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

    }

    fun updateNote(not:Note,cont:String){
        var db=this.writableDatabase
        var cv = ContentValues()
        cv.put("TITLE",not.title)
        cv.put("NOTE",cont)
        db.update("NOTES",cv,"ID=?", arrayOf(not.id.toString()))
    }

    fun insertDay(title: String, time: String){
        var db = this.writableDatabase
        val cv = ContentValues()
        cv.put("TITLE",title)
        cv.put("TIME",time)

        db.insert("DAY",null,cv)
    }

    fun insertEvent(date: String, event: String){
        var db = this.writableDatabase
        val cv = ContentValues()
        cv.put("DATE",date)
        cv.put("EVENT",event)
        cv.put("CLICKED",0)

        db.insert("EVENTS",null,cv)
    }


    fun insertData(name: String, lastname: String, email: String, birth:String){
        var db = this.writableDatabase
        val cv = ContentValues()
        cv.put("NAME",name)
        cv.put("LASTNAME",lastname)
        cv.put("EMAIL", email)
        cv.put("BIRTH",birth)

        db.insert("REG",null,cv)

    }

    fun insertTask(item:String,checked:Int){
        var db = this.writableDatabase
        val cv = ContentValues()
        cv.put("ITEM",item)
        cv.put("CHECKED",checked)

        db.insert("TODO",null,cv)

    }

    fun insertNote(title:String,cont:String){
        var db= this.writableDatabase
        val cv = ContentValues()
        cv.put("TITLE",title)
        cv.put("NOTE",cont)

        db.insert("NOTES",null,cv)
    }


    fun updateTask(id:Integer, checked: Int){
        var db=this.writableDatabase
        var cv = ContentValues()
        cv.put("CHECKED",checked)
        db.update("TODO",cv,"ID=?", arrayOf(id.toString()))
    }

    fun updateEvent(id:Integer, checked: Int){
        var db=this.writableDatabase
        var cv = ContentValues()
        cv.put("CLICKED",checked)
        db.update("EVENTS",cv,"ID=?", arrayOf(id.toString()))
    }

    fun delete(id: Integer){
        var db = this.writableDatabase
        db.delete("EVENTS","ID=?", arrayOf(id.toString()))
    }


    fun deleteEvent(ev:Event){
        var db=this.writableDatabase
        db.delete("EVENTS","EVENT=?", arrayOf(ev.event.toString()))
   /*     var cursor: Cursor? = null

            db.beginTransaction()
            try {
                cursor = db.query("EVENTS",null,null,null,null,null,null,null)
                if(cursor!=null){
                    if(cursor.moveToFirst()){
                        do {
                            if((cursor.getInt(cursor.getColumnIndex("CLICKED"))) == 1)
                                delete(Integer(cursor.getInt(cursor.getColumnIndex("ID"))))
                        } while (cursor.moveToNext());
                    }
                }
            } finally {
                db.endTransaction()
                if (cursor != null) {
                    cursor.close()
                }
            }
      */  }

    fun deleteNote(not:Note) {
        var db = this.writableDatabase
        db.delete("NOTES","NOTE=?", arrayOf(not.notes.toString()))
    }

    fun deleteTask(id:Integer){
        var db=this.writableDatabase
        db.delete("TODO","ID=?", arrayOf(id.toString()))
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
                        var task: ToDo = ToDo(Integer(-1),"jelena",0)
                        task.id=Integer(cursor.getInt(cursor.getColumnIndex("ID")))
                        task.title=cursor.getString(cursor.getColumnIndex("ITEM"))
                        task.isChecked=cursor.getInt(cursor.getColumnIndex("CHECKED"))
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
                        var dog: Event = Event(Integer(-1),"00.00.0000.","")
                        dog.date=cursor.getString(cursor.getColumnIndex("DATE"))
                        dog.event=cursor.getString(cursor.getColumnIndex("EVENT"))
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
        return lista
    }
/*
    fun getAllOnDay(day: Integer): MutableList<Day> {
        var db = this.readableDatabase
        var cursor: Cursor? = null
        var lista = mutableListOf<Day>()

        db.beginTransaction()
        try {
            cursor = db.query("DAYS",null,null,null,null,null,null,null)
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do {
                        var dog: Day?=null
                        var id= Integer(cursor.getString(cursor.getColumnIndex("ID")))
                        var dan=Integer(cursor.getString(cursor.getColumnIndex("DAY")))
                        var title=cursor.getString(cursor.getColumnIndex("TITLE"))
                        var time=cursor.getString(cursor.getColumnIndex("TIME"))
                        if(dan == day)
                            lista.add(Day(id,dan,title,time))
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            db.endTransaction()
            if (cursor != null) {
                cursor.close()
            }
        }
        return lista
    }

*/
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
            return ev
        }
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
                        var note: Note = Note(Integer(-1),"","")
                        note.title=cursor.getString(cursor.getColumnIndex("TITLE"))
                        note.notes=cursor.getString(cursor.getColumnIndex("NOTE"))
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
        return lista
    }
}