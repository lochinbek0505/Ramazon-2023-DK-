package com.wordpress.lochindev.ramazon_taqvimi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wordpress.lochindev.ramazon_taqvimi.R
import com.wordpress.lochindev.ramazon_taqvimi.models.DataDialog
import kotlinx.android.synthetic.main.item_dialog.view.*

class AdapterDialogFragmernt(val list: ArrayList<DataDialog>) : RecyclerView.Adapter<AdapterDialogFragmernt.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_dialog,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            tv_eslatma_vil.text = list[position].shahrNomi
            time_vil.text = list[position].timeFarqti
        }
    }

    override fun getItemCount(): Int = list.size
}