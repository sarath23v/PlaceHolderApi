package com.example.usbank.customAdatper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.usbank.R
import com.example.usbank.databinding.PhotoItemBinding
import com.example.usbank.model.AlbumsDetailsModel
import com.example.usbank.model.AlbumsModel


class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotosViewHolder>() {


    private var mAlbumsDetailsModel: List<AlbumsDetailsModel>? = null
    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PhotosViewHolder {
        val mListItemBinding = DataBindingUtil.inflate<PhotoItemBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.photo_item, viewGroup, false
        )

        return PhotosViewHolder(mListItemBinding)
    }

    override fun onBindViewHolder(mPhotosViewHolder: PhotosViewHolder, i: Int) {
        val currentAlbum = mAlbumsDetailsModel!![i]
        val url = GlideUrl(
            currentAlbum.thumbnailUrl, LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build()
        )
        Glide.with(mPhotosViewHolder.listItemBinding.root.context)
            .load(url)
            .into(mPhotosViewHolder.listItemBinding.image);
    }

    override fun getItemCount(): Int {
        return  if (mAlbumsDetailsModel != null) {
            mAlbumsDetailsModel!!.size
        } else {
            0
        }
    }

    fun setAlbumsPhotoList(mAlbumsDetailsModel: List<AlbumsDetailsModel>?) {
        this.mAlbumsDetailsModel = mAlbumsDetailsModel
        notifyDataSetChanged()
    }

    inner class PhotosViewHolder(var listItemBinding: PhotoItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root){

        init {
            itemView.setOnClickListener {
                mAlbumsDetailsModel?.get(adapterPosition)?.let { it1 -> it1.url?.let { it2 ->
                    onItemClick?.invoke(it2)
                } }
            }
        }
    }
}
