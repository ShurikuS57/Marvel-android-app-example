package ru.taptm.marvelcomicssample.reposetory.network.response

import com.google.gson.annotations.SerializedName

data class StoriesResponse (
        @SerializedName("available") var available: Int? = null,
        @SerializedName("collectionURI") var collectionURI: String? = null,
        @SerializedName("items")var items: List<Item_Response>? = null,
        @SerializedName("returned") var returned: Int? = null
)
