package com.champnc.kingpowertest.data.service

import com.champnc.kingpowertest.data.model.AlbumResponse
import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {

    @GET("/albums/1/photos")
    suspend fun getAlbumList(): Response<List<AlbumResponse>>
}