package com.champnc.kingpowertest.data.repository

import com.champnc.kingpowertest.data.model.AlbumResponse
import com.champnc.kingpowertest.data.service.AlbumService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface AlbumRepository {
    fun getAlbumList(): Flow<List<AlbumResponse>?>
}

class AlbumRepositoryImpl @Inject constructor(private val api: AlbumService) : AlbumRepository {
    override fun getAlbumList(): Flow<List<AlbumResponse>?> = flow {
        val response = api.getAlbumList()
        if (response.isSuccessful) {
            emit(response.body())
        } else {
            error("Cannot get album list")
        }
    }
}
