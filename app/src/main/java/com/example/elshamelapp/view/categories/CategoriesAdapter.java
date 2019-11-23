package com.example.elshamelapp.view.categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elshamelapp.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoryRecyclerViewHolders> {

    private List<ItemObjectModel> itemList;
    private Context context;

    public CategoriesAdapter(Context context, List<ItemObjectModel> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public CategoryRecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_category_item, null);
        CategoryRecyclerViewHolders rcv = new CategoryRecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewHolders holder, int position) {
        holder.countryName.setText(itemList.get(position).getName());
        holder.countryPhoto.setImageResource(itemList.get(position).getPhoto());
    }


    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}