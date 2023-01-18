@file:OptIn(ExperimentalCoroutinesApi::class)

package com.champnc.kingpowertest.presentation

import com.champnc.kingpowertest.CoroutinesTestExtension
import com.champnc.kingpowertest.InstantExecutorExtension
import com.champnc.kingpowertest.domain.model.Album
import com.champnc.kingpowertest.domain.usecase.GetAlbumUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class, CoroutinesTestExtension::class)
internal class AlbumViewModelTest {

    private lateinit var albumViewModel: AlbumViewModel
    private val getAlbumUseCase = mockk<GetAlbumUseCase>()

    @Test
    fun `test getAlbumList success`() = runTest {


        every { getAlbumUseCase.execute() } returns flow {
            listOf(
                Album(
                    albumId = 0,
                    id = 0,
                    url = "",
                    title = "",
                    thumbnailUrl = ""
                )
            )
        }

        albumViewModel = AlbumViewModel(getAlbumUseCase = getAlbumUseCase)

        assertEquals(MainScreenState.Loading, albumViewModel.mainScreenState.value)
    }
}