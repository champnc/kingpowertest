package com.champnc.kingpowertest.presentation

import com.champnc.kingpowertest.domain.model.Album

sealed interface MainScreenState {
    object Loading : MainScreenState
    object Error : MainScreenState
    data class Success(val data: List<Album>): MainScreenState
}
