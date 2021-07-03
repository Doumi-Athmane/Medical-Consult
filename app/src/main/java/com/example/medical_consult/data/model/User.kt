package com.example.medical_consult.data.model

import java.io.Serializable

data class User (
        var id :Int,
        var email: String,
        var password:String,
        var type:String
        ):Serializable