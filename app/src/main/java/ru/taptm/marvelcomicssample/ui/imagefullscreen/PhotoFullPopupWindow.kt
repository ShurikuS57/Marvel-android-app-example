package ru.taptm.marvelcomicssample.ui.imagefullscreen

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.ProgressBar
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.chrisbanes.photoview.PhotoView
import ru.taptm.marvelcomicssample.GlideApp
import ru.taptm.marvelcomicssample.R

class PhotoFullPopupWindow(private var context: Context, layout: Int, v: View?, imageUrl: String, bitmap: Bitmap?) : PopupWindow((context?.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.popup_photo_full, null), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
    internal var view: View
    internal var photoView: PhotoView
    internal var loading: ProgressBar
    internal var parent: ViewGroup

    init {
        elevation = 5.0f
        this.view = contentView
        val closeButton = this.view.findViewById<View>(R.id.ib_close) as ImageView
        isOutsideTouchable = true

        isFocusable = true
        closeButton.setOnClickListener {
            dismiss()
        }
        photoView = view.findViewById<View>(R.id.image) as PhotoView
        loading = view.findViewById<View>(R.id.loading) as ProgressBar
        photoView.maximumScale = 6f
        parent = photoView.parent as ViewGroup
        if (bitmap != null) {
            loading.visibility = View.GONE
            parent.background = BitmapDrawable(context?.resources, ImageFullPopupHelper.fastblur(Bitmap.createScaledBitmap(bitmap, 50, 50, true)))// ));
            photoView.setImageBitmap(bitmap)
        } else {
            loading.isIndeterminate = true
            loading.visibility = View.VISIBLE
            GlideApp.with(context).asBitmap()
                    .load(imageUrl)
                    .error(R.drawable.ic_picture_place_holder)
                    .listener(object : RequestListener<Bitmap> {
                        override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Bitmap>, isFirstResource: Boolean): Boolean {
                            loading.isIndeterminate = false
                            loading.setBackgroundColor(Color.LTGRAY)
                            return false
                        }

                        override fun onResourceReady(resource: Bitmap, model: Any, target: Target<Bitmap>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                            parent.background = BitmapDrawable(context?.resources, ImageFullPopupHelper.fastblur(Bitmap.createScaledBitmap(resource, 50, 50, true)))// ));
                            photoView.setImageBitmap(resource)
                            loading.visibility = View.GONE
                            return false
                        }
                    })

                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(photoView)
            showAtLocation(v, Gravity.CENTER, 0, 0)
        }
    }
}
