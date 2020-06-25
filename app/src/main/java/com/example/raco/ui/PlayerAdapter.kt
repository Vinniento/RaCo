package com.example.raco.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.raco.R
import com.example.raco.models.PlayerResponse
import kotlinx.android.synthetic.main.player_card.view.*

class PlayerAdapter(private val playerList: List<PlayerResponse>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    //viewTyp nur wenn man mehrere arten von viewitems hat oder so?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.player_card,
            parent,
            false
        ) //attachToRoot = false heißt "don't attach the view to the recyclerView yet, the recyclerView itself will be responsible for that
        return PlayerViewHolder(itemView)
    }

    override fun getItemCount() = playerList.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val currentItem = playerList[position]

        holder.firstname.text = currentItem.firstname
        holder.lastname.text = currentItem.lastname

        //wäre das gleiche aber würde jedes mal findViewById aufrufen, anstatt die gecachten vom VIewHOlder zu holen
        //holder.firstname.playerFirstName_Cardview.text = currentItem.firstname
    }

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //TODO image ressource files müssten hier dann auch gesetzt werden
        val firstname: TextView =
            itemView.playerFirstName_Cardview //itemView. = synthetic property, same as findFiewByID
        val lastname: TextView = itemView.playerLastName_Cardview
    }
}