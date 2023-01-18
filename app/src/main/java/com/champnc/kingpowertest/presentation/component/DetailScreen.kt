@file:OptIn(ExperimentalMaterial3Api::class)

package com.champnc.kingpowertest.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.champnc.kingpowertest.R

@Composable
fun DetailScreenView(
    navController: NavHostController,
    arg: String
) {
    val detailScreenContentDescription = stringResource(R.string.detail_screen_content_description)
    val imageContentDescription = stringResource(R.string.image_content_description)

    Scaffold(
        topBar = {
            DetailScreenTopAppBar(navController)
        }
    ) { paddingValue ->
        Column(modifier = Modifier
            .padding(paddingValue)
            .background(MaterialTheme.colorScheme.tertiary)
            .semantics {
                this.contentDescription = detailScreenContentDescription
            }) {
            AsyncImage(
                model = arg,
                contentDescription = imageContentDescription,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun DetailScreenTopAppBar(navController: NavHostController) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.detail_scene_title), color = Color.White) },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Filled.ArrowBack, null, tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}