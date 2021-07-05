package com.example.medical_consult.ui.view.fragments

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medical_consult.R
import com.example.medical_consult.data.model.Rdv
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.fragment_confirmation_rdv.*
import kotlinx.android.synthetic.main.fragment_liste_medecins.*
import kotlinx.android.synthetic.main.fragment_prise_rdv.*
import kotlinx.android.synthetic.main.fragment_profil_medecin.*
import java.text.DateFormat

class ConfirmationRdv : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation_rdv, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        QR.setImageBitmap(getQrCodeBitmap(arguments?.getParcelable("rdv")))
        nommede.setText("Dr "+arguments?.getString("nom"))
        addd.setText(arguments?.getString("addr"))
        specc.setText(arguments?.getString("spec"))
        date.setText(arguments?.getString("jour"))
        horr.setText(arguments?.getString("hor"))

        acc.setOnClickListener { view ->

            val bundle = bundleOf()
            view?.findNavController()?.navigate(R.id.action_confirmationRdv_to_listeMedecinsFragment, bundle)

        }
        voirRDV.setOnClickListener { view->
            val bundle = bundleOf()
            view?.findNavController()?.navigate(R.id.action_confirmationRdv_to_listeRdv, bundle)

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