@file:OptIn(ExperimentalCoroutinesApi::class)

package com.champnc.kingpowertest.domain.usecase

import com.champnc.kingpowertest.CoroutinesTestExtension
import com.champnc.kingpowertest.InstantExecutorExtension
import com.champnc.kingpowertest.data.model.AlbumResponse
import com.champnc.kingpowertest.data.repository.AlbumRepository
import com.champnc.kingpowertest.domain.model.Album
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class, CoroutinesTestExtension::class)
internal class GetAlbumUseCaseTest {

    lateinit var getAlbumUseCase: GetAlbumUseCase
    private val albumRepository = mockk<AlbumRepository>()

    @BeforeEach
    fun setUp() {
        getAlbumUseCase = GetAlbumUseCaseImpl(albumRepository = albumRepository)
    }

    @Test
    fun `test getAlbumUseCase and mapping success`() = runTest {
        val expected = listOf(
            Album(
                albumId = 0,
                id = 0,
                thumbnailUrl = "",
                title = "",
                url = ""
            )
        )

        every { albumRepository.getAlbumList() } returns flow {
            listOf(
                AlbumResponse(
                    albumId = 0,
                    id = 0,
                    thumbnailUrl = "",
                    title = "",
                    url = ""
                )
            )
        }

        getAlbumUseCase.execute().collect {
            assertEquals(expected, it)
        }
    }

    @Test
    fun `test getAlbumUseCase and mapping error`() = runTest {
        every { albumRepository.getAlbumList() } throws IllegalStateException()

        assertThrows<IllegalStateException> {
            albumRepository.getAlbumList().collect()
        }
    }
}