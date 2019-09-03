package com.luisvillalobos.dev.moviesfeed_exercisemvp.movies;

import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.apimodel.Result;

import io.reactivex.Observable;

public interface Repository {

    Observable<Result> getResultFromNetwork();
    Observable<Result> getResultFromCache();
    Observable<Result> getResultData();

    Observable<String> getCountryFromNetwork();
    Observable<String> getCountryFromCache();
    Observable<String> getCountryData();

}
