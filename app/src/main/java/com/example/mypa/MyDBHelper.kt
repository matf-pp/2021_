package com.example.mypa

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context) : SQLiteOpenHelper(context,"DataBase", null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS TODO(ID INTEGER PRIMARY KEY AUTOINCREMENT,ITEM TEXT,CHECKED INT)")
        db?.execSQL("CREATE TABLE IF NOT EXISTS REG(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, LASTNAME TEXT, EMAIL TEXT)")
        db?.execSQL("CREATE TABLE IF NOT EXISTS EVENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, EVENT TEXT, CLICKED INT)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertEvent(date: String, event: String){
        var db = this.writableDatabase
        val cv = ContentValues()
        cv.put("DATE",date)
        cv.put("EVENT",event)
        cv.put("CLICKED",0)

        db.insert("EVENTS",null,cv)
    }


    fun insertData(name: String, lastname: String, email: String){
        var db = this.writableDatabase
        val cv = ContentValues()
        cv.put("NAME",name)
        cv.put("LASTNAME",lastname)
        cv.put("EMAIL", email)

        db.insert("REG",null,cv)
    }

    /*fun getData() : String {
        var db = this.readableDatabase
        var jelena : String = "Jelena"
        var name = db.rawQuery("SELECT NAME FROM REG WHERE NAME=\""+jelena + "\"",null).toString()
        return name
    }*/

    fun insertTask(item:String,checked:Int){
        var db = this.writableDatabase
        val cv = ContentValues()
        cv.put("ITEM",item)
        cv.put("CHECKED",checked)

        db.insert("TODO",null,cv)
    }


    fun updateTask(id:Integer, checked: Int){
        var db=this.writableDatabase
        var cv = ContentValues()
        cv.put("CHECKED",checked)
        db.update("TODO",cv,"ID=?", arrayOf(id.toString()))
    }

  /*  fun updateEvent(id:Integer, checked: Int){
        var db=this.writableDatabase
        var cv = ContentValues()
        cv.put("CLICKED",checked)
        db.update("EVENTS",cv,"ID=?", arrayOf(id.toString()))
    }
*/
  /*  fun deleteEvent(){
        var db=this.writableDatabase
        //db.delete("EVENTS","ID=?", arrayOf(id.toString()))
        var cursor: Cursor? = null

        db.beginTransaction()
        try {
            cursor = db.query("EVENTS",null,null,null,null,null,null,null)
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do {
                        if((cursor.getInt(cursor.getColumnIndex("CLICKED"))) == 1)
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
*/
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

}