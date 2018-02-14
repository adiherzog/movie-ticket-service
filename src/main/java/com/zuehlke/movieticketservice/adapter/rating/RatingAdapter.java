package com.zuehlke.movieticketservice.adapter.rating;

import com.zuehlke.movieticketservice.adapter.FeignClientFactory;
import com.zuehlke.movieticketservice.domain.Rating;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class RatingAdapter {

    private final RatingClient ratingClient;

    public RatingAdapter(String url) {
        ratingClient = FeignClientFactory.createWithFallback(url, RatingClient.class, (id) -> emptyList());
    }

    public List<Rating> getRatingsById(long id) {
        List<MovieRatingsResponse> ratings = ratingClient.getRatings(id);

        return ratings
            .stream()
            .map(ratingResponse -> new Rating(ratingResponse.getSource(), ratingResponse.getValue()))
            .collect(Collectors.toList());
    }

}
