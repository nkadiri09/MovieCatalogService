package com.naren.career.moviecatalogservice.resource;


import com.naren.career.moviecatalogservice.resource.service.MovieService;
import com.naren.career.moviecatalogservice.resource.service.RatingService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieCatalogResource.class);

    @Autowired
    private RatingService ratingService;

    @Autowired
    private MovieService movieService;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        LOGGER.info("In getCatalog for the user :{} ", userId);
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
}
