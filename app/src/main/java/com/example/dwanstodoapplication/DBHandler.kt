package com.example.dwanstodoapplication
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler (context: Context?, cursorFactory: SQLiteDatabase.CursorFactory?) :
        SQLiteOpenHelper(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION){
    /**
     * creates database ref table
     * @param db reference to todo app database
     */
    override fun onCreate(db: SQLiteDatabase) {
       // define create statement todo table
        val query = "CREATE TABLE " + TABLE_TODO_LIST + "(" +
                COLUMN_TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TODO_ISCHECKED + " TEXT " +
                COLUMN_TODO_NAME + " TEXT);";

        //execute statement
        db.execSQL(query)

    }

    /**
     * creates a new version of the database
     * @param db reference todoapp database
     * @param oldVersion of the database
     * @param newVersion of the database
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //define drop statement for the todo table
        val query = "DROP TABLE IF EXISTS " + TABLE_TODO_LIST

        //execute statement
        db.execSQL(query)

        //call method that creates the table
        onCreate(db)
    }

    /**
     *
     *@param todoName
     */
    fun addTodo(todoName: String?){

        //get reference to todoapp database
        val db = writableDatabase

        //get reference to todoapp database
        val values = ContentValues()

        //put data into the content values obj
        values.put(COLUMN_TODO_NAME, todoName)
        values.put(COLUMN_TODO_ISCHECKED, "false")

        //insert data in ContentValues obj table
        db.insert(TABLE_TODO_LIST, null, values)

        //close
        db.close()
    }

    companion object{
        //initialize constraints for the DB name and version
        private const val DATABASE_NAME = "todoapp.db"
        private const val DATABASE_VERSION = 1

        //initialize constraints for the todo table
        private const val TABLE_TODO_LIST = "todo"
        private const val COLUMN_TODO_ID = "_id"
        private const val COLUMN_TODO_NAME = "name"
        private const val COLUMN_TODO_ISCHECKED = "is_checked"




    }

}

