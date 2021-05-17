package com.example.lbctest.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.lbctest.CoroutineTestRule
import com.example.lbctest.domain.repo.AlbumsRepositoryImpl
import com.example.lbctest.domain.usecases.GetAlbumsUseCase
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class GetAlbumsUseCaseTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var sut: GetAlbumsUseCase

    private lateinit var albumsRepository: AlbumsRepositoryImpl

    @Before
    fun setUp() {
        albumsRepository = mock(AlbumsRepositoryImpl::class.java)
        sut = GetAlbumsUseCase(albumsRepository)
    }

    @Test
    @Suppress("IllegalIdentifier")
    fun `when invoke albumsRepository getAll should be called`() = runBlockingTest {

        sut.invoke()
        verify(albumsRepository).getAll()

    }
}