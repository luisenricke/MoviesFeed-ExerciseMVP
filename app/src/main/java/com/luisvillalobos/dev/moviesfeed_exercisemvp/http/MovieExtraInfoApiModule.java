package com.luisvillalobos.dev.moviesfeed_exercisemvp.http;

import com.luisvillalobos.dev.moviesfeed_exercisemvp.BuildConfig;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.http.apimodel.OpenMovieDb;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

@Module
public class MovieExtraInfoApiModule {

    public final String BASE_URL = "http://www.omdbapi.com/";
    public final String API_KEY = BuildConfig.openMoviesApiKey;

    @Provides
    public OkHttpClient providesClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("apikey", API_KEY).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        }).build();
    }

    @Provides
    public Retrofit providesRetrofit(String baseURL, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public MoviesExtraInfoApiService provideApiService() {
        return providesRetrofit(BASE_URL, providesClient()).create(MoviesExtraInfoApiService.class);
    }

}
