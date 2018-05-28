package ru.taptm.marvelcomicssample.reposetory.network.response

import com.google.gson.annotations.SerializedName

data class PriceResponse(
    @SerializedName("type") var type: String? = null,
    @SerializedName("price") var price: Double? = null
)
