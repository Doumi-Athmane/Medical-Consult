package com.example.medical_consult.ui.view.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import com.example.medical_consult.R
import com.example.medical_consult.data.api.RetrofitService
import com.example.medical_consult.data.model.LoginRequest
import com.example.medical_consult.data.model.LoginResponse
import com.example.medical_consult.data.model.Medecin
import com.example.medical_consult.ui.adapter.MedecinAdapter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_liste_medecins.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_login)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        LoginButton.setOnClickListener {
            login();
        }
    }

    private fun login(){
        var email = EmailTextView.text.toString() ;
        var passwd = PasswordTextView.text.toString() ;
        var credentials = LoginRequest(email,passwd)
        var call = RetrofitService.endpoint.postLogin(credentials)
            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>?, response:Response<LoginResponse>?) {
                    if (response?.isSuccessful!!) {
                        val data: LoginResponse? = response.body()
                        if (data != null ) {
                            getContext().edit {
                                putBoolean("connected",true)
                                putInt("id",data.id)
                                putString("token",data.token)
                                putString("type",data.type)
                            }
                            switchToActivity();
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "Les entrees sont incorrectes", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                    Toast.makeText(this@LoginActivity,"L'utilisateur n'existe pas", Toast.LENGTH_SHORT).show()
                }
            })
    }
    fun getContext():SharedPreferences{
        return getSharedPreferences("MedicalConsultContext", Context.MODE_PRIVATE)
    }
    fun switchToActivity(){
        when(getContext().getString("type","")){
            "patient" -> switchToPatientActivity()
            "medecin" -> switchToMedecinActivity()
            else -> {
                Toast.makeText(this,"Le type d'utlisateur est errone", Toast.LENGTH_SHORT).show()
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
}