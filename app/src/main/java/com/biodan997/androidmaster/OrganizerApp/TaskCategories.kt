package com.biodan997.androidmaster.OrganizerApp

//cambios en el sealed class TaskCategory(var isSelected:Boolean=false)*ultimo tramo*min: 9.09,

sealed class TaskCategories(var isSelected:Boolean=true){
    object Personal : TaskCategories()
    object Business : TaskCategories()
    object Other : TaskCategories()
}

