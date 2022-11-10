package com.example.usbank.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.usbank.model.AlbumsDetailsModel
import com.example.usbank.repository.AlbumsRepository

class PhotoViewModel(application: Application) : AndroidViewModel(application) {
    private val mAlbumsRepository: AlbumsRepository = AlbumsRepository()

    fun getAlbumPhotos(id:String):MutableLiveData<List<AlbumsDetailsModel>> = mAlbumsRepository.getAlbumsDetails(id)

}