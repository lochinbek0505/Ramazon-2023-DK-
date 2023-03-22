package com.wordpress.lochindev.ramazon_taqvimi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wordpress.lochindev.ramazon_taqvimi.R
import kotlinx.android.synthetic.main.item_location.view.*

class AdapterCountry(val data: List<String>, var listener:ItemSelectListener) : RecyclerView.Adapter<AdapterCountry.ViewHolder>(){

        interface ItemSelectListener{

            fun onClick(id:String)

        }


    class ViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_location,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            txt_country.text = data[position]
        }
        holder.itemView.card_view.setOnClickListener {
            listener.onClick(data[position])
        }

    }
    override fun getItemCount(): Int  = data.size
}