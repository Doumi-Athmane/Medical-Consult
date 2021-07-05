package com.example.medical_consult.data.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


data class Rdv(

        var plageHorraireId: Int,
        var medecinId: Int,
        var patientId: Int,
        var state: String?,
        var date: String?,
        val id: Int? = null
):Serializable,Parcelable
{
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int) {
        }

        override fun describeContents(): Int {
                return 0
        }

        override fun writeToParcel(parcel: Parcel?, i: Int) {
                parcel?.writeInt(plageHorraireId)
                parcel?.writeInt(medecinId)
                parcel?.writeInt(patientId)
                parcel?.writeString(state)
                parcel?.writeString(date)
                parcel?.writeValue(id)
        }

        companion object CREATOR : Parcelable.Creator<Rdv> {
                override fun createFromParcel(parcel: Parcel): Rdv {
                        return Rdv(parcel)
                }

                override fun newArray(size: Int): Array<Rdv?> {
                        return arrayOfNulls(size)
                }
        }
}
