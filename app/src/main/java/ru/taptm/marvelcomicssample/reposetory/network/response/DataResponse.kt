package ru.taptm.marvelcomicssample.reposetory.network.response

import com.google.gson.annotations.SerializedName

data class DataResponse  (
    @SerializedName("offset") var offset: Int? = null,
    @SerializedName("limit") var limit: Int? = null,
    @SerializedName("total") var total: Int? = null,
    @SerializedName("count") var count: Int? = null,
    @SerializedName("results") var results: List<ResultResponse>? = null
)
