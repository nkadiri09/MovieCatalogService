package com.naren.career.moviecatalogservice.resource.controller;


import com.naren.career.moviecatalogservice.resource.bean.CatalogItem;
import com.naren.career.moviecatalogservice.resource.bean.Movie;
import com.naren.career.moviecatalogservice.resource.bean.UserRating;
import com.naren.career.moviecatalogservice.resource.client.MovieClient;
import com.naren.career.moviecatalogservice.resource.service.MovieService;
import com.naren.career.moviecatalogservice.resource.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieCatalogResource.class);

    @Autowired
    private MovieClient movieClient;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private MovieService movieService;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        LOGGER.info("In getCatalog for the user : {} ", userId);
        LOGGER.info("port is : {} ", port);

        /*List<Rating> ratings = Arrays.asList(new Rating("100", 5),
                new Rating("101", 6));*/
        UserRating userRating = ratingService.getUserRating(userId);

        Stream<CatalogItem> catalogItemStream = userRating.getUserRating().stream().map(rating -> {
            // Movie movie = restTemplate.getForObject("http://localhost:8002/movies/" + rating.getMovieId(), Movie.class);

            Movie movie = movieService.getMovie(rating.getMovieId());

            // consolidating the results
            return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
        });
        return catalogItemStream.collect(Collectors.toList());
    }

    @RequestMapping("/feign/{userId}")
    public List<CatalogItem> getCatalogWithFeign(@PathVariable("userId") String userId){

        LOGGER.info("In getCatalog for the user :{} ", userId);
        /*List<Rating> ratings = Arrays.asList(new Rating("100", 5),
                new Rating("101", 6));*/
        UserRating userRating = ratingService.getUserRating(userId);

        Stream<CatalogItem> catalogItemStream = userRating.getUserRating().stream().map(rating -> {
            // Movie movie = restTemplate.getForObject("http://localhost:8002/movies/" + rating.getMovieId(), Movie.class);

            Movie movie = movieClient.getMovie(rating.getMovieId());

            // consolidating the results
            return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
        });
        return catalogItemStream.collect(Collectors.toList());
    }
}
