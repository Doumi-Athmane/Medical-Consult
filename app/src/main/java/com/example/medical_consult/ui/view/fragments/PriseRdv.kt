package com.example.medical_consult.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
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
import kotlinx.android.synthetic.main.fragment_prise_rdv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.text.DateFormat
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
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {

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

        val calendar = Calendar.getInstance()

        jour.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)

            jour.date = calendar.timeInMillis



        }
        var preferences = this.activity?.getSharedPreferences("MedicalConsultContext", Context.MODE_PRIVATE)

        prendreRDV.setOnClickListener { view ->

            val id = arguments?.getInt("id")!!
            val selectedDate:Long = jour.date
            calendar.timeInMillis = selectedDate
            val dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM)
            var date = dateFormatter.format(calendar.time)
            if (hor != 0) {
                var rdv = Rdv(hor, id, preferences?.getInt("patientId",0)!!, "confirmed", date)
                addRDV(rdv,date,plagehor)
            }
            else{
                Toast.makeText(requireActivity(), "Veuillez selectionner un creneau horraire", Toast.LENGTH_LONG).show()
            }

        }
    }
    private fun addRDV(rdv: Rdv,date:String,plagehor:String){
        val call = RetrofitService.endpoint.addRDV(rdv)
        call.enqueue(object : Callback<Rdv> {
            override fun onResponse(
                call: Call<Rdv>?, response:
                Response<Rdv>?
            ) {
                if (response?.isSuccessful!!) {
                    val data = response.body()
                    try {
                        if(data!=null){
                            Toast.makeText(requireActivity(), "Comfirmation avec succes !", Toast.LENGTH_LONG).show()

                            val bundle = bundleOf(
                                    "id" to arguments?.getInt("id"),
                                    "nom" to arguments?.getString("nom"),
                                    "addr" to arguments?.getString("addr"),
                                    "num" to arguments?.getString("num"),
                                    "spec" to arguments?.getString("spec"),
                                    "jour" to date,
                                    "hor" to plagehor
                            )
                            bundle.putParcelable("rdv",data)
                            view?.findNavController()?.navigate(R.id.action_priseRdv_to_confirmationRdv, bundle)
                        }
                    }
                    catch (e:Exception){
                        Toast.makeText(requireActivity()," Erreur voici le result ${data.toString()}", Toast.LENGTH_LONG).show()
                    }

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