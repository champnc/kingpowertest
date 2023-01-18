@file:OptIn(ExperimentalCoroutinesApi::class)

package com.champnc.kingpowertest.data.repository

import com.champnc.kingpowertest.CoroutinesTestExtension
import com.champnc.kingpowertest.InstantExecutorExtension
import com.champnc.kingpowertest.data.model.AlbumResponse
import com.champnc.kingpowertest.data.service.AlbumService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Response

@ExtendWith(InstantExecutorExtension::class, CoroutinesTestExtension::class)
internal class AlbumRepositoryTest {

    lateinit var albumRepository: AlbumRepository
    private val api = mockk<AlbumService>()

    @BeforeEach
    fun setUp() {
        albumRepository = AlbumRepositoryImpl(api = api)
    }

    @Test
    fun `test getAlbumList success`() = runTest {
        val expected = listOf(
            AlbumResponse(
                albumId = 0,
                id = 0,
                thumbnailUrl = "",
                title = "",
                url = ""
            )
        )

        coEvery { api.getAlbumList() } returns Response.success(
            listOf(
                AlbumResponse(
                    albumId = 0,
                    id = 0,
                    thumbnailUrl = "",
                    title = "",
                    url = ""
                )
            )
        )

        albumRepository.getAlbumList().collect {
            assertEquals(expected, it)
        }
    }

    @Test
    fun `test getAlbumList error`() = runTest {
        coEvery { api.getAlbumList() } throws IllegalStateException()

        assertThrows<IllegalStateException> {
            albumRepository.getAlbumList().collect()
        }
    }
}