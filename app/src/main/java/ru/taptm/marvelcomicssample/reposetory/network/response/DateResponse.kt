package ru.taptm.marvelcomicssample.reposetory.network.response

import com.google.gson.annotations.SerializedName

data class DateResponse (
    @SerializedName("type") var type: String? = null,
    @SerializedName("date") var date: String? = null
)
