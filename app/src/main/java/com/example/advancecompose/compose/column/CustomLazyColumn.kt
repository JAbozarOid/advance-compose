package com.example.advancecompose.compose.column

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.advancecompose.R
import com.example.advancecompose.data.local.TvShowList
import com.example.advancecompose.model.TvShow
import com.example.advancecompose.ui.theme.AdvanceComposeTheme


/**
 * display custom lazy column with custom items
 */
@Composable
internal fun imageItem(model: TvShow) {
    Image(
        painter = painterResource(id = model.imageId),
        contentDescription = "image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(4.dp)
            .height(140.dp)
            .width(100.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
    )
}

@Preview(showBackground = false)
@Composable
private fun imageItemPreview() {
    AdvanceComposeTheme {
        imageItem(model = TvShow(imageId = R.drawable.ar))
    }
}


@Composable
internal fun cardItem(model: TvShow, selectedIem: (TvShow) -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clickable { selectedIem(model) }, verticalAlignment = Alignment.CenterVertically
        ) {
            imageItem(model = model)
            Column {
                Text(text = model.name, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = model.overview,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = model.year.toString(),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = model.rate.toString(),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }

    }
}

@Preview(showBackground = false)
@Composable
private fun cardItemPreview() {
    AdvanceComposeTheme {
        cardItem(
            model = TvShow(
                name = "Test",
                imageId = R.drawable.ar,
                overview = "When evil comes to earth",
                year = 2016,
                rate = 8.6
            ),
            selectedIem = {

            })
    }
}

@Composable
internal fun displayListData(selectedIem: (TvShow) -> Unit) {

    val items = remember { TvShowList.tvShows }

    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(items = items, itemContent = {
            cardItem(model = it, selectedIem = selectedIem)
        })
    }

}

@Preview(showBackground = false)
@Composable
private fun listDataPreview() {
    AdvanceComposeTheme {
        displayListData(selectedIem = {})
    }
}