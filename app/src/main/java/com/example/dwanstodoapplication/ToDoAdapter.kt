package com.example.dwanstodoapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ToDoAdapter(
        //declare a MutableList of ToDos
        var todos: MutableList<ToDo>
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){
    //declare a ViewHolder that will hold the layout of an item in the
    //RecyclerView
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //declare TextView mutable field with null safety
    var todoTextView: TextView? = null

    //declare checkbox mutable field with null safety
    var todoCheckBox: CheckBox? = null

    /**
     * Create a ToDoViewHolder that references the li_main layout resource
     * and return it
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.li_main,
                parent,
                false
        ))
    }

    /**
     * Returns the number of items in the MutableList of ToDos
     */
    override fun getItemCount(): Int {
        return todos.size

    }

    /**
     * Initializes each of the items in the recyclerview and maps
     */
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        //get current item in mutable list of todos and store it in a
        //mutable variable
        val currentToDo = todos[position]

        holder.itemView.apply {
            //make TextView refer to TextView in li_main layout resource
            todoTextView = findViewById<View>(R.id.todoTextView) as TextView
            //tell kotlin that textView isnt null
            //assign the name value in the current item to the text attribute
            todoTextView!!.text = currentToDo.name
            //make checkbox refer to TextView in li_main layout resource
            todoCheckBox = findViewById<View>(R.id.todoCheckBox) as CheckBox
            //tell kotlin that checkbox isnt null
            //assign the is checked value to the current item to ischecked
            //attribute of checkbox
            todoCheckBox!!.isChecked= currentToDo.ischecked

            //tell kotlin that checkBox isnt null
            //set onCheckedChangeListener to it
            todoCheckBox!!.setOnCheckedChangeListener { _, _ ->
                //reverse the value in the ischecked field of the current todo
                currentToDo.ischecked = !currentToDo.ischecked
            }
        }

    }

    /**
     * This method gets called by the addTodo method in the MainActivity
     * when the add button is clicked. It will call the DBHandler method
     * thats adds a todo into the database
     *
     */
    fun addTodo(dbHandler: DBHandler, name: String){
        //ask kotlin to check if dbhandler is null
        //if it isnt call its addTodo method
        dbHandler?.addTodo(name)
        notifyDataSetChanged()
    }

    fun deleteTodos(dbHandler: DBHandler){
        todos.forEach {
            if(it.ischecked){
                dbHandler?.deleteToDo(it.id)
            }
        }
        notifyDataSetChanged()
    }
}