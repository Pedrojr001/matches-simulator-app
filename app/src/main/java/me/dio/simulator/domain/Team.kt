package me.dio.simulator.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Team (
    @SerializedName("nome")
    val name: String,
    @SerializedName("força")
    val stars: Int,
    @SerializedName("imagem")
    val image: String,
    var score: Int?

        ): Parcelable