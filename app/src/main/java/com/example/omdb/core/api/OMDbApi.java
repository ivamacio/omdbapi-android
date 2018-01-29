package com.example.omdb.core.api;

import com.example.omdb.models.Movie;
import com.example.omdb.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ivamaciomagalhaes on 1/27/18.
 */

public interface OMDbApi {

    @GET("/")
    Call<SearchResponse> searchByTitle(@Query("s") String title);

    @GET("/")
    Call<Movie> searchByOMDbId(@Query("i") String omdbId);
}