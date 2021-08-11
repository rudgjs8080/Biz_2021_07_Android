package com.rudgjs8080.topnews.service.impl;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rudgjs8080.topnews.adapter.NewsViewAdapter;
import com.rudgjs8080.topnews.config.NaverAPI;
import com.rudgjs8080.topnews.model.NaverNewsDTO;
import com.rudgjs8080.topnews.model.NaverParent;
import com.rudgjs8080.topnews.service.NaverRetrofit;
import com.rudgjs8080.topnews.service.NaverRetrofitClient;
import com.rudgjs8080.topnews.service.NaverService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverNewsServiceImplV1 implements NaverService {


    private RecyclerView newsListView;
    public NaverNewsServiceImplV1(RecyclerView newsListView) {
        this.newsListView = newsListView;
    }

    @Override
    public void getNews(String search) {

        Call<NaverParent> naverCall = NaverRetrofitClient
                .getApiClient()
                .getNews(NaverAPI.CLIENT_ID,NaverAPI.CLIENT_SECRET,search,1,20);

        naverCall.enqueue(new Callback<NaverParent>() {
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                int resCode = response.code();
                if(resCode < 300){
                    Log.d("네이버 뉴스 조회 : ", response.body().toString());

                    List<NaverNewsDTO> newsList = response.body().items;
                    NewsViewAdapter viewAdapter = new NewsViewAdapter(newsList);

                    newsListView.setAdapter(viewAdapter);

                    RecyclerView.LayoutManager layoutManager
                            = new LinearLayoutManager(newsListView.getContext());
                    newsListView.setLayoutManager(layoutManager);
                }else {
                    Log.d("오류 발생 : ",response.toString());
                }
            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

            }
        });
        }
}
