package com.luisvillalobos.dev.moviesfeed_exercisemvp.http;

import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.apimodel.OpenMovieDb;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesExtraInfoApiService {
    @GET("/")
    Observable<OpenMovieDb> getExtraInfoMoview(@Query("t") String title);
}
