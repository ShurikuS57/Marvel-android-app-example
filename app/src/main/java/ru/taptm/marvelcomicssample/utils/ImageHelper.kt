package ru.taptm.marvelcomicssample.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.taptm.marvelcomicssample.R

class ImageHelper {
    companion object {
        fun setImageWithPlaceHolder(url: String, view: ImageView) {
            Glide.with(view.context)
                    .load(url)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.ic_picture_place_holder)
                            .error(R.drawable.ic_picture_place_holder)
                    )
                    .into(view)
        }
    }
}
