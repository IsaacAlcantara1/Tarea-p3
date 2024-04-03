package com.example.tareagitprog

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvNombre: TextView = view.findViewById(R.id.tvNombre)
    private val tvCorreo: TextView = view.findViewById(R.id.tvCorreo)
    private val tvTelefono: TextView = view.findViewById(R.id.tvTelefono)
    private val trashButton: ImageView = view.findViewById(R.id.trashbutton)
    private val edithButton: ImageView = view.findViewById(R.id.editButton)

    fun render(
        datos: Registro,
        onClickDelete: (Int) -> Unit,
        onItemClickEdit: (Int) -> Unit
    ){
        tvNombre.text = datos.nombre
        tvCorreo.text = datos.correo
        tvTelefono.text = datos.telefono

        trashButton.setOnClickListener { onClickDelete(adapterPosition) }
        edithButton.setOnClickListener { onItemClickEdit(adapterPosition) }


    }
}