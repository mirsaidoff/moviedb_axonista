package uz.mirsaidoff.moviedb.model

import com.google.gson.annotations.SerializedName

class ProductionCountry(
    @SerializedName("iso_3166_1") val iso: String,
    @SerializedName("name") val name: String
)