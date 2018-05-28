package ru.taptm.marvelcomicssample.reposetory.network.response

import com.google.gson.annotations.SerializedName


data class ImageResponse(
    @SerializedName("path") var path: String? = null,
    @SerializedName("extension") var extension: String? = null)