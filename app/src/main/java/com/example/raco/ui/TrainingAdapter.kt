package com.example.raco.ui

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.raco.R
import com.example.raco.models.TrainingResponse
import com.example.raco.utilities.HelperClass
import kotlinx.android.synthetic.main.training_card.view.*

class TrainingAdapter(private val trainingList: List<TrainingResponse>) :
    RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>() {
    //viewTyp nur wenn man mehrere arten von viewitems hat oder so?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.training_card,
            parent,
            false
        ) //attachToRoot = false heißt "don't attach the view to the recyclerView yet, the recyclerView itself will be responsible for that
        return TrainingViewHolder(itemView)
    }

    override fun getItemCount() = trainingList.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val currentItem = trainingList[position]

        //Bonus für hässlichkeit :D
        /*  var year: MutableList<Char> = mutableListOf()
          var month: MutableList<Char> = mutableListOf()
          var day: MutableList<Char> = mutableListOf()

          var counter = 0
          currentItem.date.toCharArray().forEachIndexed { index, c ->
              if (!c.equals('-') && counter == 0) {
                  year.add(c)
              } else if (c.equals('-'))
                  counter++
              else if (!c.equals('-') && counter == 1)
                  month.add(c)
              else if (!c.equals('-') && counter == 2)
                  day.add(c)
          }*/



        holder.date.text = "Date: ${HelperClass.parseDateFromJson(currentItem.date)}"
        holder.time.text = "Time: ${HelperClass.parseTimeFromJson(currentItem.time)}"
        //wäre das gleiche aber würde jedes mal findViewById aufrufen, anstatt die gecachten vom VIewHOlder zu holen
        //holder.firstname.playerFirstName_Cardview.text = currentItem.firstname
    }

    class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //TODO image ressource files müssten hier dann auch gesetzt werden
        val date: TextView =
            itemView.trainingsDateCardTextview //itemView. = synthetic property, same as findFiewByID
        val time: TextView = itemView.trainingsTimeCardTextview


    }
}