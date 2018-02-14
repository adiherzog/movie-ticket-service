package com.zuehlke.movieticketservice.adapter.rating;

import com.zuehlke.movieticketservice.adapter.FeignClientFactory;
import com.zuehlke.movieticketservice.domain.Rating;

import java.util.List;
import java.util.stream.Collectors;

public class RatingAdapter {

    private final RatingService ratingService;

    public RatingAdapter(String url) {
        ratingService = FeignClientFactory.create(url, RatingService.class);
    }

    public List<Rating> getRatingsById(long id) {
        List<MovieRatingsResponse> ratings = ratingService.getRatings(id);

        return ratings
            .stream()
            .map(ratingResponse -> new Rating(ratingResponse.getSource(), ratingResponse.getValue()))
            .collect(Collectors.toList());
    }

}
