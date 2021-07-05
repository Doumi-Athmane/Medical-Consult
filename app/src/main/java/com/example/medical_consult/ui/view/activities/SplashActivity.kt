package com.example.medical_consult.ui.view.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.HandlerCompat.postDelayed
import com.example.medical_consult.R
import okhttp3.internal.http2.Http2Reader

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            if(verifLoged()){
                switchToActivity()
            }else{
                switchToLoginActivity()
            }
        }, 1500)
    }

    fun getContext(): SharedPreferences {
        return getSharedPreferences("MedicalConsultContext", Context.MODE_PRIVATE)
    }
    fun verifLoged():Boolean{
        return getContext().getBoolean("connected", false) ;
    }
    fun switchToActivity(){
        when(getContext().getString("type","")){
            "patient" -> switchToPatientActivity()
            "medecin" -> switchToMedecinActivity()
            else -> {
                switchToLoginActivity()
            }
        }
    }
    fun switchToPatientActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun switchToMedecinActivity(){
        val intent = Intent(this, MedecinActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun switchToLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}