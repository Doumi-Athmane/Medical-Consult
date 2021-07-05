package com.example.medical_consult.data.model
import java.io.Serializable

data class Traitement1 (
    var plageHorraireId:Int,
    var medecinId:Int,
    var patientId:Int,
    var medecinNom:String,
    var medecinSpeciality:String,
    var medecinCity:String,
    var date:String,
    var id : Int,
    var rdvId: Int,
    var description:String
):Serializable