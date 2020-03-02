package com.tarun.demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tarun.demo.R
import com.tarun.demo.model.Item
import kotlinx.android.synthetic.main.item_list.view.*

class ItemAdapter(private val items: ArrayList<Item>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: Item) {
            itemView.apply {
             tvTitle.text = item.title
             tvNumberOfBracket.text = item.numbackers
             tvBy.text = item.by
            }
        }
    }
}