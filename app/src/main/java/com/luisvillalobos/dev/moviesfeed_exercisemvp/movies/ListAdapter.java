package com.luisvillalobos.dev.moviesfeed_exercisemvp.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luisvillalobos.dev.moviesfeed_exercisemvp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemViewHolder> {

    private List<ViewModel> list;

    public ListAdapter(List<ViewModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        holder.itemName.setText(list.get(position).getName());
        holder.countryName.setText(list.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view_fragment_title)
        public TextView itemName;
        @BindView(R.id.text_view_fragment_country)
        public TextView countryName;

        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
