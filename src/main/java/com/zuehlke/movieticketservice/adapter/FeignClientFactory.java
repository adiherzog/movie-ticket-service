package com.zuehlke.movieticketservice.adapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuehlke.movieticketservice.adapter.movie.MovieServiceAdapter;
import feign.Logger;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;

public class FeignClientFactory {

    public static <T> T create(String url, Class<T> clazz) {
        return FeignClientFactory.createBuilder().target(clazz, url);
    }

    public static <T> T createWithFallback(String url, Class<T> clazz, T fallback) {
        return FeignClientFactory.createBuilder().target(clazz, url, fallback);
    }

    private static <T> HystrixFeign.Builder createBuilder() {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        return HystrixFeign.builder()
                .decoder(new JacksonDecoder(mapper))
                .logger(new Slf4jLogger(MovieServiceAdapter.class))
                .logLevel(Logger.Level.FULL);
    }

}
