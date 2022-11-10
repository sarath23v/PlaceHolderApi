package com.example.usbank.network


import com.example.usbank.model.AlbumsDetailsModel
import com.example.usbank.model.AlbumsModel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDataService {


    @GET("albums")
    fun apiRequestAlbums(): Call<List<AlbumsModel>>

    @GET("photos")
    fun apiRequestAlbumsPhotos(@Query("albumId") id: String): Call<List<AlbumsDetailsModel>>

}
