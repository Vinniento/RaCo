package com.example.raco.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.raco.R
import com.example.raco.models.TrainingResponse
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

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val currentItem = trainingList[position]

        holder.date.text = currentItem.date
        holder.time.text = currentItem.time
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