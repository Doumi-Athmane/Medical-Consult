package com.example.medical_consult.ui.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medical_consult.R
import com.example.medical_consult.data.api.RetrofitService
import com.example.medical_consult.data.model.Medecin
import com.example.medical_consult.data.model.Patient
import com.example.medical_consult.data.model.PlageHorraire
import com.example.medical_consult.data.model.Rdv
import com.example.medical_consult.ui.adapter.MedecinAdapter
import com.example.medical_consult.ui.adapter.RdvAdapter
import kotlinx.android.synthetic.main.fragment_liste_medecins.*
import kotlinx.android.synthetic.main.fragment_liste_medecins.rView
import kotlinx.android.synthetic.main.fragment_liste_rdv.*
import kotlinx.android.synthetic.main.rdv_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListeRdv : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste_rdv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        rView1.layoutManager = LinearLayoutManager(requireActivity())
        super.onActivityCreated(savedInstanceState)
        var preferences = this.activity?.getSharedPreferences("MedicalConsultContext", Context.MODE_PRIVATE)
        loadDataFromBDDRdv(preferences?.getInt("id",0)!!)



    }


    fun getPlageHorraire(id:Int):String {
        var call = RetrofitService.endpoint.getPlageHorraire(id)
        var ret = ""
        try {
            call.enqueue( object : Callback<PlageHorraire> {
                override fun onResponse(call: Call<PlageHorraire>?, response: Response<PlageHorraire>?) {
                    if (response?.isSuccessful!!) {
                        val data: PlageHorraire? = response.body()
                        if (data != null ) {
                            ret =  "${data.heureDebut}H - ${data.heureFin}H"
                            Toast.makeText(activity, ret, Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(activity, "Les entrees sont incorrectes", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<PlageHorraire>?, t: Throwable?) {
                    Toast.makeText(activity,"plage horraire n'existant pas", Toast.LENGTH_SHORT).show()
                }
            })
        }catch (e:Exception){
            Toast.makeText(activity,"plage horraire n'existant pas", Toast.LENGTH_SHORT).show()
        }
        return ret
    }


    private fun loadDataFromBDDRdv(id:Int){
        val call = RetrofitService.endpoint.getRdvByPatient(id)

        call.enqueue(object: Callback<List<Rdv>> {
            override fun onResponse(call: Call<List<Rdv>>?, response:
            Response<List<Rdv>>?){
                if(response?.isSuccessful!!){
                    val  data:List<Rdv>? = response.body()

                    if(data != null){

                        rView1.adapter = RdvAdapter(requireActivity(),data)
                    }
                }else{

                    Toast.makeText(requireActivity(),"Erreur",Toast.LENGTH_LONG).show()

                }
            }
            override fun onFailure(call: Call<List<Rdv>>?, t: Throwable?) {
                Toast.makeText(requireActivity(),t.toString(),Toast.LENGTH_LONG).show()
            }
        })

    }



}