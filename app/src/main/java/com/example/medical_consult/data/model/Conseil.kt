package com.example.medical_consult.data.model

import java.io.Serializable

data class Conseil(

        var traitmentId:Int,
        var type:String,
        var description:String
):Serializable