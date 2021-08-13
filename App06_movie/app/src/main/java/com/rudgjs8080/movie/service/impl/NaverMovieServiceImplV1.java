package com.rudgjs8080.movie.service.impl;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rudgjs8080.movie.adapter.MovieViewAdapter;
import com.rudgjs8080.movie.config.NaverAPI;
import com.rudgjs8080.movie.model.MovieDTO;
import com.rudgjs8080.movie.model.MovieParent;
import com.rudgjs8080.movie.service.NaverMovieService;
import com.rudgjs8080.movie.service.RetrofitAPIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverMovieServiceImplV1 implements NaverMovieService {

    protected RecyclerView recyclerView;

    public NaverMovieServiceImplV1(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public MovieDTO getMovies(String search) {

        Call<MovieParent> retrofitReturn = RetrofitAPIClient.getApiClient()
                .getMovie(NaverAPI.CLIENT_ID,NaverAPI.CLIENT_SECRET,search,1,10);

        retrofitReturn.enqueue(new Callback<MovieParent>() {
            @Override
            public void onResponse(Call<MovieParent> call, Response<MovieParent> response) {
                int resCode = response.code();
                if(resCode < 300){
                    Log.d("네이버 데이터: " , response.body().toString());
                    MovieParent m_parent =response.body();
                    List<MovieDTO> m_List = m_parent.items;

                    MovieViewAdapter movieViewAdapter = new MovieViewAdapter(m_List);

                    recyclerView.setAdapter(movieViewAdapter);

                    RecyclerView.LayoutManager layoutManager
                            = new LinearLayoutManager(recyclerView.getContext());
                    recyclerView.setLayoutManager(layoutManager);

                } else {
                    Log.d("Error 발생 : ", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<MovieParent> call, Throwable t) {

            }
        });


        return null;
    }
}
