package com.naren.career.moviecatalogservice.resource.bean;

public class Movie {

    private String name;
    private String movieId;
    private String desc;

    public Movie(){}

    public Movie(String name, String movieId, String desc) {
        this.name = name;
        this.movieId = movieId;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}