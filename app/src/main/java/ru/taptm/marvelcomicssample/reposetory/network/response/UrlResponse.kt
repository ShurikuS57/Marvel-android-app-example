package ru.taptm.marvelcomicssample.reposetory.network.response

import com.google.gson.annotations.SerializedName

data class UrlResponse (
    @SerializedName("type") var type: String? = null,
    @SerializedName("url") var url: String? = null
)
