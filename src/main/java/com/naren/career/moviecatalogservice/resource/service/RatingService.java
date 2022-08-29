package com.naren.career.moviecatalogservice.resource.service;

import com.naren.career.moviecatalogservice.resource.bean.Rating;
import com.naren.career.moviecatalogservice.resource.bean.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(
            fallbackMethod ="getUserRatingFallBack",
            threadPoolKey = "movieInfoPool",
            // BulkHead pattern implementation to separate the thread pool to each service.s
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value = "10"),
                    @HystrixProperty(name="maxQueueSize", value = "20"),
            })
    public UserRating getUserRating(String userId){
        UserRating userRating = restTemplate.getForObject("http://rating-data-service/rating/user/" + userId, UserRating.class);
        System.out.println("userRating is: "+userRating);
        return userRating;
    }

    public UserRating getUserRatingFallBack(String userId){
        UserRating userRating = new UserRating();
        Rating rating = new Rating();
        rating.setRating(10);
        rating.setMovieId(userId);
        userRating.setUserRating(List.of(rating));
        System.out.println("calling getUserRatingFallBack ");
        return userRating;
    }
}
