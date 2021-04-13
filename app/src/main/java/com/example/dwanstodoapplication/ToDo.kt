package com.example.dwanstodoapplication

data class ToDo(
        //declare a mutable Int to store the todo id
            var id: Int,
            //declare a immutable String to store the todo name
            var name: String,
            //declare a mutable boolean to store the todo is_checked
            var ischecked: Boolean = false
)

