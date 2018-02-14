package com.zuehlke.movieticketservice.adapter;

import com.zuehlke.movieticketservice.adapter.movie.MovieServiceAdapter;
import com.zuehlke.movieticketservice.domain.MovieDetails;
import com.zuehlke.movieticketservice.domain.MovieSummary;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class MovieServiceAdapterIntegrationTest {

    static {
        System.setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", "5000");
    }

    @Test
    public void getAll() throws Exception {
        MovieServiceAdapter movieServiceAdapter = new MovieServiceAdapter("https://movie-service.herokuapp.com/");

        List<MovieSummary> movies = movieServiceAdapter.getAll();

        assertThat(movies, hasSize(7));
        assertThat(movies, hasItem(new MovieSummary(1, "Batman Begins", "https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg")));
    }

    @Test
    public void getMovieById() throws Exception {
        MovieServiceAdapter movieServiceAdapter = new MovieServiceAdapter("https://movie-service.herokuapp.com/");

        Optional<MovieDetails> movieDetail = movieServiceAdapter.getMovieById(1);

        assertThat(movieDetail.isPresent(), is(true));
    }

}