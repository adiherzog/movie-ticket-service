package com.zuehlke.movieticketservice.adapter.rating;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieRatingsResponse {

    private final String source;
    private final String value;

    @JsonCreator
    public MovieRatingsResponse(@JsonProperty("source") String source, @JsonProperty("value") String value) {
        this.source = source;
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public String getValue() {
        return value;
    }

}
