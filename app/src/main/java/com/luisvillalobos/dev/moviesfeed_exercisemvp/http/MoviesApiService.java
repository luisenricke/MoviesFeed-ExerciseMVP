package com.luisvillalobos.dev.moviesfeed_exercisemvp.http;

import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.apimodel.TopMoviesRated;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MoviesApiService {

    @GET("top_rated")
    Observable<TopMoviesRated> getTopMoviesRated(@Query("page") int page);

}
