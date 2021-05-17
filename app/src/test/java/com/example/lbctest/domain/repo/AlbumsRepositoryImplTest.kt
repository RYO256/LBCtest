package com.example.lbctest.domain.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.lbctest.CoroutineTestRule
import com.example.lbctest.core.Resource
import com.example.lbctest.data.AlbumsRepositoryImpl
import com.example.lbctest.data.remote.NetworkDataSource
import com.example.lbctest.data.remote.dto.SongDto
import com.example.lbctest.data.local.LocalDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.lang.Exception

@ExperimentalCoroutinesApi
class AlbumsRepositoryImplTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var sut: AlbumsRepositoryImpl

    private lateinit var networkDataSource: NetworkDataSource
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        networkDataSource = mock(NetworkDataSource::class.java)
        localDataSource = mock(LocalDataSource::class.java)
        sut = AlbumsRepositoryImpl(networkDataSource, localDataSource)
    }


    @Test
    @Suppress("IllegalIdentifier")
    fun `when getAll() should return cache, clear, success `() = runBlockingTest {
        val responses = mutableListOf<String>()

        `when`(networkDataSource.getSongs()).thenReturn(flow {
            emit(Resource.Success(listOf<SongDto>()))
            responses.add("success")
        })

        `when`(localDataSource.getCachedAlbums()).then {
            responses.add("cache")
        }.thenReturn(Resource.Success(listOf()))

        `when`(localDataSource.removeCachedAlbums()).then {
            responses.add("clear")
        }

        sut.getAll().collect()

        print(responses)
        assert(responses.containsAll(listOf("cache", "clear", "success")))

    }


    @Test
    @Suppress("IllegalIdentifier")
    fun `when getAll() should return cache, error `() = runBlockingTest {
        val responses = mutableListOf<String>()

        `when`(networkDataSource.getSongs()).thenReturn(flow {
            emit(Resource.Failure(Exception("exception")))
            responses.add("failure")
        })

        `when`(localDataSource.getCachedAlbums()).then {
            responses.add("cache")
        }.thenReturn(Resource.Success(listOf()))

        sut.getAll().collect()

        print(responses)
        assert(responses.containsAll(listOf("cache", "failure")))

    }

}

