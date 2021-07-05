package com.example.medical_consult.data.api

import retrofit2.Call
import com.example.medical_consult.data.model.*
import retrofit2.http.*

interface EndPoint {
    @POST("/conseil/add")
    fun postConseil(@Body conseil:Conseil): Call<Conseil1>

    @GET("/conseil/{id}")
    fun getConseilByTraitement(@Path("id") id:Int?): Call<List<Conseil>>
    @GET("/rdv/patient/{id}")
    fun getRdvByPatient(@Path("id") id:Int?): Call<List<Rdv>>

    @GET("/auth/traitements")
    fun getTraiements(): Call<List<Traitement>>

    @GET("/traitment/patient/{id}")
    fun getTraiementByPatient(@Path("id") id:Int?): Call<List<Traitement1>>

    @GET("/auth/medecins")
    fun getMedecins(): Call<List<Medecin>>


    @POST("/auth/")
    fun postLogin(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/rdv/verif/{id}")
    fun verifRdv(@Path("id") id:Int?,@Body rdv:Rdv): Call<verifResponse>
    @DELETE("/rdv/{id}")
    fun suppRDV(@Path("id") id:Int?):Call<verifResponse>
    @GET("/auth/{id}")
    fun getPatientById(@Path("id") id:Int?): Call<User>
    @GET("/horraire/{id}")
    fun getPlageHorraire(@Path("id") id:Int?): Call<PlageHorraire>

    @GET("/auth/patient/{id}")
    fun getPatient(@Path("id") id:Int?): Call<Patient>

    @GET("/auth/medecin/{id}")
    fun getMedecinByID(@Path("id") id:Int?): Call<Medecin>

    @POST("/rdv/add")
    fun addRDV(@Body rdv: Rdv): Call<Rdv>

    @POST("/rdv/pris/{id}")
    fun getRdvByMed(@Body date: String): Call<List<Int>>
}