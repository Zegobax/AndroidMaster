package com.biodan997.androidmaster.OrganizerApp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.biodan997.androidmaster.R


class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)
    //cambio: Se agrega el viewContainer****************************
    private val viewContainer:CardView= view.findViewById(R.id.viewContainer)
//******************************************************************

//cambios en el fun render declaration *ultimo tramo*min: 9.06,
//*********************************************************************************
    fun render(TaskCategories: TaskCategories, onItemSelected: (Int)-> Unit) {

        val color= if(TaskCategories.isSelected) {

            R.color.organizer_background_card
        }else{
            R.color.organizer_background_disable
        }
//************************************************************************************
        //Se crean estos BackgroundColor*********************************
        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context,color))

        itemView.setOnClickListener { onItemSelected(layoutPosition) }
        //******************************************************************

        when(TaskCategories){
            com.biodan997.androidmaster.OrganizerApp.TaskCategories.Business -> {
                tvCategoryName.text="Business"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.organizer_business_category))

            }
            com.biodan997.androidmaster.OrganizerApp.TaskCategories.Other -> {
                tvCategoryName.text="Others"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.organizer_others_category))


            }
            com.biodan997.androidmaster.OrganizerApp.TaskCategories.Personal -> {
              tvCategoryName.text="Personal"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.organizer_personal_category))


            }


        }

    }

}