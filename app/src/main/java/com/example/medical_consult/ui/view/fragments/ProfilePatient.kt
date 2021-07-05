package com.example.medical_consult.ui.view.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.TextKeyListener.clear
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
import com.example.medical_consult.ui.view.activities.MedecinActivity
import com.example.medical_consult.ui.view.activities.SplashActivity
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


        nomPatient.setText(preferences?.getString("fullName","Nom patient"))
        numPatient.setText(preferences?.getString("phone","Numero de telephone"))
        decnxPatient.setOnClickListener {
            preferences?.edit()?.clear()?.apply();
            val intent = Intent(activity, SplashActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }


    }

}