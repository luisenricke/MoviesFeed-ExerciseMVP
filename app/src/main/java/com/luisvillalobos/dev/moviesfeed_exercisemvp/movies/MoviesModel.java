package com.luisvillalobos.dev.moviesfeed_exercisemvp.movies;

import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.apimodel.Result;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class MoviesModel implements MoviewMVP.Model {
    private Repository repository;

    public MoviesModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<ViewModel> result() {
        return Observable.zip(repository.getResultData(), repository.getCountryData(), new BiFunction<Result, String, ViewModel>() {
            @Override
            public ViewModel apply(Result result, String s) {
                //TODO: Cambiar
                return new ViewModel(result.getTitle(), s);
            }
        });
    }
}
