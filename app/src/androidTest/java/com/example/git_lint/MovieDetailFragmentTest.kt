package com.example.git_lint

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bumptech.glide.request.RequestOptions
import com.example.git_lint.data.Movie
import com.example.git_lint.data.source.MoviesRemoteDataSource
import com.example.git_lint.factory.MovieFragmentFactory
import com.example.git_lint.ui.movie.MovieDetailFragment
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest{

    @Test
    fun test_recreateActivity() {

        // SETUP
        val movieId = 1
        val title = "The Rundown"
        val description = "A tough aspiring chef is hired to bring home a mobster's son from the Amazon but " +
                "becomes involved in the fight against an oppressive town operator and the search " +
                "for a legendary treasure."
        val movie = Movie(
            movieId,
            title,
            "https://nyc3.digitaloceanspaces.com/open-api-spaces/open-api-static/blog/1/The_Rundown-the_rundown.png",
            description ,
            arrayListOf("R.J. Stewart", "James Vanderbilt"),
            arrayListOf("Dwayne Johnson", "Seann William Scott", "Rosario Dawson", "Christopher Walken")
        )

        val moviesDataSource = mockk<MoviesRemoteDataSource>()
        every {
            moviesDataSource.getMovie(movieId)
        } returns movie

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.default_image)
            .error(R.drawable.default_image)
        val fragmentFactory = MovieFragmentFactory(requestOptions, moviesDataSource)
        val bundle = Bundle()
        bundle.putInt("movie_id", movieId)
        val scenario = launchFragmentInContainer<MovieDetailFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        // VERIFY
        Espresso.onView(withId(R.id.movie_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(title)))

        Espresso.onView(withId(R.id.movie_description))
            .check(ViewAssertions.matches(ViewMatchers.withText(description)))

        // VERIFY nothing has changed
        Espresso.onView(withId(R.id.movie_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(title)))

        Espresso.onView(withId(R.id.movie_description))
            .check(ViewAssertions.matches(ViewMatchers.withText(description)))
    }

    @Test
    fun test_isMovieDataVisible() {

        // SETUP
        val movieId = 1
        val title = "The Rundown"
        val description = "A tough aspiring chef is hired to bring home a mobster's son from the Amazon but " +
                "becomes involved in the fight against an oppressive town operator and the search " +
                "for a legendary treasure."
        val movie = Movie(
            movieId,
            title,
            "https://nyc3.digitaloceanspaces.com/open-api-spaces/open-api-static/blog/1/The_Rundown-the_rundown.png",
            description ,
            arrayListOf("R.J. Stewart", "James Vanderbilt"),
            arrayListOf("Dwayne Johnson", "Seann William Scott", "Rosario Dawson", "Christopher Walken")
        )

        // NOTE:
        // Also could have built a "FakeMoviesRemoteDataSource" (AKA a STUB).
        // I don't think it matters in this case.
        // Probably for a larger repository and more complex app I would stub the repository. Then
        // you could test errors, various success cases, etc...
        val moviesDataSource = mockk<MoviesRemoteDataSource>()
        every {
            moviesDataSource.getMovie(movieId)
        } returns movie

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.default_image)
            .error(R.drawable.default_image)
        val fragmentFactory = MovieFragmentFactory(requestOptions, moviesDataSource)
        val bundle = Bundle()
        bundle.putInt("movie_id", movieId)
        val scenario = launchFragmentInContainer<MovieDetailFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        // VERIFY
        Espresso.onView(withId(R.id.movie_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(title)))

        Espresso.onView(withId(R.id.movie_description))
            .check(ViewAssertions.matches(ViewMatchers.withText(description)))

    }

}



















