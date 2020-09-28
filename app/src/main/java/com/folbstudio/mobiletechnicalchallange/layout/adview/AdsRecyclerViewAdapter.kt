package com.folbstudio.mobiletechnicalchallange.layout.adview

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.folbstudio.mobiletechnicalchallange.R
import com.folbstudio.mobiletechnicalchallange.layout.adview.AdsRecyclerViewAdapter.AdViewBinder
import com.folbstudio.mobiletechnicalchallange.models.Ad
import com.folbstudio.mobiletechnicalchallange.utils.inflate
import kotlinx.android.synthetic.main.ad_item.view.*

class AdsRecyclerViewAdapter : RecyclerView.Adapter<AdViewBinder>() {

    private var ads: List<Ad> = listOf()

    class AdViewBinder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.image
        var title: TextView = itemView.description
        var price: TextView = itemView.price
        var location: TextView = itemView.location
        var liked: ImageView = itemView.favButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewBinder {
        val inflatedView = parent.inflate(R.layout.ad_item)
        return AdViewBinder(inflatedView)
    }

    override fun onBindViewHolder(holder: AdViewBinder, position: Int) {
        with(ads[position]) {
            holder.image.setImageBitmap(imageBitmap)
            holder.title.text = description
            holder.price.text = getPrice()
            holder.location.text = location
            holder.liked.setImageDrawable(getIcon())

            holder.liked.setOnClickListener {
                toggleFavorite()
                notifyDataSetChanged()
            }
        }
    }

    fun updateAds(newAdList: List<Ad>) {
        ads = newAdList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return ads.size
    }
}