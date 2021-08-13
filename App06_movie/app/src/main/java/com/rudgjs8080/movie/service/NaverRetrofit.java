package com.rudgjs8080.movie.service;

import com.rudgjs8080.movie.model.MovieParent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface NaverRetrofit {
    @GET("movie.json")
    public Call<MovieParent> getMovie(
            @Header("X-Naver-Client-Id") String clientId,
            @Header("X-Naver-Client-Secret") String clientSecret,
            @Query("query") String query,
            @Query("start") int start,
            @Query("display") int end
    );


}
