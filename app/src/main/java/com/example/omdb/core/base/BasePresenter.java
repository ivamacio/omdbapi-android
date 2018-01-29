package com.example.omdb.core.base;

import com.example.omdb.core.api.OMDbApiService;

/**
 * Created by ivamaciomagalhaes on 1/27/18.
 */

public interface BasePresenter {
    void start();

    void stop();

    void loadApi(OMDbApiService service);
}
