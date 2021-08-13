package com.rudgjs8080.movie.adapter;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rudgjs8080.movie.R;
import com.rudgjs8080.movie.model.MovieDTO;
import com.squareup.picasso.Picasso;


import java.util.List;

public class MovieViewAdapter extends RecyclerView.Adapter {
    private List<MovieDTO> m_list;
    public MovieViewAdapter(List<MovieDTO> m_list) {
        this.m_list = m_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.movie_item_view, parent, false);

        MovieItemHolder viewholder = new MovieItemHolder(view);
        return viewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MovieItemHolder movieItemHolder = (MovieItemHolder) holder;

        MovieDTO movieDTO = m_list.get(position);

        String i_title = movieDTO.getTitle();
        Spanned sp_title = Html.fromHtml(i_title, Html.FROM_HTML_MODE_LEGACY);
        movieItemHolder.i_title.setText(sp_title);

        String i_director = movieDTO.getDirector();
        movieItemHolder.i_director.setText("감독 : " + i_director);

        String i_actor = movieDTO.getActor();
        movieItemHolder.i_actor.setText("출연배우 : " + i_actor);

        String i_rating = movieDTO.getUserRating();
        movieItemHolder.i_rating.setText("평점 : " + i_rating);

        if (!movieDTO.getImage().isEmpty()) {

            Picasso.get().load(movieDTO.getImage()).into(movieItemHolder.i_img);
        }

    }

    @Override
    public int getItemCount() {
        return m_list == null ? 0 : m_list.size();
    }

    public static class MovieItemHolder extends RecyclerView.ViewHolder{

        public TextView i_title;
        public ImageView i_img;
        public TextView i_director;
        public TextView i_actor;
        public TextView i_rating;

        public MovieItemHolder(@NonNull View itemView) {

            super(itemView);
            i_title = itemView.findViewById(R.id.movie_item_title);
            i_img = itemView.findViewById(R.id.movie_item_image);
            i_director = itemView.findViewById(R.id.movie_item_director);
            i_actor = itemView.findViewById(R.id.movie_item_actor);
            i_rating = itemView.findViewById(R.id.movie_item_userRation);



        }
    }



}
