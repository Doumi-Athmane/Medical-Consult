package com.example.medical_consult.data.model

import java.io.Serializable

data class Rdv_med (
    var nommed :String,
    var addr : String,
    var Spec : String,
    var date : String,
    var qr : String
        ):Serializable