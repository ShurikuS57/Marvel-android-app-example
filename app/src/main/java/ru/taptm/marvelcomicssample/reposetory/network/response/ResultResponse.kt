package ru.taptm.marvelcomicssample.reposetory.network.response

import com.google.gson.annotations.SerializedName

data class ResultResponse (
        @SerializedName("id") var id: Int? = null,
        @SerializedName("digitalId") var digitalId: Int? = null,
        @SerializedName("title") var title: String? = null,
        @SerializedName("issueNumber") var issueNumber: Int? = null,
        @SerializedName("variantDescription") var variantDescription: String? = null,
        @SerializedName("description") var description: String? = null,
        @SerializedName("modified") var modified: String? = null,
        @SerializedName("isbn") var isbn: String? = null,
        @SerializedName("upc") var upc: String? = null,
        @SerializedName("diamondCode") var diamondCode: String? = null,
        @SerializedName("ean") var ean: String? = null,
        @SerializedName("issn") var issn: String? = null,
        @SerializedName("format") var format: String? = null,
        @SerializedName("pageCount") var pageCount: Int? = null,
        @SerializedName("textObjects") var textObjects: List<Any>? = null,
        @SerializedName("resourceURI") var resourceURI: String? = null,
        @SerializedName("urls") var urls: List<UrlResponse>? = null,
        @SerializedName("series") var series: SeriesResponse? = null,
        @SerializedName("variants") var variants: List<Any>? = null,
        @SerializedName("collections") var collections: List<Any>? = null,
        @SerializedName("collectedIssues") var collectedIssues: List<Any>? = null,
        @SerializedName("dates") var dates: List<DateResponse>? = null,
        @SerializedName("prices") var prices: List<PriceResponse>? = null,
        @SerializedName("thumbnail") var thumbnail: ThumbnailResponse? = null,
        @SerializedName("images") var images: List<ImageResponse>? = null,
        @SerializedName("creators") var creators: CreatorsResponse? = null,
        @SerializedName("characters") var characters: CharactersResponse? = null,
        @SerializedName("stories") var stories: StoriesResponse? = null,
        @SerializedName("events") var events: EventsResponse? = null
)
