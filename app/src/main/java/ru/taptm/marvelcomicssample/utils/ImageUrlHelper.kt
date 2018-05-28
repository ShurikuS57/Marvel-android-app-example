package ru.taptm.marvelcomicssample.utils

import ru.taptm.marvelcomicssample.reposetory.network.response.ImageResponse

class ImageUrlHelper {
    enum class SizeImage(val type: String) {
        PORTRAIT_XLARGE("portrait_xlarge"),
        PORTRAIT_INCREDIBLE("portrait_incredible")
    }
    
    companion object {

        fun prepareDefaultImageUrl(images: List<ImageResponse>?): String {
            return prepareUrl(images, SizeImage.PORTRAIT_XLARGE)
        }

        fun prepareFullImageUrl(images: List<ImageResponse>?): String {
            return prepareUrl(images, SizeImage.PORTRAIT_INCREDIBLE)
        }

        private fun prepareUrl(images: List<ImageResponse>?, type: SizeImage): String {
            if (images != null && images.isNotEmpty()) {
                val image = images[0]
                val path = image.path
                val endFileName = image.extension
                return String.format("%s/%s.%s", path, type.type, endFileName)
            }
            return ""
        }
    }
}
