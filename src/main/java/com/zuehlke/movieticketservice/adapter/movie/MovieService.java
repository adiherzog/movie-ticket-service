package com.zuehlke.movieticketservice.adapter.movie;

import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface MovieService {

    @RequestLine("GET /api/v1/movies")
    List<MovieServiceResponse> getMovies();

    @RequestLine("GET /api/v1/movies/{id}")
    MovieServiceResponse getMovieDetails(@Param("id") long id);
}
