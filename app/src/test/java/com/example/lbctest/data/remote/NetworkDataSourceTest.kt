package com.example.lbctest.data.remote

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.lbctest.CoroutineTestRule
import com.example.lbctest.core.Resource
import com.example.lbctest.data.remote.dto.SongDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import java.io.IOException
import kotlin.Exception

class NetworkDataSourceTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var sut: NetworkDataSource

    private lateinit var webService: WebService

    private val dispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(dispatcher)

    @Before
    fun setUp() {
        webService = Mockito.mock(WebService::class.java)
        sut = NetworkDataSource(webService)
    }

    @ExperimentalCoroutinesApi
    @Test
    @Suppress("IllegalIdentifier")
    fun `when getSongs() should return success `() = runBlockingTest {
        Mockito.`when`(webService.getSongs()).thenReturn(listOf(SongDto(0, 0, "", "", "")))
        lateinit var result : Resource<List<SongDto>>
        testScope.launch {
            sut.getSongs().collect {
              result = it
            }
        }

        assert(result is Resource.Success )

    }

    @ExperimentalCoroutinesApi
    @Test
    @Suppress("IllegalIdentifier")
    fun `when getSongs() should return failure `() = runBlockingTest {
        given(webService.getSongs()).willAnswer {
            throw IOException("Ooops")
        }
        lateinit var result : Resource<List<SongDto>>
        testScope.launch {
            sut.getSongs().collect {
                result = it
            }
        }

        assert(result is Resource.Failure )

    }

}
