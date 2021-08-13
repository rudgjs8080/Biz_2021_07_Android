package com.rudgjs8080.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.rudgjs8080.movie.service.NaverMovieService;
import com.rudgjs8080.movie.service.impl.NaverMovieServiceImplV1;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar main_toolbar = findViewById(R.id.main_app_toolbar);
        setSupportActionBar(main_toolbar);

        recyclerView = findViewById(R.id.movie_list_view);

        NaverMovieService naverMovieService
                = new NaverMovieServiceImplV1(recyclerView);
        naverMovieService.getMovies("반지");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search)
                .getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("영화이름 검색");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                NaverMovieService naverMovieService
                        = new NaverMovieServiceImplV1(recyclerView);
                naverMovieService.getMovies(query.trim());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }






}