package com.champnc.kingpowertest.domain.usecase

import com.champnc.kingpowertest.data.repository.AlbumRepository
import com.champnc.kingpowertest.data.model.AlbumResponse
import com.champnc.kingpowertest.domain.model.Album
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface GetAlbumUseCase {
    fun execute(): Flow<List<Album>>
}

class GetAlbumUseCaseImpl @Inject constructor(private val albumRepository: AlbumRepository) :
    GetAlbumUseCase {
    override fun execute(): Flow<List<Album>> = flow {
        albumRepository.getAlbumList().catch {
            error("Cannot get album list")
        }.map {
            it?.map { album ->
                mapperToModel(album)
            } ?: listOf()
        }.collect{
            emit(it)
        }
    }

    private fun mapperToModel(albumResponse: AlbumResponse) = Album(
        albumResponse.albumId,
        albumResponse.id,
        albumResponse.url,
        albumResponse.title,
        albumResponse.thumbnailUrl
    )
}