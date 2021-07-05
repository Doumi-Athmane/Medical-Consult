package com.example.medical_consult.data.model

import java.io.Serializable

data class Conseil1(
    var id:Int,
    var traitementId:Int,
    var type:String,
    var description:String
):Serializable