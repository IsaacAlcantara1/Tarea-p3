package com.example.tareagitprog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private var registroList: List<Registro>,
    private val onClickDelete: (Int) -> Unit,
    private val onItemClickEdit: (Int) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = registroList[position]
        holder.render(item, onClickDelete, onItemClickEdit)
    }

    override fun getItemCount(): Int {
        return registroList.size
    }

    fun updateAdapter(registroList: List<Registro>) {
        this.registroList = registroList
        notifyDataSetChanged()
    }

}