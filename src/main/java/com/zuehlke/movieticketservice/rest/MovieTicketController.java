package com.zuehlke.movieticketservice.rest;

import com.zuehlke.movieticketservice.adapter.movie.MovieServiceAdapter;
import com.zuehlke.movieticketservice.adapter.rating.RatingAdapter;
import com.zuehlke.movieticketservice.domain.MovieDetails;
import com.zuehlke.movieticketservice.domain.MovieSummary;
import com.zuehlke.movieticketservice.domain.Rating;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MovieTicketController {

    private MovieServiceAdapter movieServiceAdapter;
    private RatingAdapter ratingAdapter;

    public MovieTicketController(MovieServiceAdapter movieServiceAdapter, RatingAdapter ratingAdapter) {
        this.movieServiceAdapter = movieServiceAdapter;
        this.ratingAdapter = ratingAdapter;
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }

    @GetMapping("/api/v1/movies")
    @ResponseBody
    public List<MovieSummary> getMovies() {
        return new MovieServiceAdapter("https://movie-service.herokuapp.com").getAll();
    }

    @GetMapping("/api/v1/movies/{id}")
    @ResponseBody
    public MovieDetails getMovieDetails(@PathVariable("id") long id) {
        Optional<MovieDetails> optionalMovie = movieServiceAdapter.getMovieById(id);

        if(!optionalMovie.isPresent()) {
            throw new NotFoundException();
        }
        MovieDetails movie = optionalMovie.get();

        List<Rating> ratings = ratingAdapter.getRatingsById(id);
        movie.setRatings(ratings);
        return movie;
    }

}