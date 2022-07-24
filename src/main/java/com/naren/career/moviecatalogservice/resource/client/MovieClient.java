package com.naren.career.moviecatalogservice.resource.client;

import com.naren.career.moviecatalogservice.resource.bean.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="movie-info-service", url = "${client.movie.baseUrl}")
public interface MovieClient {

    @GetMapping("{userId}")
    Movie getMovie(@PathVariable String userId);
}
