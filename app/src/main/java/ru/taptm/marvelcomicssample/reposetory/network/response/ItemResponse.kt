package ru.taptm.marvelcomicssample.reposetory.network.response

import com.google.gson.annotations.SerializedName

data class ItemResponse (
    @SerializedName("resourceURI") var resourceURI: String? = null,
    @SerializedName("name") var name: String? = null
)
