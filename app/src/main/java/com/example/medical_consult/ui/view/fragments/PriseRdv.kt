package com.example.medical_consult.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.medical_consult.R
import com.example.medical_consult.data.api.RetrofitService
import com.example.medical_consult.data.model.Medecin
import com.example.medical_consult.data.model.Rdv
import com.example.medical_consult.ui.adapter.MedecinAdapter
import kotlinx.android.synthetic.main.fragment_liste_medecins.*
import kotlinx.android.synthetic.main.fragment_prise_rdv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PriseRdv : Fragment() {

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val plages = resources.getStringArray(R.array.Phorraire)

        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item, plages
        )

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                Toast.makeText(requireActivity(),
                    getString(R.string.selected_item) + " " +
                            "" + plages[position], Toast.LENGTH_SHORT).show()
            }
            /*override fun onItemSelected(
                parent: AdapterView<*>?, view: View,
                position: Int, id: Long
            ) {
                parent?.getItemAtPosition(position)
            }*/

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        }
    }*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prise_rdv, container, false)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val plages = resources.getStringArray(R.array.Phorraire)
        var hor = 0
        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item, plages
        )

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {

                hor = position
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        }
        nomMed.setText("Dr " + arguments?.getString("nom"))
        adrs.setText(arguments?.getString("addr"))
        special.setText(arguments?.getString("spec"))






        prendreRDV.setOnClickListener { view ->

            val id = arguments?.getInt("id")!!
            val date = jour.text.toString()
            var rdv = Rdv(hor, id, 10, "confirmed", date)
            addRDV(rdv)
        }
    }
    private fun addRDV(rdv: Rdv){
        val call = RetrofitService.endpoint.addRDV(rdv)
        call.enqueue(object : Callback<Rdv> {
            override fun onResponse(
                call: Call<Rdv>?, response:
                Response<Rdv>?
            ) {
                if (response?.isSuccessful!!) {
                    val  data = response.body()


                    Toast.makeText(requireActivity(), "Création réussite", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Erreur lors de la création",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

            override fun onFailure(call: Call<Rdv>?, t: Throwable?) {
                Toast.makeText(requireActivity(), t.toString(), Toast.LENGTH_LONG).show()
            }
        })

    }
/*
    private fun getRdv(date : String){
        val call = RetrofitService.endpoint.getRdvByMed(date)
        call.enqueue(object: Callback<List<Int>> {
            override fun onResponse(call: Call<List<Int>>?, response:
            Response<List<Int>>?){
                if(response?.isSuccessful!!){
                    val  data:List<Int>? = response.body()

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
*/



}