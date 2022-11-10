package com.example.usbank.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.usbank.model.AlbumsDetailsModel

import com.example.usbank.model.AlbumsModel
import com.example.usbank.network.RetrofitClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumsRepository {


    fun getAlbums(): MutableLiveData<List<AlbumsModel>> {
        val albumsLiveData = MutableLiveData<List<AlbumsModel>>()
        val dataService = RetrofitClient.service
        val call = dataService.apiRequestAlbums()

        call.enqueue(object : Callback<List<AlbumsModel>> {

            override fun onResponse(call: Call<List<AlbumsModel>>, resp: Response<List<AlbumsModel>>) {
                Log.d(TAG, "Total Albums: " + resp.body()!!.size)
                if (resp?.body() != null) {
                    albumsLiveData.value = resp.body()
                }
            }

            override fun onFailure(call: Call<List<AlbumsModel>>, t: Throwable) {
                t.printStackTrace()

            }
        })
        return albumsLiveData
    }

    fun getAlbumsDetails(id:String): MutableLiveData<List<AlbumsDetailsModel>> {
        val albumDetailsLiveData = MutableLiveData<List<AlbumsDetailsModel>>()
        val dataService = RetrofitClient.service
        val call = dataService.apiRequestAlbumsPhotos(id)

        call.enqueue(object : Callback<List<AlbumsDetailsModel>> {

             override fun onResponse(call: Call<List<AlbumsDetailsModel>>, resp: Response<List<AlbumsDetailsModel>>) {
                Log.d(TAG, "Total Pics in Album " +id+" :"+ resp.body()!!.size)
                if (resp?.body() != null) {
                   albumDetailsLiveData.value = resp.body()
                    Log.d(TAG, "VALUE " +id+" :"+ albumDetailsLiveData.value?.get(0)?.thumbnailUrl)
                }
            }

            override fun onFailure(call: Call<List<AlbumsDetailsModel>>, t: Throwable) {
                t.printStackTrace()

            }
        })
        return albumDetailsLiveData
    }



    companion object {

        private val TAG = "AlbumsRepository"
    }
}
