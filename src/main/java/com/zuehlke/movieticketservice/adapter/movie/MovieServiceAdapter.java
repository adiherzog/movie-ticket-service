package com.zuehlke.movieticketservice.adapter.movie;

import com.zuehlke.movieticketservice.adapter.FeignClientFactory;
import com.zuehlke.movieticketservice.domain.MovieDetails;
import com.zuehlke.movieticketservice.domain.MovieSummary;
import feign.FeignException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieServiceAdapter {

    private MovieService movieService;

    public MovieServiceAdapter(String url) {
        movieService = FeignClientFactory.create(url, MovieService.class);
    }

    public List<MovieSummary> getAll() {
        List<MovieServiceResponse> movies = movieService.getMovies();

        return movies
            .stream()
            .map(movie -> new MovieSummary(movie.getId(), movie.getTitle(), movie.getPoster()))
            .collect(Collectors.toList());
    }

    public Optional<MovieDetails> getMovieById(long id) {
        MovieServiceResponse movieDetails;

        try {
            movieDetails = movieService.getMovieDetails(id);
        } catch(FeignException e) {
            return Optional.empty();
        }

        return Optional.of(new MovieDetails(movieDetails.getId(), movieDetails.getTitle(), movieDetails.getPoster(),
                movieDetails.getPlot(), movieDetails.getYear(), movieDetails.getGenre(), null));
    }

}
