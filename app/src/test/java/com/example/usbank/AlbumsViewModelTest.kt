package com.example.usbank

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.usbank.model.AlbumsModel
import com.example.usbank.repository.AlbumsRepository
import com.example.usbank.viewmodel.AlbumsViewModel
import io.mockk.MockKAnnotations.init
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class AlbumsViewModelTest  {

    @MockK
    private lateinit var application: Application
    lateinit var viewModel: AlbumsViewModel
    @MockK
    lateinit var repository: AlbumsRepository
    val data = MutableLiveData<List<AlbumsModel>>(listOf(AlbumsModel("1","1","title")))


    @Before
    fun setUp() {
        init(this,relaxUnitFun = true)
        viewModel = AlbumsViewModel(application)
        every { repository.getAlbums() } returns data

    }

    @Test
    fun testGetAlbumPhotos() {

        Assert.assertEquals(viewModel.albums.value,data)
    }
}