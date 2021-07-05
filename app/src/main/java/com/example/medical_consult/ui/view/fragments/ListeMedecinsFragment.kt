package com.example.medical_consult.ui.view.fragments

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
import com.example.medical_consult.ui.adapter.MedecinAdapter
import kotlinx.android.synthetic.main.fragment_liste_medecins.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListeMedecinsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_liste_medecins, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        rView.layoutManager = LinearLayoutManager(requireActivity())
        super.onActivityCreated(savedInstanceState)
        loadDataFromBDD()

        Profile.setOnClickListener { view->
            val bundle = bundleOf()
            view?.findNavController()?.navigate(R.id.action_listeMedecinsFragment_to_profilePatient, bundle)
        }
        rdvv.setOnClickListener { view->
            val bundle = bundleOf()
            view?.findNavController()?.navigate(R.id.action_listeMedecinsFragment_to_listeRdv, bundle)
        }

        trt.setOnClickListener { view->
            val bundle = bundleOf()
            view?.findNavController()?.navigate(R.id.action_listeMedecinsFragment_to_listeTraitement, bundle)
        }
    }

    private fun loadDataFromBDD(){
        val call = RetrofitService.endpoint.getMedecins()
        call.enqueue(object: Callback<List<Medecin>> {
            override fun onResponse(call: Call<List<Medecin>>?, response:
            Response<List<Medecin>>?){
                if(response?.isSuccessful!!){
                    val  data:List<Medecin>? = response.body()

                    if(data != null){
                        rView.adapter = MedecinAdapter(requireActivity(),data)
                    }
                }else{

                    Toast.makeText(requireActivity(),"Erreur",Toast.LENGTH_LONG).show()

                }
            }
            override fun onFailure(call: Call<List<Medecin>>?, t: Throwable?) {
                Toast.makeText(requireActivity(),t.toString(),Toast.LENGTH_LONG).show()
            }
        })

    }







}