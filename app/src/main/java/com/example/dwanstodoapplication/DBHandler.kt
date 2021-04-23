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
                COLUMN_TODO_ISCHECKED + " TEXT, " +
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

    fun deleteToDo(id: Int){
        //get reference to todoapp database
        val db = writableDatabase

        //define statement
        val query = "DELETE FROM " + TABLE_TODO_LIST + " WHERE " +
                COLUMN_TODO_ID + " = " + id

        //execute delete statement
        db.execSQL(query)

        //close reference
        db.close()
    }

    /**
     * This method gets called when the Main Activity is created and when
     * the add and delete buttons get clicked
     * @return MutableList of Todos that contains all of the data
     * in the todo table
     */
    val todos: MutableList<ToDo>
    get(){
        //get reference to todoapp database
        val db = writableDatabase

        //select statement
        val query = "SELECT * FROM " + TABLE_TODO_LIST

        //execute and store in cursor
        val c = db.rawQuery(query, null)

        //create Mutable list of todos that will be returned
        val list: MutableList<ToDo> = ArrayList()

        //loop through rows in cursor
        while (c.moveToNext()){
            //create Mutable ToDo using the data in the current
            //row in the cursor
            val todo: ToDo = ToDo(c.getInt(c.getColumnIndex("_id")),
            c.getString(c.getColumnIndex("name")),
            c.getString(c.getColumnIndex("is_checked")).toBoolean());
            list.add(todo)
        }
        db.close()

        return list

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

