package com.example.medical_consult.data.model

import java.io.Serializable

data class LoginResponse (
    var id:Int ,
    var type:String,
    var token:String
    ):Serializable
