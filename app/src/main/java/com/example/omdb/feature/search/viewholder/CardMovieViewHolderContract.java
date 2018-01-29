package com.example.omdb.feature.search.viewholder;

/**
 * Created by ivamaciomagalhaes on 1/28/18.
 */

public interface CardMovieViewHolderContract {
    void setTitle(String title);
    void setYear(String year);
    void setMovieThumbnail(String poster);
    void resetViews();
}
