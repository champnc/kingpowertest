@file:OptIn(ExperimentalMaterial3Api::class)

package com.champnc.kingpowertest.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.champnc.kingpowertest.R
import com.champnc.kingpowertest.domain.model.Album

@Composable
fun CardItem(album: Album, onClicked: (String) -> Unit) {
    val contentDescription = stringResource(id = R.string.card_item_content_description)

    Card(modifier = Modifier
        .wrapContentSize()
        .semantics {
            this.contentDescription = contentDescription
        }, onClick = { onClicked.invoke(album.url) }) {
        Column() {
            AsyncImage(
                model = album.thumbnailUrl,
                contentDescription = stringResource(R.string.thumbnail_content_description),
                Modifier.size(150.dp)
            )
            Text(
                text = album.title,
                modifier = Modifier.width(150.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}