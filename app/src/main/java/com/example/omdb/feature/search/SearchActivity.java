package com.example.omdb.feature.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.omdb.R;
import com.example.omdb.core.api.OMDbApiService;
import com.example.omdb.core.base.BaseActivity;
import com.example.omdb.core.base.BaseRecyclerView;
import com.example.omdb.core.utils.ContentState;
import com.example.omdb.feature.common.components.CustomSearch;
import com.example.omdb.feature.details.DetailsActivity;
import com.example.omdb.models.Card;

import java.util.List;

public class SearchActivity extends BaseActivity implements SearchContract.View,
                                                            CustomSearch.CustomSearchListener,
                                                            SearchAdapter.SearchAdapterListener {
    private SearchContract.Presenter presenter;

    protected CustomSearch customSearch;
    protected BaseRecyclerView recyclerView;
    protected SearchAdapter adapter;
    protected View loading;
    protected RecyclerView.LayoutManager layoutManager;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SearchPresenter(this);
        presenter.loadApi(OMDbApiService.getInstance(this));
        setContentView(R.layout.activity_search);

        customSearch = findViewById(R.id.cs_search);
        recyclerView = findViewById(R.id.rv_card_movies);
        loading = findViewById(R.id.loading);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                                                false);
        adapter = new SearchAdapter(getBaseContext(), this);
        recyclerView.initLoadingStateView(loading);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        customSearch.setListener(this);
        presenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMovies(List<Card> results) {
        adapter.setDataSource(results);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void cleanSearch() {
        customSearch.cleanSearch();
    }

    @Override
    public void showError() {
        adapter.setContentState(ContentState.ERROR);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSearch(String movie) {
        hideKeyboard();
        presenter.onSearchedMovie(movie);
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                                        getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showLoading() {
        adapter.setContentState(ContentState.LOADING);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCardClicked(String imdbId) {
        Intent intent = DetailsActivity.newIntent(this, imdbId);
        startActivity(intent);
    }
}