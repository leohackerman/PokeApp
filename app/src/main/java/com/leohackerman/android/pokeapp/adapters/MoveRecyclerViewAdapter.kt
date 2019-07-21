package com.leohackerman.android.pokeapp.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leohackerman.android.pokeapp.R
import com.leohackerman.android.pokeapp.models.MoveDefinition
import kotlinx.android.synthetic.main.move_list_item.view.*

class MoveRecyclerViewAdapter(val moves: List<MoveDefinition>) : RecyclerView.Adapter<MoveRecyclerViewAdapter.ViewHolder>()  {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(moves[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MoveRecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.move_list_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return moves.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(moveDefinition: MoveDefinition){
            val move = moveDefinition.move
            itemView.move_name.text = move.name
        }
    }
}