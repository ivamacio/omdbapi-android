package com.example.omdb.feature.details;

import com.example.omdb.core.base.BasePresenter;
import com.example.omdb.core.base.BaseView;
import com.example.omdb.models.Card;

import java.util.List;

/**
 * Created by ivamaciomagalhaes on 1/28/18.
 */

public interface DetailsContract {

    interface View extends BaseView<Presenter> {

        void setPoster(String poster);

        void setTitle(String title);

        void setThumbnail(String poster);

        void setPlot(String plot);

        void setYearAndRuntime(String year, String runtime);

        void setActors(String actors);

        void setDirector(String director);

        void setProduction(String production);

        void setWriters(String writers);

        void setIMDbRating(String imdbRating);

        void showError();

        void showLoading();

        void dismissLoading();

        }

    interface Presenter extends BasePresenter {
        void onSearchMovie(String movie);
    }
}