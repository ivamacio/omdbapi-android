package com.example.omdb.feature.search;

import com.example.omdb.core.api.OMDbApiService;
import com.example.omdb.models.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ivamaciomagalhaes on 1/28/18.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;
    private OMDbApiService apiService;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.hideKeyboard();
    }

    @Override
    public void stop() {
        //Nada
    }

    @Override
    public void loadApi(OMDbApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void onSearchedMovie(String title) {
        view.cleanSearch();
        view.hideKeyboard();
        view.showLoading();
        Call<SearchResponse> moviesCallback = apiService.getOMDbApi().searchByTitle(title);
        moviesCallback.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse movies = response.body();
                if (movies.getError() != null) {
                    view.showError();
                } else {
                    view.showMovies(movies.getResults());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                view.showError();
            }
        });
    }
}
