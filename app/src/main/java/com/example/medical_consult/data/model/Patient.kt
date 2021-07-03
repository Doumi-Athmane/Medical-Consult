package com.example.medical_consult.data.model

import java.io.Serializable

data class Patient (
        var id:Int,
        var fullName:String,
        var phone:String,
        var userId:Int
        ):Serializable
