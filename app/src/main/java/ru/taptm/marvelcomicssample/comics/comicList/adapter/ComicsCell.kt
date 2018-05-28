package ru.taptm.marvelcomicssample.comics.comicList.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.jaychang.srv.kae.SimpleCell
import com.jaychang.srv.kae.SimpleViewHolder
import ru.taptm.marvelcomicssample.R
import ru.taptm.marvelcomicssample.reposetory.network.response.ResultResponse
import ru.taptm.marvelcomicssample.utils.ImageHelper
import ru.taptm.marvelcomicssample.utils.ImageUrlHelper

class ComicsCell(item: ResultResponse) : SimpleCell<ResultResponse>(item) {

    override fun getLayoutRes(): Int {
        return R.layout.item_comics_book
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int, context: Context, payload: Any?) {
        val coverImage = holder.itemView.findViewById<ImageView>(R.id.image_cover_comics)
        val imageUrl = ImageUrlHelper.prepareDefaultImageUrl(item.images)

        if (imageUrl.isNotEmpty()) {
            ImageHelper.setImageWithPlaceHolder(imageUrl, coverImage)
        } else {
            coverImage.setImageResource(R.drawable.ic_picture_place_holder)
        }

        val titleComics = holder.itemView.findViewById<TextView>(R.id.text_name_comics)
        titleComics.text = item.title
    }
}