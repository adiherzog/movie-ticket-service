package com.zuehlke.movieticketservice;

import com.zuehlke.movieticketservice.adapter.movie.MovieServiceAdapter;
import com.zuehlke.movieticketservice.adapter.rating.RatingAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTicketServiceApplication.class, args);
	}

	@Bean
	public MovieServiceAdapter movieServiceAdapter() {
		return new MovieServiceAdapter("https://movie-service.herokuapp.com");
	}

	@Bean
	public RatingAdapter ratingAdapter() {
		return new RatingAdapter("https://movie-rating-service.herokuapp.com");
	}

}
