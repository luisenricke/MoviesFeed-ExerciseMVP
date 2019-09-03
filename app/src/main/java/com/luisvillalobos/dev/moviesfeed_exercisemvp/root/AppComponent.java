package com.luisvillalobos.dev.moviesfeed_exercisemvp.root;

import com.luisvillalobos.dev.moviesfeed_exercisemvp.MainActivity;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.MovieExtraInfoApiModule;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.MovieTitleApiModule;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.movies.MoviesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, MoviesModule.class, MovieTitleApiModule.class, MovieExtraInfoApiModule.class})
public interface AppComponent {

    void inject(MainActivity target);
}
