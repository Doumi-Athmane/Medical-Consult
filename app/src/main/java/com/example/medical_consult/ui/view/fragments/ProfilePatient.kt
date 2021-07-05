package com.example.medical_consult.ui.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.medical_consult.R
import com.example.medical_consult.data.api.RetrofitService
import com.example.medical_consult.data.model.Patient
import com.example.medical_consult.data.model.Rdv
import com.example.medical_consult.data.model.User
import kotlinx.android.synthetic.main.fragment_profile_patient.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfilePatient : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_patient, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var preferences = this.activity?.getSharedPreferences("MedicalConsultContext", Context.MODE_PRIVATE)

        var user = getPatient(preferences?.getInt("id",0)!!)

    }

    private fun getPatient(idPatientt: Int):User{
        val call = RetrofitService.endpoint.getPatientById(idPatientt)
        var data:User = User(2,"","","",3,"","")
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                if (response?.isSuccessful!!) {
                    data = response.body()!!
                    nomPatient.setText(data?.fullName)
                    numPatient.setText(data?.phone)


                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Erreur lors de la création",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Toast.makeText(requireActivity(), t.toString(), Toast.LENGTH_LONG).show()
            }
        })
        Toast.makeText(requireActivity(), ""+ data, Toast.LENGTH_LONG).show()

        return data
    }

}