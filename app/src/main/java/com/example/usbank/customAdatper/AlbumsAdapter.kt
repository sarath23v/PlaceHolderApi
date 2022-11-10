package com.example.usbank.customAdatper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.usbank.ItemClickListener


import com.example.usbank.R
import com.example.usbank.databinding.RowListItemBinding


import com.example.usbank.model.AlbumsModel

import java.util.ArrayList

class AlbumsAdapter(val onItemClick: ItemClickListener) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    private var mAlbumsModel: ArrayList<AlbumsModel>? = null


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AlbumsViewHolder {
        val mDeveloperListItemBinding = DataBindingUtil.inflate<RowListItemBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.row_list_item, viewGroup, false
        )

        return AlbumsViewHolder(mDeveloperListItemBinding)
    }

    override fun onBindViewHolder(mAlbumsViewHolder: AlbumsViewHolder, i: Int) {
        val currentAlbum = mAlbumsModel!![i]
        mAlbumsViewHolder.listItemBinding.albumsModel = currentAlbum
        mAlbumsViewHolder.itemView.setOnClickListener {
            onItemClick.onItemClick(i,currentAlbum.id)
        }



    }

    override fun getItemCount(): Int {
        return  if (mAlbumsModel != null) {
            mAlbumsModel!!.size
        } else {
            0
        }
    }

    fun setAlbumsList(mAlbumsModel: ArrayList<AlbumsModel>) {
        this.mAlbumsModel = mAlbumsModel
        notifyDataSetChanged()
    }


    inner class AlbumsViewHolder(var listItemBinding: RowListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root){
        /*init {
            itemView.setOnClickListener {
                onItemClick.onItemClick()
                mAlbumsModel?.get(adapterPosition)?.let { it1 -> onItemClick?.invoke(it1) }
            }
        }*/
        }
}
