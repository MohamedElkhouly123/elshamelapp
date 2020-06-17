package com.example.OneForAll.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OneForAll.R;
import com.example.OneForAll.data.model.ItemObjectModel;

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

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_sub_category_item, null);
        CategoryRecyclerViewHolders rcv = new CategoryRecyclerViewHolders(layoutView, (Activity) context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewHolders holder, int position) {
        holder.countryName.setText(itemList.get(position).getName());

        holder.countryPhoto.setImageResource(itemList.get(position).getPhoto());
        setAnimation(holder.itemView, position);

    }
    private void setAnimation(View viewToAnimate, int position) {
        Animation animation = null;
        animation = AnimationUtils.loadAnimation(context, R.anim.rv_animation_down_to_up);
        viewToAnimate.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}