package com.example.medical_consult.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.medical_consult.R
import com.example.medical_consult.data.api.RetrofitService
import com.example.medical_consult.data.model.Conseil
import com.example.medical_consult.data.model.Conseil1
import com.example.medical_consult.data.model.Rdv
import kotlinx.android.synthetic.main.fragment_demande_conseil.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DemandeConseil : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demande_conseil, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        envy.setOnClickListener { view->
            var conss = Conseil(1,"request",conss.text.toString())
            addConseil(conss)
            Toast.makeText(
                requireActivity(),
                "Demande conseil envoyer",

                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun addConseil(con: Conseil){
        val call = RetrofitService.endpoint.postConseil(con)
        call.enqueue(object : Callback<Conseil1> {
            override fun onResponse(
                call: Call<Conseil1>?, response:
                Response<Conseil1>?
            ) {
                if (response?.isSuccessful!!) {
                    val data = response.body()

                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Erreur lors de la création",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

            override fun onFailure(call: Call<Conseil1>?, t: Throwable?) {
                Toast.makeText(requireActivity(), t.toString(), Toast.LENGTH_LONG).show()
            }
        })

    }
}