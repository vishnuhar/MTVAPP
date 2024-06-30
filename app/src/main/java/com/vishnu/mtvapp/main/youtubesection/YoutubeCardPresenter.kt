package com.vishnu.mtvapp.main.youtubesection

import android.os.Build
import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.vishnu.mtvapp.R
import com.vishnu.mtvapp.main.youtubesection.model.YouTube

class YoutubeCardPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val context = parent.context
        val cardView = ImageCardView(context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setBackgroundColor(context.resources.getColor(androidx.leanback.R.color.lb_tv_white, null))
            }

        }
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val youTube = item as YouTube
        val cardView = viewHolder.view as ImageCardView
        cardView.titleText = youTube.title
        cardView.contentText = youTube.desc


        cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)
        Glide.with(cardView.context)
            .load(youTube.thumbnailUrl)
            .centerCrop()
            .error(R.drawable.movie)
            .into(cardView.mainImageView)

    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val cardView = viewHolder.view as ImageCardView
        cardView.mainImage = null
    }

    companion object {
        private const val CARD_WIDTH = 315
        private const val CARD_HEIGHT = 176
    }
}
