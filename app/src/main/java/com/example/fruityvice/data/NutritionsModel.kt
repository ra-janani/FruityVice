package com.example.fruityvice.data


import com.google.gson.annotations.SerializedName

data class NutritionsModel(
    @SerializedName("calories")
    val calories: Int? = 0,
    @SerializedName("carbohydrates")
    val carbohydrates: Double? = 0.0,
    @SerializedName("fat")
    val fat: Double? = 0.0,
    @SerializedName("protein")
    val protein: Double? = 0.0,
    @SerializedName("sugar")
    val sugar: Double? = 0.0
)