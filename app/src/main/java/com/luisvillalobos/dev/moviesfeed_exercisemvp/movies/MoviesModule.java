package com.luisvillalobos.dev.moviesfeed_exercisemvp.movies;

import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.MoviesApiService;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.MoviesExtraInfoApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {

    @Provides
    public MoviewMVP.Presenter providesMoviePresenter(MoviewMVP.Model moviesModel) {
        return new MoviesPresenter(moviesModel);
    }

    @Provides
    public MoviewMVP.Model providesMovieModel(Repository repository) {
        return new MoviesModel(repository);
    }

    @Singleton
    @Provides
    public Repository providesMovieRepository(MoviesApiService moviesApiService, MoviesExtraInfoApiService moviesExtraInfoApiService) {
        return new MoviesRepository(moviesApiService,moviesExtraInfoApiService);
    }

}
