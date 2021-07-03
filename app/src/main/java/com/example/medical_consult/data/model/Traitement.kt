package com.example.medical_consult.data.model

import java.io.Serializable

data class Traitement (
        var id : Int,
        var rdvId: Int,
        var description:String
        ):Serializable