package com.example.medical_consult.ui.view.fragments

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.medical_consult.R
import com.example.medical_consult.data.api.RetrofitService
import com.example.medical_consult.data.model.Rdv
import kotlinx.android.synthetic.main.fragment_liste_medecins.*
import kotlinx.android.synthetic.main.fragment_prise_rdv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class PriseRdv : Fragment() {


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
        var plagehor = ""
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
                plagehor = plages[position]
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
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val date: String = sdf.format(Date(jour.date))
            //Toast.makeText(requireActivity(), date, Toast.LENGTH_LONG).show()
            var rdv = Rdv(hor, id, 10, "confirmed", date)
            addRDV(rdv)

            val bundle = bundleOf("id" to arguments?.getInt("id"),
                    "nom" to arguments?.getString("nom") , "addr" to arguments?.getString("addr") ,
                    "num" to arguments?.getString("num")
                    , "spec" to arguments?.getString("spec") , "jour" to date , "hor" to plagehor)

            view?.findNavController()?.navigate(R.id.action_priseRdv_to_confirmationRdv , bundle)

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
                    val data = response.body()


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




}