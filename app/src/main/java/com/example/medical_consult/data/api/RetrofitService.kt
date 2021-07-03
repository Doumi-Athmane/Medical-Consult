package com.example.medical_consult.data.api



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val endpoint :EndPoint by lazy {
        Retrofit.Builder().baseUrl("https://tdm-back.herokuapp.com/"). addConverterFactory(
            GsonConverterFactory.create()). build().create(EndPoint::class.java)
    }
}

