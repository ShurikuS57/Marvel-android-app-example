package ru.taptm.marvelcomicssample.reposetory.network.response

import com.google.gson.annotations.SerializedName

data class CharactersResponse (
        @SerializedName("available") var available: Int? = null,
        @SerializedName("collectionURI") var collectionURI: String? = null,
        @SerializedName("items") var items: List<ItemResponse>? = null,
        @SerializedName("returned") var returned: Int? = null
)

