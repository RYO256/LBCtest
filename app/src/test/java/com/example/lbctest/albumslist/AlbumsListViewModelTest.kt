package com.example.lbctest.albumslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.lbctest.CoroutineTestRule
import com.example.lbctest.core.Resource
import com.example.lbctest.domain.usecases.GetAlbumsUseCase
import com.example.lbctest.ui.albumslist.AlbumsListViewModel
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test

import org.junit.Rule

import kotlinx.coroutines.test.runBlockingTest
import org.mockito.Mockito.*

class AlbumsListViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var sut: AlbumsListViewModel

    private lateinit var getAlbumsUseCase: GetAlbumsUseCase

    @Before
    fun setUp() {
        getAlbumsUseCase = mock(GetAlbumsUseCase::class.java)
        `when`(getAlbumsUseCase.invoke()).thenReturn(flowOf(Resource.Success(listOf())))
        sut = AlbumsListViewModel(getAlbumsUseCase)
    }

    @Test
    @Suppress("IllegalIdentifier")
    fun `when getAlbums getAlbumsUseCase should be called`() = runBlockingTest {
        sut.getAlbums().observeForever(Observer {
            verify(getAlbumsUseCase).invoke()
        })

    }
}
