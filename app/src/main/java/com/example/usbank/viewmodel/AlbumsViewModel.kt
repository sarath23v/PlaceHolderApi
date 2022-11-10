package com.example.usbank.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


import com.example.usbank.model.AlbumsModel
import com.example.usbank.repository.AlbumsRepository


class AlbumsViewModel(application: Application) : AndroidViewModel(application) {
    private val mAlbumsRepository: AlbumsRepository = AlbumsRepository()

    val albums: LiveData<List<AlbumsModel>>
        get() = mAlbumsRepository.getAlbums()


}
