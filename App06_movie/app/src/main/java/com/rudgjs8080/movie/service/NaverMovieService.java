package com.rudgjs8080.movie.service;

import com.rudgjs8080.movie.model.MovieDTO;

public interface NaverMovieService {

    public MovieDTO getMovies(String search);

}
