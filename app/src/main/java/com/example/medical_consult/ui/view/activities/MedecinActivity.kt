package com.example.medical_consult.ui.view.activities

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.budiyev.android.codescanner.*
import com.example.medical_consult.R

class MedecinActivity : AppCompatActivity() {

    val  MY_CAMERA_PERMISSION = 1111
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()
        setContentView(R.layout.activity_medecin)
        val verifFrag = findNavController(R.id.rdv_verif_frag)
        setupActionBarWithNavController(verifFrag)
    }
    fun checkPermission(){
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),MY_CAMERA_PERMISSION)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.rdv_verif_frag)
        return navController.navigateUp()
    }
}