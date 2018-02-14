package com.zuehlke.movieticketservice.adapter.rating;

import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface RatingService {

    @RequestLine("GET /api/v1/ratings/{id}")
    List<MovieRatingsResponse> getRatings(@Param("id") long id);

}
