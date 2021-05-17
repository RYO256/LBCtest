package com.example.lbctest.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.lbctest.CoroutineTestRule
import com.example.lbctest.data.local.LocalDataSource
import com.example.lbctest.data.local.SongsDao_Impl
import com.example.lbctest.data.local.entities.SongEntity
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito.*

class LocalDataSourceTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var sut: LocalDataSource

    private lateinit var songsDao: SongsDao_Impl

    @Before
    fun setUp() {
        songsDao = mock(SongsDao_Impl::class.java)
        sut = LocalDataSource(songsDao )
    }

    @Test
    @Suppress("IllegalIdentifier")
    fun `when saveSong songsDao save should be called`() = runBlockingTest {
        val songEntity = SongEntity()
        sut.saveSong(songEntity)
        verify(songsDao).save(songEntity)

    }

    @Test
    @Suppress("IllegalIdentifier")
    fun `when getCachedAlbums songsDao getAll should be called`() = runBlockingTest {
        `when`(songsDao.getAll()).thenReturn(listOf())
        sut.getCachedAlbums()
        verify(songsDao).getAll()
    }

    @Test
    @Suppress("IllegalIdentifier")
    fun `when removeCachedAlbums songsDao deleteAll should be called`() = runBlockingTest {
        sut.removeCachedAlbums()
        verify(songsDao).deleteAll()
    }

}