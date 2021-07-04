package com.example.medical_consult.data.api

import retrofit2.Call
import com.example.medical_consult.data.model.*
import retrofit2.http.*

interface EndPoint {
    @POST("/conseil/add")
    fun postConseil(@Body conseil:Conseil): Call<Conseil>

    @GET("/conseil/{id}")
    fun getConseilByTraitement(@Path("id") id:Int?): Call<List<Conseil>>

    @GET("/auth/medecins")
    fun getMedecins(): Call<List<Medecin>>

    @GET("/auth/medecins/{id}")
    fun getMedecinByID(): Call<Medecin>

    @POST("/rdv/add")
    fun addRDV(@Body rdv: Rdv): Call<Rdv>

    @POST("/rdv/pris/{id}")
    fun getRdvByMed(@Body date: String): Call<List<Int>>
}