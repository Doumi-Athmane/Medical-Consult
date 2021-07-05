package com.example.medical_consult.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medical_consult.R
import com.example.medical_consult.data.model.Medecin
import com.example.medical_consult.data.model.Rdv
import com.example.medical_consult.data.model.Rdv_med
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.fragment_confirmation_rdv.*
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


        holder.QrList.setImageBitmap(getQrCodeBitmap(data[position]))

        holder.rdvItemlayout.setOnClickListener { view ->

            val bundle = bundleOf("id" to data[position].medecinId,"plage" to holder.horr.text, "jour" to data[position].date)
            bundle.putParcelable("rdv",data[position])
            view?.findNavController()?.navigate(R.id.action_listeRdv_to_monRdv , bundle)
        }



    }

    fun getQrCodeBitmap(data:Rdv?): Bitmap {
        val qrCodeContent = "${data?.id}:${data?.plageHorraireId}:${data?.medecinId}:${data?.patientId}:${data?.state}:${data?.date}"
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 } // Make the QR code buffer border narrower
        val size = 512 //pixels
        val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size, hints)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }


}

class MyViewHolder1(view: View) : RecyclerView.ViewHolder(view) {
    val horr = view.findViewById<TextView>(R.id.horraire) as TextView
    val time = view.findViewById<TextView>(R.id.time) as TextView
    val voirplus = view.findViewById<ImageView>(R.id.voirplus) as ImageView
    val rdvItemlayout = view.findViewById<ConstraintLayout>(R.id.rdvItemlayout) as ConstraintLayout
    val QrList = view.findViewById<ImageView>(R.id.QRListitem) as ImageView

}