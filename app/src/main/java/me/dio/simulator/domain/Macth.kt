package me.dio.simulator.domain

import android.os.Parcelable
import.kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Macth (
    @SerializedName("descrição")
    val description: String,
    @SerializedName("local")
    val place: Place,
    @SerializedName("mandante")
    val homeTeam: Team,
    @SerializedName("visitante")
    val awayTeam: Team

        ) :Parcelable