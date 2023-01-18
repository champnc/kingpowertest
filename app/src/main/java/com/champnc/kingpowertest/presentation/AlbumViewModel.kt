package com.champnc.kingpowertest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.champnc.kingpowertest.domain.usecase.GetAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val getAlbumUseCase: GetAlbumUseCase) : ViewModel() {

    private val _mainScreenState: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState.Loading)
    val mainScreenState: StateFlow<MainScreenState> = _mainScreenState

    init {
        getAlbumList()
    }

    private fun getAlbumList() = viewModelScope.launch {
        getAlbumUseCase.execute().onStart {
            _mainScreenState.value = MainScreenState.Loading
        }.catch {
            _mainScreenState.value = MainScreenState.Error
        }.collect{
            _mainScreenState.value = MainScreenState.Success(it)
        }
    }
}