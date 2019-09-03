package com.luisvillalobos.dev.moviesfeed_exercisemvp.movies;

import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.MoviesApiService;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.MoviesExtraInfoApiService;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.apimodel.OpenMovieDb;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.apimodel.Result;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.apimodel.TopMoviesRated;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MoviesRepository implements Repository {

    private MoviesApiService moviesApiService;
    private MoviesExtraInfoApiService moviesExtraInfoApiService;

    private List<String> countries;
    private List<Result> results;

    private long lastTimestamp;

    private static final long CACHE_LIFETIME = 20 * 1000; // 20 segundos

    public MoviesRepository(MoviesApiService info, MoviesExtraInfoApiService infoExtra) {
        this.moviesApiService = info;
        this.moviesExtraInfoApiService = infoExtra;
        this.lastTimestamp = System.currentTimeMillis();
        this.countries = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    public boolean isUpdated() {
        return (System.currentTimeMillis() - lastTimestamp) < CACHE_LIFETIME;
    }

    @Override
    public Observable<Result> getResultFromNetwork() {
        Observable<TopMoviesRated> topMoviesRatedObservable = moviesApiService.getTopMoviesRated(1)
                .concatWith(moviesApiService.getTopMoviesRated(2)
                        .concatWith(moviesApiService.getTopMoviesRated(3)));

        return topMoviesRatedObservable.
                concatMap(new Function<TopMoviesRated, ObservableSource<? extends Result>>() {
                    @Override
                    public Observable<Result> apply(TopMoviesRated topMoviesRated) {
                        return Observable.fromIterable(topMoviesRated.getResults());
                    }
                })
                .doOnNext(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        results.add(result);
                    }
                });
    }

    @Override
    public Observable<Result> getResultFromCache() {
        if (isUpdated())
            return Observable.fromIterable(results);
        else {
            lastTimestamp = System.currentTimeMillis();
            results.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<Result> getResultData() {
        return getResultFromCache().switchIfEmpty(getResultFromNetwork());
    }

    @Override
    public Observable<String> getCountryFromNetwork() {
        return getResultFromNetwork().concatMap(new Function<Result, Observable<OpenMovieDb>>() {
            @Override
            public Observable<OpenMovieDb> apply(Result result) {
                return moviesExtraInfoApiService.getExtraInfoMoview(result.getTitle());
            }
        }).concatMap(new Function<OpenMovieDb, Observable<String>>() {
            @Override
            public Observable<String> apply(OpenMovieDb openMovieDb) throws Exception {
                if (openMovieDb == null || openMovieDb.getCountry() == null)
                    return Observable.just("Desconocido");
                else
                    return Observable.just(openMovieDb.getCountry());
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String country) {
                countries.add(country);
            }
        });
    }

    @Override
    public Observable<String> getCountryFromCache() {
        if (isUpdated())
            return Observable.fromIterable(countries);
        else {
            lastTimestamp = System.currentTimeMillis();
            countries.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<String> getCountryData() {
        return getCountryFromCache().switchIfEmpty(getCountryFromNetwork());
    }
}
