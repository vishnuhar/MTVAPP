package com.vishnu.mtvapp.main.imagesection.imagecardpresenter

import android.os.Build
import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.vishnu.mtvapp.R
import com.vishnu.mtvapp.main.imagesection.model.Image

class ImagePresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val context = parent.context
        val cardView = ImageCardView(context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setBackgroundColor(context.resources.getColor(R.color.default_background, null))
            }
        }
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val image = item as Image
        val cardView = viewHolder.view as ImageCardView

        cardView.titleText = image.title
        cardView.contentText = image.description

        val imageUrl = image.imageUrl
        cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)
        if (imageUrl != null) {
            Glide.with(cardView.context)
                .load(imageUrl)
                .centerCrop()
                .error(R.drawable.movie)
                .into(cardView.mainImageView)
        } else {
            cardView.mainImage = cardView.resources.getDrawable(R.drawable.movie, null)
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val cardView = viewHolder.view as ImageCardView
        cardView.mainImage = null
    }

    companion object {

        private val CARD_WIDTH = 313
        private val CARD_HEIGHT = 176
    }
}