package uz.mirsaidoff.moviedb.data.model

import com.google.gson.annotations.SerializedName

class ProductionCompany(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("origin_country") val originCountry: String
)