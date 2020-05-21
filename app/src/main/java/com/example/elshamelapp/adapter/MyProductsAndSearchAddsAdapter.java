package com.example.elshamelapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
import com.example.elshamelapp.data.model.ProductDataModel;
import com.example.elshamelapp.view.activity.HomeCycleActivity;
import com.example.elshamelapp.view.fragment.HomeCycle2.home.ProductsAndAddsDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragmentWithAnimation;

public class MyProductsAndSearchAddsAdapter extends RecyclerView.Adapter<MyProductsAndSearchAddsAdapter.ViewHolder> {


    private Context context;
    private Activity activity;
    private List<ProductDataModel> itemList = new ArrayList<>();
//    private ClientData clientData;
//    private ApiService apiService;

    public MyProductsAndSearchAddsAdapter(Context context,
                                          Activity activity,
                                          List<ProductDataModel> itemList
    ) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.itemList = itemList;
//        clientData = LoadUserData(activity);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_products_item_large,
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
            holder.cardViewProductsItemLargeNameTv.setText(itemList.get(position).getName());
            holder.cardViewProductsItemLargeCostTv.setText(itemList.get(position).getCost());
            holder.cardViewProductsItemLargePhoto.setImageResource(itemList.get(position).getPhoto());

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
               Bundle bundle=new Bundle();
                bundle.putString("ISMYPRODUCTDETAILS","my");
               ProductsAndAddsDetailsFragment productsAndAddsDetailsFragment=new ProductsAndAddsDetailsFragment();
                productsAndAddsDetailsFragment.setArguments(bundle);
                replaceFragmentWithAnimation(homeCycleActivity.getSupportFragmentManager(), R.id.home_activity_fram, productsAndAddsDetailsFragment, "r");
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
        @BindView(R.id.card_view_products_item_large_photo)
        ImageView cardViewProductsItemLargePhoto;
        @BindView(R.id.card_view_products_item_large_photo_is_favourite)
        ImageView cardViewProductsItemLargePhotoIsFavourite;
        @BindView(R.id.card_view_products_item_large_name_tv)
        TextView cardViewProductsItemLargeNameTv;
        @BindView(R.id.card_view_products_item_large_cost_tv)
        TextView cardViewProductsItemLargeCostTv;
        @OnClick(R.id.card_view_products_item_large_photo_is_favourite)
        public void onViewClicked() {
        }
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}