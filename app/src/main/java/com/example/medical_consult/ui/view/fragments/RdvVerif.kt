package com.example.medical_consult.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.medical_consult.R
import com.example.medical_consult.data.api.RetrofitService
import com.example.medical_consult.data.model.Patient
import com.example.medical_consult.data.model.PlageHorraire
import kotlinx.android.synthetic.main.rdv_verif.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RdvVerif: Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        staterdv.text =  arguments?.getString("state","state")
        daterdv.text = arguments?.getString("date","date")
        setPlageHorraire(arguments?.getInt("plageHorraireId",0))
        setPatientName(arguments?.getInt("patientId",0))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.rdv_verif, container, false)
    }


    fun setPlageHorraire(id:Int?):String {
        var call = RetrofitService.endpoint.getPlageHorraire(id)
        var ret = ""
        try {
            call.enqueue( object : Callback<PlageHorraire> {
                override fun onResponse(call: Call<PlageHorraire>?, response: Response<PlageHorraire>?) {
                    if (response?.isSuccessful!!) {
                        val data: PlageHorraire? = response.body()
                        if (data != null ) {
                            ret =  "${data.heureDebut}H - ${data.heureFin}H"
                            horrairerdv.text = ret
                        }
                    } else {
                        Toast.makeText(activity, "Les entrees sont incorrectes", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<PlageHorraire>?, t: Throwable?) {
                    Toast.makeText(activity,"l'horraire'n'existant pas", Toast.LENGTH_SHORT).show()
                }
            })
        }catch (e:Exception){
            Toast.makeText(activity,"l'horraire n'existant pas", Toast.LENGTH_SHORT).show()
        }finally {
            return ret
        }
    }

    fun setPatientName(id:Int?):String {
        var call = RetrofitService.endpoint.getPatient(id)
        var ret = ""
        try {
            call.enqueue( object : Callback<Patient> {
                override fun onResponse(call: Call<Patient>?, response: Response<Patient>?) {
                    if (response?.isSuccessful!!) {
                        val data: Patient? = response.body()
                        if (data != null ) {
                            ret = data.fullName
                            //Toast.makeText(activity, data.toString(), Toast.LENGTH_LONG).show()
                            patientrdv.text= ret
                        }
                    } else {
                        Toast.makeText(activity, "Les entrees sont incorrectes", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<Patient>?, t: Throwable?) {
                    Toast.makeText(activity,"le patient n'existant pas", Toast.LENGTH_SHORT).show()
                }
            })
        }catch (e:Exception){
            Toast.makeText(activity,"le patient n'existant pas", Toast.LENGTH_SHORT).show()
        }finally {
            return ret
        }
    }


}