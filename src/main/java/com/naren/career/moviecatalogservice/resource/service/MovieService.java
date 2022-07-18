package com.naren.career.moviecatalogservice.resource.service;

import com.naren.career.moviecatalogservice.resource.Movie;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @HystrixCommand(fallbackMethod ="getMovieFallback" )
    public Movie getMovie(String movieId){

            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://Movie-info-service/movies/" + movieId)
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();

        return movie;
    }

    public Movie getMovieFallback(String movieId){
        System.out.println("calling getMovieFallback");
        return new Movie("Movie Not Found", movieId, "" );
    }
}
