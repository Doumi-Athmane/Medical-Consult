package com.example.medical_consult.ui.view.fragments

import android.content.Intent
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
import kotlinx.android.synthetic.main.fragment_mon_rdv.*
import kotlinx.android.synthetic.main.fragment_profil_medecin.*
import kotlinx.android.synthetic.main.fragment_profile_patient.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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


        annulerRdv.setOnClickListener { view ->
            SuppRDV(arguments?.getInt("idRdv")!!)
            val bundle = bundleOf()
            view?.findNavController()?.navigate(R.id.action_monRdv_to_listeRdv, bundle)
        }
    }

    private fun SuppRDV(id : Int){
        val call = RetrofitService.endpoint.suppRDV(id)
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
                    Toast.makeText(requireActivity(), ""+ data, Toast.LENGTH_LONG).show()



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