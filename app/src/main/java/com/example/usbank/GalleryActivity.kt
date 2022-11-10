package com.example.usbank

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.usbank.customAdatper.PhotoAdapter
import com.example.usbank.databinding.ActivityGalleryBinding
import com.example.usbank.viewmodel.PhotoViewModel

class GalleryActivity : AppCompatActivity() {

    internal var activityGalleryBinding: ActivityGalleryBinding?= null
    var viewModel: PhotoViewModel? = null
    private var m_AlbumsAdapter: PhotoAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityGalleryBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_gallery)
        viewModel = ViewModelProviders.of(this).get(PhotoViewModel::class.java)

        setUpAdapter()
        var id = intent.getStringExtra(GALLERY_ACTIVITY_BUNDLE_DATA_KEY)

        viewModel?.getAlbumPhotos(id)?.observe(this, androidx.lifecycle.Observer{ photos ->
            m_AlbumsAdapter?.setAlbumsPhotoList(photos)
        })



    }

    private fun setUpAdapter() {
        val recyclerView = activityGalleryBinding?.viewdeveloper
        recyclerView!!.layoutManager = GridLayoutManager(this, 4)
        recyclerView.setHasFixedSize(true)

        m_AlbumsAdapter = PhotoAdapter()
        recyclerView.adapter = m_AlbumsAdapter

        m_AlbumsAdapter!!.onItemClick = { album ->
            val url = GlideUrl(
                album, LazyHeaders.Builder()
                    .addHeader("User-Agent", "your-user-agent")
                    .build()
            )
            activityGalleryBinding?.imageDetails?.visibility = View.VISIBLE
            activityGalleryBinding?.let {
                Glide.with(this)
                    .load(url)
                    .into(it?.imageDetails)
            };

        }


    }

    companion object {
        const val GALLERY_ACTIVITY_BUNDLE_DATA_KEY = "photolistId"
    }

    override fun onBackPressed() {
        if(activityGalleryBinding?.imageDetails?.visibility == View.VISIBLE)
            activityGalleryBinding?.imageDetails?.visibility = View.GONE
        else
            super.onBackPressed()
    }
}