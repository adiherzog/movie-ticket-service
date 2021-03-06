package com.zuehlke.movieticketservice;

import com.zuehlke.movieticketservice.adapter.movie.MovieServiceAdapter;
import com.zuehlke.movieticketservice.adapter.rating.RatingAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class MovieTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTicketServiceApplication.class, args);
	}

	@Bean
	public MovieServiceAdapter movieServiceAdapter(@Value("${endpoint.movie-service}") String url) {
		return new MovieServiceAdapter(url);
	}

	@Bean
	public RatingAdapter ratingAdapter(@Value("${endpoint.movie-rating-service}") String url) {
		return new RatingAdapter(url);
	}

}
