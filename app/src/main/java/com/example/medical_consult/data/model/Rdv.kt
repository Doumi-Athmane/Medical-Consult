package com.example.medical_consult.data.model

import java.io.Serializable

data class Rdv (

        var plageHorraireId:Int,
        var medecinId:Int,
        var patientId:Int,
        var state:String,
        var date:String
        ):Serializable