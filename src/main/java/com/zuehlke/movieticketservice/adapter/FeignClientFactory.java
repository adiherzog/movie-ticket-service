package com.zuehlke.movieticketservice.adapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuehlke.movieticketservice.adapter.movie.MovieServiceAdapter;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;

public class FeignClientFactory {

    public static <T> T create(String url, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        return Feign.builder()
                .decoder(new JacksonDecoder(mapper))
                .logger(new Slf4jLogger(MovieServiceAdapter.class))
                .logLevel(Logger.Level.FULL)
                .target(clazz, url);
    }
}
