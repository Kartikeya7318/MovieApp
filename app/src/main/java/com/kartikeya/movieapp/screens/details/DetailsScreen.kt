package com.kartikeya.movieapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.kartikeya.movieapp.model.Movie
import com.kartikeya.movieapp.model.getMovies
import com.kartikeya.movieapp.widgets.MovieRow


@Composable
fun DetailsScreen(
    navController: NavController,
    movieId: String?
) {
    val newMovieList = getMovies().filter { movie ->
        movie.id == movieId;
    }
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.LightGray,
            elevation = 5.dp
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
            Spacer(modifier = Modifier.width(100.dp))
            Text(text = "Movies")
        }
    }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MovieRow(movie = newMovieList.first())
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "Movie Images")
                HorizontalScrollableImageView(newMovieList)


            }

        }
    }

}

@Composable
private fun HorizontalScrollableImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 5.dp
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = "Movie Poster"
                )
            }
        }
    }
}

