package com.example.dwanstodoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * This function gets called when the Add button is clicked.
     * it adds a todo into the database.
     * @param view MainActivity view
     */
    fun addTodo(view: View?){

    }

    /**
     * This function gets called when the delete button is clicked.
     * it deletes selected todos into the database.
     * @param view MainActivity view
     */
    fun deleteTodo(view: View?){

    }
}