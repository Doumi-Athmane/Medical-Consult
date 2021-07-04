package com.example.medical_consult.ui.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.medical_consult.R
import com.example.medical_consult.data.api.RetrofitService
import com.example.medical_consult.data.model.Medecin
import com.example.medical_consult.ui.adapter.MedecinAdapter
import kotlinx.android.synthetic.main.fragment_liste_medecins.*
import kotlinx.android.synthetic.main.fragment_profil_medecin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfilMedecin : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil_medecin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        nom.setText("Dr "+arguments?.getString("nom"))
        addr.setText(arguments?.getString("addr"))
        nume.setText(arguments?.getString("num"))
        spec.setText(arguments?.getString("spec"))
        Glide.with(requireActivity()).load("https://tdm-back.herokuapp.com/"+arguments?.getString("image")).into(image)


        apple.setOnClickListener { view ->

            val uri = Uri.parse("tel:"+nume.text)
            val intent = Intent(Intent.ACTION_DIAL, uri)
            requireActivity().startActivity(intent)

        }

        rdv.setOnClickListener { view ->

            val bundle = bundleOf("id" to arguments?.getInt("id"),
                "nom" to arguments?.getString("nom") , "addr" to arguments?.getString("addr") ,
                "num" to arguments?.getString("num")
                , "spec" to arguments?.getString("spec") , "image" to arguments?.getString("image"))

            view?.findNavController()?.navigate(R.id.action_profilMedecin_to_priseRdv , bundle)

        }
    }




}