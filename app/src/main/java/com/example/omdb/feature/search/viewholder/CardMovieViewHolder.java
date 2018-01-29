package com.example.omdb.feature.search.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.omdb.R;
import com.squareup.picasso.Picasso;

/**
 * Created by ivamaciomagalhaes on 1/28/18.
 */

public class CardMovieViewHolder extends RecyclerView.ViewHolder implements CardMovieViewHolderContract {

    protected LinearLayout cardContainerLinearLayout;
    private ImageView thumbnailImageView;
    private TextView movieTitleTextView;
    private TextView yearTextView;

    private Context context;

    public LinearLayout getCardContainerLinearLayout() {
        return cardContainerLinearLayout;
    }

    public CardMovieViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        cardContainerLinearLayout = itemView.findViewById(R.id.ll_card_container);
        thumbnailImageView = itemView.findViewById(R.id.iv_thumbnail);
        movieTitleTextView = itemView.findViewById(R.id.tv_movie_title);
        yearTextView = itemView.findViewById(R.id.tv_year);
    }

    @Override
    public void setTitle(String title) {
        movieTitleTextView.setText(title);
    }

    @Override
    public void setYear(String year) {
        yearTextView.setText(year);
    }

    @Override
    public void setMovieThumbnail(String poster) {
        Picasso.with(context).load(poster).fit().into(thumbnailImageView);
    }

    @Override
    public void resetViews() {
        thumbnailImageView.setImageBitmap(null);
        movieTitleTextView.setText("");
        yearTextView.setText("");
    }

}