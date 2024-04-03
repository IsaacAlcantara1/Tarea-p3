package com.example.tareagitprog

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var registros = mutableListOf<Registro>(
    )
    private lateinit var rvList: RecyclerView
    private lateinit var ftButton: FloatingActionButton
    private lateinit var rAdapter: Adapter
    private lateinit var filtro: EditText
    private val manager = LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filtro = findViewById(R.id.etFilter)

        filtro.addTextChangedListener { idFiltrar ->
            val searchText = idFiltrar.toString().lowercase()

            val datosFiltered = registros.filterIndexed { index, _ ->
                (index + 1).toString().contains(searchText)
            }
            rAdapter.updateAdapter(datosFiltered)
        }
        initComponents()
        initUi()
        initListeners()
    }
    private fun initListeners() {
        ftButton.setOnClickListener { showDialog() }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun showDialog() {
        displayDialog(null)
    }

    private fun displayDialog(posicion: Int?) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_registro)

        val nombreEditText: EditText = dialog.findViewById(R.id.addNombre)
        val correoEditText: EditText = dialog.findViewById(R.id.addCorreo)
        val telefonoEditText: EditText = dialog.findViewById(R.id.addTelefono)
        val agregarButton: Button = dialog.findViewById(R.id.btnAgregar)


        if (posicion != null) {
            val registroActual = registros[posicion]
            nombreEditText.setText(registroActual.nombre)
            correoEditText.setText(registroActual.correo)
            telefonoEditText.setText(registroActual.telefono)
        }
        agregarButton.setOnClickListener {
            addButtonClicked(
                nombreEditText.text.toString(),
                correoEditText.text.toString(),
                telefonoEditText.text.toString(),
                posicion,
                dialog
            )
        }

        dialog.setCanceledOnTouchOutside(false)
        val imgBack: ImageView = dialog.findViewById(R.id.imgBack)
        imgBack.setOnClickListener { dialog.hide() }
        dialog.show()
    }

    private fun addButtonClicked(nombre: String, correo: String, telefono: String, posicion: Int?, dialog: Dialog) {
        if (!nombre.isNotEmpty() || !correo.isNotEmpty() || !telefono.isNotEmpty()) {
            return
        }
        if (posicion != null) {

            registros[posicion] = Registro(nombre, correo, telefono)
        } else {
            registros.add(Registro(nombre, correo, telefono))
        }
        updateAdapter(posicion)
        dialog.hide()
    }

    private fun initComponents() {
        ftButton = findViewById(R.id.ftButton)
        rvList = findViewById(R.id.rvList)
    }

    private fun initUi() {
        rAdapter = Adapter(
            registroList = registros,
            onClickDelete = { onItemDelete(it) },
            onItemClickEdit = { editItem(it) }
        )
        rvList.layoutManager = manager
        rvList.adapter = rAdapter
    }

    private fun onItemDelete(posicion: Int) {
        registros.removeAt(posicion)
        rAdapter.notifyItemRemoved(posicion)
    }

    private fun editItem(posicion: Int) {
        displayDialog(posicion)
    }
    private fun updateAdapter(atPosition: Int?) {
        if (atPosition != null) {
            rAdapter.notifyItemChanged(atPosition)
        } else {
            rAdapter.notifyItemInserted(registros.lastIndex)
        }
    }
}