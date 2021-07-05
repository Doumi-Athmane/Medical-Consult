package com.example.medical_consult.ui.view.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
//import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.medical_consult.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medical_consult.ui.adapter.MedecinAdapter
import com.example.medical_consult.data.model.Medecin
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.medecin_item.*
import kotlinx.android.synthetic.main.medecin_item.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nav = findNavController(R.id.nav_host)
        setupActionBarWithNavController(nav)

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)
        return navController.navigateUp()
    }
}