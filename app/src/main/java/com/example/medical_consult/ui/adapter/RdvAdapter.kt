package com.example.medical_consult.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medical_consult.R
import com.example.medical_consult.data.model.Medecin
import com.example.medical_consult.data.model.Rdv
import com.example.medical_consult.data.model.Rdv_med
import kotlinx.android.synthetic.main.rdv_item.*

class RdvAdapter (val context: Context, var data:List<Rdv>) : RecyclerView.Adapter<MyViewHolder1>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder1 {
        return MyViewHolder1(
            LayoutInflater.from(context).inflate(R.layout.rdv_item, parent, false)
        )

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder1, position: Int) {

        holder.time.text = data[position].date
        when (data[position].plageHorraireId) {
            1 -> holder.horr.text = "De 9h à 10h"
            2 -> holder.horr.text = "De 10h à 11h"
            3 -> holder.horr.text = "De 11h à 12h"
            4 -> holder.horr.text = "De 13h à 14h"
            5 -> holder.horr.text = "De 14h à 15h"
            6 -> holder.horr.text = "De 15h à 16h"
            7 -> holder.horr.text = "De 16h à 17h"
            else -> holder.horr.text = "De 9h à 10h"
        }



        holder.voirplus.setOnClickListener { view ->

            val bundle = bundleOf("id" to data[position].medecinId,"plage" to holder.horr.text, "jour" to data[position].date)

            view?.findNavController()?.navigate(R.id.action_listeRdv_to_monRdv , bundle)
        }



    }


}

class MyViewHolder1(view: View) : RecyclerView.ViewHolder(view) {
    val horr = view.findViewById<TextView>(R.id.horraire) as TextView
    val time = view.findViewById<TextView>(R.id.time) as TextView
    val voirplus = view.findViewById<ImageView>(R.id.voirplus) as ImageView

}