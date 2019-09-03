package com.luisvillalobos.dev.moviesfeed_exercisemvp.root;

import android.app.Application;

import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.MovieExtraInfoApiModule;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.MovieTitleApiModule;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.movies.MoviesModule;

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .moviesModule(new MoviesModule())
                .movieTitleApiModule(new MovieTitleApiModule())
                .movieExtraInfoApiModule(new MovieExtraInfoApiModule())
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
