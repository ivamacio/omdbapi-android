package com.example.omdb.feature.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omdb.R;
import com.example.omdb.core.api.OMDbApiService;
import com.example.omdb.core.base.BaseActivity;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends BaseActivity implements DetailsContract.View {
    private DetailsContract.Presenter presenter;

    private static String BUNDLE_IMDB_ID = "imdbId";

    protected View movieContainerView;
    protected View loadingView;
    protected ImageView posterImageView;
    protected ImageView thumbnailImageView;
    protected TextView titleTextView;
    protected TextView yearRuntimeTextView;
    protected TextView ratingTextView;
    protected TextView plotTextView;
    protected TextView actorsTextView;
    protected TextView directorTextView;
    protected TextView productionTextView;
    protected TextView writersTextView;

    public static Intent newIntent(Context context, String imdbId) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(BUNDLE_IMDB_ID, imdbId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DetailsPresenter(this);
        presenter.loadApi(OMDbApiService.getInstance(this));
        setContentView(R.layout.activity_details);

        movieContainerView = findViewById(R.id.ll_movie_container);
        loadingView = findViewById(R.id.loading);
        posterImageView = findViewById(R.id.iv_poster);
        thumbnailImageView = findViewById(R.id.iv_thumbnail);
        titleTextView = findViewById(R.id.tv_title);
        plotTextView = findViewById(R.id.tv_plot);
        yearRuntimeTextView = findViewById(R.id.tv_year_runtime);
        ratingTextView = findViewById(R.id.tv_rating);
        actorsTextView = findViewById(R.id.tv_actors);
        directorTextView = findViewById(R.id.tv_director);
        productionTextView = findViewById(R.id.tv_production);
        writersTextView = findViewById(R.id.tv_writers);

        presenter.start();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            presenter.onSearchMovie(bundle.getString(BUNDLE_IMDB_ID));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setPoster(String poster) {
        Picasso.with(this).load(poster).fit().centerCrop().into(posterImageView);
    }

    @Override
    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    @Override
    public void setThumbnail(String poster) {
        Picasso.with(this).load(poster).fit().into(thumbnailImageView);
    }

    @Override
    public void setPlot(String plot) {
        plotTextView.setText(plot);
    }

    @Override
    public void setYearAndRuntime(String year, String runtime) {
        yearRuntimeTextView.setText(getString(R.string.year_and_runtime, year, runtime));
    }

    @Override
    public void setActors(String actors) {
        actorsTextView.setText(actors);
    }

    @Override
    public void setDirector(String director) {
        directorTextView.setText(director);
    }

    @Override
    public void setProduction(String production) {
        productionTextView.setText(production);
    }

    @Override
    public void setWriters(String writers) {
        writersTextView.setText(writers);
    }

    @Override
    public void setIMDbRating(String imdbRating) {
        ratingTextView.setText(getString(R.string.imdb_rating, imdbRating));
    }

    @Override
    public void showError() {
        Toast.makeText(this, getText(R.string.error_state), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        movieContainerView.setVisibility(View.GONE);
    }

    @Override
    public void dismissLoading() {
        loadingView.setVisibility(View.GONE);
        movieContainerView.setVisibility(View.VISIBLE);
    }
}