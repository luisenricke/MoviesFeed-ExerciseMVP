package com.luisvillalobos.dev.moviesfeed_exercisemvp.movies;

public class ViewModel {
    private String name;
    private String country;

    public ViewModel() {
    }

    public ViewModel(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
