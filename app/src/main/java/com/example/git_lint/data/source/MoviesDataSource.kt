package com.example.git_lint.data.source

import com.example.git_lint.data.Movie

interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?

    fun getMovies(): List<Movie>
}