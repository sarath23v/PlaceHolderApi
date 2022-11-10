package com.example.usbank

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.usbank.customAdatper.AlbumsAdapter
import com.example.usbank.databinding.ActivityMainBinding
import com.example.usbank.model.AlbumsModel
import com.example.usbank.viewmodel.AlbumsViewModel
import java.util.*

class MainActivity : AppCompatActivity(), ItemClickListener{

    internal var activityMainBinding: ActivityMainBinding?= null
    internal var loadBar : ProgressBar? = null
    var viewModel: AlbumsViewModel? = null
    private var m_AlbumsAdapter: AlbumsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main);

        // bind RecyclerView
        setUpAdapter()

        getAlbums()
    }

    private fun setUpAdapter() {
        val recyclerView = activityMainBinding?.viewdeveloper
        loadBar = activityMainBinding!!.loadBar
        recyclerView!!.layoutManager = GridLayoutManager(this, 2)
        recyclerView.setHasFixedSize(true)

        viewModel = ViewModelProviders.of(this).get(AlbumsViewModel::class.java)

        m_AlbumsAdapter = AlbumsAdapter(this)
        recyclerView.adapter = m_AlbumsAdapter

    }

    private fun getAlbums() {
        ///get the list of dev from api response
        viewModel!!.albums.observe(this,
            Observer<List<Any>> { mDeveloperModel ->
                ///if any thing chnage the update the UI
                m_AlbumsAdapter?.setAlbumsList(mDeveloperModel as ArrayList<AlbumsModel>)
                loadBar?.visibility = View.GONE
            })
    }

    override fun onItemClick(position: Int, id: String?) {
        val intent = Intent(this, GalleryActivity::class.java)
        intent.putExtra(GalleryActivity.GALLERY_ACTIVITY_BUNDLE_DATA_KEY, id)
        this.startActivity(intent)
        Toast.makeText(this, "$id", Toast.LENGTH_SHORT).show()
    }
}
