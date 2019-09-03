package com.luisvillalobos.dev.moviesfeed_exercisemvp.movies;


import io.reactivex.Observable;


public interface MoviewMVP {
    interface View {
        void updateData(ViewModel viewModel);

        void showSnackBar(String message);
    }

    interface Model {

        Observable<ViewModel> result();

    }

    interface Presenter {
        void loadData();

        void rxJavaUnsuscribe();

        void setView(MoviewMVP.View view);
    }
}
