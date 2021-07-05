package com.example.medical_consult.ui.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medical_consult.R
import com.example.medical_consult.data.api.RetrofitService
import com.example.medical_consult.data.model.Traitement1
import com.example.medical_consult.ui.adapter.TraitementAdapter
import kotlinx.android.synthetic.main.fragment_liste_traitement.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListeTraitement : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste_traitement, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        rView5.layoutManager = LinearLayoutManager(requireActivity())
        super.onActivityCreated(savedInstanceState)
        var preferences = this.activity?.getSharedPreferences("MedicalConsultContext", Context.MODE_PRIVATE)
        loadDataFromBDD((preferences?.getInt("id",0)!!))
    }

    private fun loadDataFromBDD(idr:Int){
        val call = RetrofitService.endpoint.getTraiementByPatient(idr)
        call.enqueue(object: Callback<List<Traitement1>> {
            override fun onResponse(call: Call<List<Traitement1>>?, response:
            Response<List<Traitement1>>?){

                if(response?.isSuccessful!!){
                    val  data:List<Traitement1>? = response.body()

                    if(data != null){
                        rView5.adapter = TraitementAdapter(requireActivity(),data)
                    }
                }else{

                    Toast.makeText(requireActivity(),"Erreur!!!"+response.body(), Toast.LENGTH_LONG).show()

                }
            }
            override fun onFailure(call: Call<List<Traitement1>>?, t: Throwable?) {
                Toast.makeText(requireActivity(),t.toString(), Toast.LENGTH_LONG).show()
            }
        })

    }

}