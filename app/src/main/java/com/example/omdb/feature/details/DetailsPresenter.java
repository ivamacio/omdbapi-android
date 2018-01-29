package com.example.omdb.feature.details;

import com.example.omdb.R;
import com.example.omdb.core.api.OMDbApiService;
import com.example.omdb.models.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ivamaciomagalhaes on 1/28/18.
 */

public class DetailsPresenter implements DetailsContract.Presenter {

    private DetailsContract.View view;
    private OMDbApiService apiService;
    private String imdbId;

    public DetailsPresenter(DetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.showLoading();
    }

    @Override
    public void stop() {

    }

    @Override
    public void loadApi(OMDbApiService service) {
        apiService = service;
    }

    @Override
    public void onSearchMovie(String imbdId) {
        this.imdbId = imbdId;
        view.showLoading();
        Call<Movie> movieCallback = apiService.getOMDbApi().searchByOMDbId(imbdId);
        movieCallback.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                view.setPoster(movie.getPoster());
                view.setThumbnail(movie.getPoster());
                view.setTitle(movie.getTitle());
                view.setYearAndRuntime(movie.getYear(), movie.getRuntime());
                view.setIMDbRating(movie.getImdbRating());
                view.setPlot(movie.getPlot());
                view.setDirector(movie.getDirector());
                view.setActors(movie.getActors());
                view.setProduction(movie.getProduction());
                view.setWriters(movie.getWriter());

                view.dismissLoading();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                view.showError();
            }
        });
    }
}