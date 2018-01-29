package com.example.omdb.feature.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.omdb.R;
import com.example.omdb.core.base.BaseAdapter;
import com.example.omdb.feature.search.viewholder.CardMovieViewHolder;
import com.example.omdb.feature.search.viewholder.EmptyStateViewHolder;
import com.example.omdb.feature.search.viewholder.ErrorStateViewHolder;
import com.example.omdb.models.Card;

/**
 * Created by ivamaciomagalhaes on 1/28/18.
 */

public class SearchAdapter extends BaseAdapter<Card> {

    private Context context;
    private SearchAdapterListener listener;

    public SearchAdapter(Context context, SearchAdapterListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ViewTypes.ITEM_VIEW:
                view = getLayoutInflater().inflate(R.layout.view_card_movie_item,
                        parent, false);
                return new CardMovieViewHolder(view, context);
            case ViewTypes.EMPTY_VIEW:
                view = getLayoutInflater().inflate(R.layout.view_empty_state,
                        parent, false);
                return new EmptyStateViewHolder(view);
            case ViewTypes.ERROR_VIEW:
                view = getLayoutInflater().inflate(R.layout.view_error_state,
                        parent, false);
                return new ErrorStateViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case ViewTypes.ITEM_VIEW:
                CardMovieViewHolder cardMovieViewHolder = (CardMovieViewHolder) holder;
                final Card card = getDataSource().get(position);
                cardMovieViewHolder.resetViews();
                cardMovieViewHolder.setMovieThumbnail(card.getPoster());
                cardMovieViewHolder.setTitle(card.getTitle());
                cardMovieViewHolder.setYear(card.getYear());
                cardMovieViewHolder.getCardContainerLinearLayout()
                        .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onCardClicked(card.getImdbId());
                        }
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (getDataSource() == null) {
            return 0;
        } else if (getDataSource().size() > 0) {
            return getDataSource().size();
        } else {
            return EMPTY_STATE_SIZE;
        }
    }

    public interface SearchAdapterListener {
        void onCardClicked(String imdbId);
    }
}