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
import com.example.medical_consult.data.model.Traitement
import com.example.medical_consult.data.model.Traitement1
import com.example.medical_consult.ui.view.fragments.ProfilMedecin
import kotlinx.android.synthetic.main.fragment_confirmation_rdv.*

class TraitementAdapter (val context: Context, var data:List<Traitement1>) : RecyclerView.Adapter<MyViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
        return MyViewHolder2(
            LayoutInflater.from(context).inflate(R.layout.traitement_item, parent, false)
        )

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {
        holder.date.text = "Le "+data[position].date
        holder.desc.text = "Description : "+data[position].description
        holder.nom.text = "Dr "+data[position].medecinNom


        holder.vplus.setOnClickListener {view ->
            val bundle = bundleOf("id" to data[position].medecinId,"nom" to data[position].medecinNom,"add" to data[position].medecinCity ,
                    "spec" to data[position].medecinSpeciality,"date" to data[position].date,"desc" to data[position].description)

            view?.findNavController()?.navigate(R.id.action_listeTraitement_to_monTraitement , bundle)
        }





    }
}

class MyViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
    val nom = view.findViewById<TextView>(R.id.nomMM) as TextView
    val date = view.findViewById<TextView>(R.id.dateRDV) as TextView
    val desc = view.findViewById<TextView>(R.id.desc) as TextView
    val vplus = view.findViewById<ImageView>(R.id.voirtrt) as ImageView

}




