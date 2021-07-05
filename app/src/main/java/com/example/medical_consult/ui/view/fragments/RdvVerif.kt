package com.example.medical_consult.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.medical_consult.R
import kotlinx.android.synthetic.main.rdv_verif.*

class RdvVerif: Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        staterdv.text =  arguments?.getString("state","state")
        daterdv.text = arguments?.getString("date","date")
        patientrdv.text = arguments?.getString("patientId","patientId")
        horrairerdv.text = arguments?.getString("plageHorraireId","plageHorraireID")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.rdv_verif, container, false)
    }
}