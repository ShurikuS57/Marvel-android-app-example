package ru.taptm.marvelcomicssample.comics.comicsdetail.model

import ru.taptm.marvelcomicssample.reposetory.network.response.ResultResponse
import ru.taptm.marvelcomicssample.utils.ImageUrlHelper

class ComicsDetailsModel(resultData: List<ResultResponse>?) {
    private val resultData = resultData?.get(0)

    fun getComicsId(): Long {
        return resultData?.id?.toLong()?:0L
    }

    fun getImageUrl(): String {
        return ImageUrlHelper.prepareDefaultImageUrl(resultData?.images)
    }

    fun  getFullImageUrl(): String {
        return ImageUrlHelper.prepareFullImageUrl(resultData?.images)
    }

    fun getTitle(): String {
        return resultData?.title?: ""
    }

    fun getDescription(): String {
        return resultData?.description?: ""
    }

    fun getCreators(): String {
        var resultStr = String()
        resultData?.creators?.items?.forEach {
            resultStr = resultStr.plus("${it.name?:""} (${it.role?:""}); ")
        }
        if (resultStr.length > 2) {
            resultStr.removeRange(resultStr.length - 2, resultStr.length)
        }
        return resultStr
    }
}

