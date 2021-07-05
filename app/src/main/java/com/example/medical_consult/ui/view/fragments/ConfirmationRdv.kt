package com.example.medical_consult.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medical_consult.R
import kotlinx.android.synthetic.main.fragment_confirmation_rdv.*
import kotlinx.android.synthetic.main.fragment_liste_medecins.*
import kotlinx.android.synthetic.main.fragment_profil_medecin.*

class ConfirmationRdv : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation_rdv, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       // var qrgEncoder = QRGEncoder(inputValue, null, QRGContents.Type.TEXT, 1);
        nommede.setText("Dr "+arguments?.getString("nom"))
        addd.setText(arguments?.getString("addr"))
        specc.setText(arguments?.getString("spec"))
        date.setText(arguments?.getString("jour"))
        horr.setText(arguments?.getString("hor"))

    }

}