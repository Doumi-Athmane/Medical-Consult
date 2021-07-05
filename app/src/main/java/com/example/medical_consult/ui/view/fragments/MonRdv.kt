package com.example.medical_consult.ui.view.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.medical_consult.R
import com.example.medical_consult.data.api.RetrofitService
import com.example.medical_consult.data.model.Medecin
import com.example.medical_consult.data.model.Rdv
import com.example.medical_consult.data.model.User
import com.example.medical_consult.data.model.verifResponse
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.fragment_confirmation_rdv.*
import kotlinx.android.synthetic.main.fragment_mon_rdv.*
import kotlinx.android.synthetic.main.fragment_profil_medecin.*
import kotlinx.android.synthetic.main.fragment_profile_patient.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class MonRdv : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mon_rdv, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lejour.setText(arguments?.getString("jour"))
        lhorr.setText(arguments?.getString("plage"))
        getMedecin(arguments?.getInt("id")!!)
        var rdv:Rdv? = arguments?.getParcelable("rdv")
        QRMonrdv.setImageBitmap(getQrCodeBitmap(rdv))

        annulerRdv.setOnClickListener { view ->
            try {
                SuppRDV(rdv?.id)
            }catch (e:Exception){
                Toast.makeText(requireActivity(), e.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun SuppRDV(id : Int?){
        val call = RetrofitService.endpoint.suppRDV(id)
        call.enqueue(object : Callback<verifResponse> {
            override fun onResponse(call: Call<verifResponse>?, response: Response<verifResponse>?) {
                if (response?.isSuccessful!!) {
                    view?.findNavController()?.popBackStack()
                } else {
                    Toast.makeText(
                            requireActivity(),
                            "Erreur lors de la suppression !",
                            Toast.LENGTH_LONG
                    ).show()
                }
            }
            override fun onFailure(call: Call<verifResponse>?, t: Throwable?) {
                Toast.makeText(requireActivity(), t.toString(), Toast.LENGTH_LONG).show()
            }
        })
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

    private fun getMedecin(id: Int) {
        val call = RetrofitService.endpoint.getMedecinByID(id)
        var data: Medecin = Medecin(2,"","","","",2.0f,2.0f,"",0)
        call.enqueue(object : Callback<Medecin> {
            override fun onResponse(call: Call<Medecin>?, response: Response<Medecin>?) {
                if (response?.isSuccessful!!) {
                    data = response.body()!!
                    nomm.setText(data.fullName)
                    ad.setText(data.city)
                    speccc.setText(data.speciality)



                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Erreur lors de la création !",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

            override fun onFailure(call: Call<Medecin>?, t: Throwable?) {
                Toast.makeText(requireActivity(), t.toString(), Toast.LENGTH_LONG).show()
            }
        })


    }

}