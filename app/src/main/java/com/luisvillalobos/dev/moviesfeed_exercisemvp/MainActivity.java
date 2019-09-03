package com.luisvillalobos.dev.moviesfeed_exercisemvp;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.movies.ListAdapter;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.movies.MoviewMVP;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.movies.ViewModel;
import com.luisvillalobos.dev.moviesfeed_exercisemvp.root.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MoviewMVP.View {

    private final String TAG = MainActivity.class.getName();

    @BindView(R.id.recycler_view_movies)
    RecyclerView recyclerViewMovies;
    @BindView(R.id.activity_root_view)
    ViewGroup activityRootView;

    @Inject
    MoviewMVP.Presenter presenter;

    private ListAdapter listAdapter;
    private List<ViewModel> resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ((App) getApplication()).getComponent().inject(this);

        listAdapter = new ListAdapter(resultList);
        recyclerViewMovies.setAdapter(listAdapter);
        recyclerViewMovies.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerViewMovies.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMovies.setHasFixedSize(true);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxJavaUnsuscribe();
        resultList.clear();
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateData(ViewModel viewModel) {
        resultList.add(viewModel);
        listAdapter.notifyItemInserted(resultList.size() - 1);
        Log.d(TAG, "Informacion nueva" + viewModel.getName() + ":" + viewModel.getCountry());
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(activityRootView, message, Snackbar.LENGTH_SHORT);
    }
}
