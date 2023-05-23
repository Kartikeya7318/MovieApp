package com.kartikeya.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kartikeya.movieapp.model.Movie
import com.kartikeya.movieapp.model.getMovies
import com.kartikeya.movieapp.navigation.MovieScreens
import com.kartikeya.movieapp.widgets.MovieRow


@Composable
fun HomeScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Magenta,
            elevation = 5.dp
        ) {
            Text(text = "Movies")
        }
    }) {
        MainContent(navController=navController)
    }
}
@Composable
fun MainContent(
    movieList: List<Movie> = getMovies(),
    navController: NavController
) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = movieList) {
                MovieRow(movie = it) { movie ->
                    navController.navigate(route = MovieScreens.DetailsScreen.name+"/${movie}")
//                    Log.d("click", "MainContent: $movie")
                }

            }

        }
    }
}
