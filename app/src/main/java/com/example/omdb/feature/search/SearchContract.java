package com.example.omdb.feature.search;

import com.example.omdb.core.base.BasePresenter;
import com.example.omdb.core.base.BaseView;
import com.example.omdb.models.Card;

import java.util.List;

/**
 * Created by ivamaciomagalhaes on 1/28/18.
 */

public interface SearchContract {

    interface View extends BaseView<Presenter> {

        void showMovies(List<Card> results);

        void cleanSearch();

        void hideKeyboard();

        void showError();

        void showLoading();

    }

    interface Presenter extends BasePresenter {
        void onSearchedMovie(String movie);
    }

}