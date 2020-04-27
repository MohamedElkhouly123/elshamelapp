package com.example.elshamelapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elshamelapp.R;
import com.example.elshamelapp.data.model.ItemObjectModel;
import com.example.elshamelapp.view.activity.HomeCycleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Category2ThAdapter extends RecyclerView.Adapter<Category2ThAdapter.ViewHolder> {



    private Context context;
    private Activity activity;
    private List<ItemObjectModel> itemList = new ArrayList<>();
//    private ClientData clientData;
//    private ApiService apiService;

    public Category2ThAdapter(Context context,
                              Activity activity,
                              List<ItemObjectModel> itemList
    ) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.itemList = itemList;
//        clientData = LoadUserData(activity);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_category_2th_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
        setAnimation(holder.itemView, position, holder);

    }

    @SuppressLint("ResourceAsColor")
    private void setData(ViewHolder holder, int position) {
        try {
            holder.position = position;
            holder.subCategory2th.setText(itemList.get(position).getName());
//            holder.subCategory2th.setBackgroundColor(R.drawable.blue_style);
//            holder.thumbnail.setBackgroundColor(R.color.photo_placeholder);

        } catch (Exception e) {

        }

    }

    private void setAnimation(View viewToAnimate, int position, ViewHolder holder) {
        Animation animation = null;
        animation = AnimationUtils.loadAnimation(activity, R.anim.rv_animation_down_to_up);
        viewToAnimate.startAnimation(animation);

    }

    private void setAction(final ViewHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeCycleActivity homeCycleActivity = (HomeCycleActivity) activity;
                Toast.makeText(v.getContext(), "Clicked Country Position = " + position, Toast.LENGTH_SHORT).show();
//                if(position==0){
//                replaceFragmentWithAnimation(homeCycleActivity.getSupportFragmentManager(), R.id.home_activity_fram, new SubCategoryFragment(), "t");
//                homeCycleActivity.setNavigationAndToolBar(View.GONE,"t");
//                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

//    @OnClick(R.id.item_client_restaurants_list_rb_rating)
//    public void onViewClicked() {
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sub_category_2th)
        TextView subCategory2th;
        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
