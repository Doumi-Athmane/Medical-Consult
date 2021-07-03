package com.example.medical_consult.data.model

import java.io.Serializable

data class Medecin (
        var id:Int ,
        var fullName:String,
        var phone:String,
        var city:String,
        var speciality:String,
        var longitude:Float,
        var latitude:Float,
        var imageUrl:String,
        var userId:Int
        ):Serializable