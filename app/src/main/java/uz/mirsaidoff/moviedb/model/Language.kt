package uz.mirsaidoff.moviedb.model

import com.google.gson.annotations.SerializedName

class Language(
    @SerializedName("iso_639_1") val iso: String,
    @SerializedName("name") val name: String
)