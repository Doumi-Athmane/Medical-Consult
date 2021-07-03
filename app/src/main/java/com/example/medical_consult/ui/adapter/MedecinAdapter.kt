package com.example.medical_consult.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.medical_consult.R
import com.example.medical_consult.data.model.Medecin

class MedecinAdapter (val context: Context, var data:List<Medecin>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.medecin_item, parent, false)
        )

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nom.text = data[position].fullName
        holder.adresse.text = data[position].city
        holder.spec.text = data[position].speciality
        holder.num.text = data[position].phone
        //holder.image.setImageResource(data[position].imageUrl)

        holder.appler.setOnClickListener { view ->

            val uri = Uri.parse("tel:"+holder.num.text)
            val intent = Intent(Intent.ACTION_DIAL, uri)
            context.startActivity(intent)

        }

        holder.geo.setOnClickListener {view ->
            val long = data[position].longitude
            val lat =data[position].latitude
            val uri = Uri.parse("geo:$long,$lat")
            val intent = Intent(Intent.ACTION_VIEW,uri)
            context.startActivity(intent)
        }

        holder.itemView.setOnClickListener { view ->
            /*val intent = Intent(context,MainActivity2::class.java)
            intent.putExtra("pr"
                ,data[position])
            context.startActivity(intent)*/
        }



    }
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nom = view.findViewById<TextView>(R.id.nomMedecin) as TextView
    val adresse = view.findViewById<TextView>(R.id.adresse) as TextView
    val image = view.findViewById<ImageView>(R.id.imageMedecin) as ImageView
    val num = view.findViewById<TextView>(R.id.num) as TextView
    val spec = view.findViewById<TextView>(R.id.specialté) as TextView
    val geo = view.findViewById<ImageView>(R.id.map) as ImageView
    val appler = view.findViewById<ImageView>(R.id.appler) as ImageView
}




