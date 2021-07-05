package com.example.medical_consult.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.medical_consult.R
import kotlinx.android.synthetic.main.fragment_mon_traitement.*
import kotlinx.android.synthetic.main.fragment_profil_medecin.*


class monTraitement : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mon_traitement, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        nomMedd.setText("Dr " + arguments?.getString("nom"))
        Addr.setText(arguments?.getString("add"))
        Desc.setText(arguments?.getString("desc"))
        Spec.setText(arguments?.getString("spec"))
        Date.setText("Date: "+arguments?.getString("date"))

        Dconseil.setOnClickListener { view ->

            val bundle = bundleOf("idMed" to arguments?.getInt("id"))

            view?.findNavController()?.navigate(R.id.action_monTraitement_to_demandeConseil , bundle)

        }

    }

}