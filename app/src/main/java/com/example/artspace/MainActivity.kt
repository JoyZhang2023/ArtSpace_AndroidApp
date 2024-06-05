package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }

}

@Composable
fun ArtSpaceScreen(modifier: Modifier=Modifier) {
    val firstArtWork = R.drawable.mona_lisa
    val secondArtWork = R.drawable.self_portrait
    val thirdArtWork = R.drawable.last_supper
    val fourthArtWork = R.drawable.vitruvian_man

    var title by remember { mutableStateOf(R.string.mona_lisa) }

    var year by remember { mutableStateOf(R.string.mona_lisa_year) }

    var currentArt by remember { mutableStateOf(firstArtWork) }

    var imageSource by remember { mutableStateOf(currentArt) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.size(25.dp))
        ArtworkDisplay(currentArtwork = currentArt)
        Spacer(modifier = modifier.size(16.dp))
        ArtworkTitle(title=title, year=year)
        Spacer(modifier = modifier.size(25.dp))
        Row (
            modifier=modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ){
            // Previous button
            Button(onClick = {
                when(currentArt) {
                    firstArtWork -> {
                        currentArt = fourthArtWork
                        title = R.string.vitruvian_man
                        year = R.string.vitruvian_man_year
                    }
                    secondArtWork -> {
                        currentArt = firstArtWork
                        title = R.string.mona_lisa
                        year = R.string.mona_lisa_year
                    }
                    thirdArtWork -> {
                        currentArt = secondArtWork
                        title = R.string.self_portrait
                        year = R.string.self_portrait_year
                    }
                    fourthArtWork -> {
                        currentArt = thirdArtWork
                        title = R.string.last_supper
                        year = R.string.last_supper_year
                    }
                }
            },
                colors = buttonColors(
                    containerColor = colorResource(id = R.color.blue_100)
                ),
                elevation = ButtonDefaults.buttonElevation (
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                )) {
                Text(
                    text = "Previous",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.gray_900)
                )
            }

            // Next button
            Button(onClick = {
                when(currentArt) {
                    thirdArtWork -> {
                        currentArt = fourthArtWork
                        title = R.string.vitruvian_man
                        year = R.string.vitruvian_man_year
                    }
                    fourthArtWork -> {
                        currentArt = firstArtWork
                        title = R.string.mona_lisa
                        year = R.string.mona_lisa_year
                    }
                    firstArtWork -> {
                        currentArt = secondArtWork
                        title = R.string.self_portrait
                        year = R.string.self_portrait_year
                    }
                    secondArtWork-> {
                        currentArt = thirdArtWork
                        title = R.string.last_supper
                        year = R.string.last_supper_year
                    }
                }
            },
                colors = buttonColors(
                    containerColor = colorResource(id = R.color.blue_100)
                ),
                elevation = ButtonDefaults.buttonElevation (
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                )) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.gray_900)
                )
            }
        }
    }

}

@Composable
fun ArtworkDisplay(
    modifier: Modifier = Modifier,
    @DrawableRes currentArtwork: Int
) {
    Image(
        painter = painterResource(currentArtwork),
        contentDescription = stringResource(id = R.string.mona_lisa),
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun ArtworkTitle(
    @StringRes title: Int,
    @StringRes year: Int
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Artwork title
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.blue_100),
            fontSize = 32.sp
        )

        // Artwork year
        Text(
            text = "— ${stringResource(id = year)} —",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.gray_300)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}