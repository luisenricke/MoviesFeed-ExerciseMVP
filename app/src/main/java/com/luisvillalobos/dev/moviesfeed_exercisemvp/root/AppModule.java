package com.luisvillalobos.dev.moviesfeed_exercisemvp.root;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;

    public AppModule(Application app){
        this.application = app;
    }

    @Provides
    @Singleton
    public Context providesContext(){
        return application;
    }

}
