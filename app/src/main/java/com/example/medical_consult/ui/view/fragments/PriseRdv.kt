package com.example.medical_consult.ui.view.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.medical_consult.R
import kotlinx.android.synthetic.main.fragment_prise_rdv.*
import kotlinx.android.synthetic.main.fragment_profil_medecin.*


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
        nomMed.setText("Dr "+arguments?.getString("nom"))
        adrs.setText(arguments?.getString("addr"))
        special.setText(arguments?.getString("spec"))


        prendreRDV.setOnClickListener { view ->



        }
    }



}