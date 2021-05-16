package com.example.lbctest.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.lbctest.core.Resource
import com.example.lbctest.domain.models.Album
import com.example.lbctest.domain.repo.AlbumsRepository
import com.example.lbctest.domain.usecases.GetAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val getAlbumsUseCase: GetAlbumsUseCase,
) : ViewModel() {


    fun getAlbums() =
            liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
                emit(Resource.Loading)
                try {
                    getAlbumsUseCase.invoke().collect {
                        emit(it)
                    }
                } catch (e: Exception) {
                    emit(Resource.Failure(e))
                }
            }

}