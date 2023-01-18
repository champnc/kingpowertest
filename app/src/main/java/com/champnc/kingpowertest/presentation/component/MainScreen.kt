@file:OptIn(ExperimentalMaterial3Api::class)

package com.champnc.kingpowertest.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.champnc.kingpowertest.R
import com.champnc.kingpowertest.presentation.AlbumViewModel
import com.champnc.kingpowertest.presentation.MainScreenState
import com.champnc.kingpowertest.presentation.Screen

@Composable
fun MainScreenView(
    navController: NavHostController, viewModel: AlbumViewModel = hiltViewModel()
) {
    val state = viewModel.mainScreenState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.main_screen_title), color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValue ->
        Column(
            Modifier
                .padding(paddingValue)
                .padding(8.dp)
        ) {
            state.value.let { mainState ->
                when (mainState) {
                    MainScreenState.Error -> {
                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = stringResource(R.string.error_something_went_wrong))
                        }
                    }
                    MainScreenState.Loading -> {
                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    is MainScreenState.Success -> {
                        LazyVerticalGrid(columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            content = {
                                items(mainState.data.size) { index ->
                                    CardItem(mainState.data[index]) {
                                        navController.navigate(Screen.DetailScreen.withArgument(it))
                                    }
                                }
                            })
                    }
                }
            }

        }
    }

}