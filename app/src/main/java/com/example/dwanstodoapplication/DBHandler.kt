package com.example.dwanstodoapplication
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler (context: Context?, cursorFactory: SQLiteDatabase.CursorFactory?) :
        SQLiteOpenHelper(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION){

}

