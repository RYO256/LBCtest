package com.example.lbctest.ui.albumdetail

import androidx.lifecycle.*
import com.example.lbctest.core.Resource
import com.example.lbctest.domain.usecases.GetAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
        private val getAlbumsUseCase: GetAlbumsUseCase,
) : ViewModel() {


    fun getSongs(albumId : Int) =
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