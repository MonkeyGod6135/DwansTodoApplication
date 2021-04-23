package com.example.dwanstodoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    //declare Edittext as mutable field
    var todoEditText: EditText? = null

    //declare recyclerview as mutable field
    var todoRecyclerView: RecyclerView? = null

    //declare dbhandler as mutable field
    var dbHandler: DBHandler? = null

    lateinit var toDoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //make EditText and recyclerview refer to actual EditText and recycler view in activity_main layout resource
        todoEditText = findViewById<View>(R.id.todoEditText) as EditText
        todoRecyclerView = findViewById<View>(R.id.todoRecyclerView) as RecyclerView

        //fully initilize  dbhandler
        dbHandler = DBHandler(this,null)

        //initialize the toDoAdapter
        toDoAdapter = ToDoAdapter(dbHandler!!.todos)

        //tell kotlin that recyclerview isnt null
        todoRecyclerView!!.adapter = toDoAdapter

        //tell kotlin that the RecyclerView int null
        todoRecyclerView!!.layoutManager = LinearLayoutManager(this)

    }

    /**
     * This function gets called when the Add button is clicked.
     * it adds a todo into the database.
     * @param view MainActivity view
     */
    fun addTodo(view: View?){
        //tell kotlin that Edittext isnt null
        //get text input in Edittext as String
        //store it in variable
        val todoName = todoEditText!!.text.toString()

        //trim variable and check if its equal to an empty string
        if(todoName.trim() == ""){
            //display "please enter a Todo! Toast
            Toast.makeText(this, "Please enter a Todo!", Toast.LENGTH_LONG).show()
        } else{
            dbHandler?.let { toDoAdapter.addTodo(it, todoName) }
            //display toast
            Toast.makeText(this, "TODO ADDED!", Toast.LENGTH_LONG).show()

            //clear edittext
            todoEditText!!.text.clear()

            //call notifyAdapter method
            notifyAdapter()
        }
    }

    /**
     * This function gets called when the delete button is clicked.
     * it deletes selected todos into the database.
     * @param view MainActivity view
     */
    fun deleteTodo(view: View?){

        //ask kotlin to check the dbHandler is null
        //if it isnt null, pass it to the deleteToDos method in
        //the toDoApapter
        dbHandler?.let {
            toDoAdapter.deleteTodos(it)
        }
    }

    private fun notifyAdapter(){
        toDoAdapter.todos = dbHandler!!.todos
    }
}