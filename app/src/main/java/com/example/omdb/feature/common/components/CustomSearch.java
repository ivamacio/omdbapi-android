package com.example.omdb.feature.common.components;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.omdb.R;
import com.example.omdb.core.base.BaseLayout;

/**
 * Created by ivamaciomagalhaes on 1/27/18.
 */

public class CustomSearch extends BaseLayout {

    private EditText inputEditText;
    private CustomSearchListener listener;

    public CustomSearch(Context context) {
        super(context);
    }

    public CustomSearch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSearch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomSearch(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setListener(CustomSearchListener listener) {
        this.listener = listener;
    }

    public void cleanSearch() {
        inputEditText.setText("");
    }

    @Override
    public void init(Context context) {
        //TODO: Implement onClick when magnifying glass is tapped.
        View view = View.inflate(context, R.layout.view_custom_search, this);
        inputEditText = view.findViewById(R.id.et_input);
        inputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (listener != null) {
                        listener.onSearch(inputEditText.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public interface CustomSearchListener {
        void onSearch(String movie);
    }
}