package com.example.advancecompose.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.advancecompose.R
import com.example.advancecompose.model.TvShow
import com.example.advancecompose.ui.theme.AdvanceComposeTheme

class DetailActivity : ComponentActivity() {

    companion object {
        private const val TV_SHOW_ID = "tvshowid"
        fun intent(context: Context, tvShow: TvShow) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(TV_SHOW_ID, tvShow)
            }
    }

    private val tvShow: TvShow by lazy {
        intent?.getSerializableExtra(TV_SHOW_ID) as TvShow
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            detailView(model = tvShow)
        }
    }
}

@Composable
fun detailView(model: TvShow) {
    val scrollState = rememberScrollState()
    Card(
        modifier = Modifier.padding(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = model.imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = model.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(text = model.overview, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Original Release : ${model.year}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "The total rate is : ${model.rate}",
                style = MaterialTheme.typography.headlineSmall
            )

        }
    }
}

@Preview(showBackground = false)
@Composable
fun detailViewPreview() {
    AdvanceComposeTheme {
        detailView(
            model = TvShow(
                imageId = R.drawable.ar,
                name = "Abozar",
                overview = "Devil comes to earth",
                year = 2016,
                rate = 8.6
            )
        )
    }
}