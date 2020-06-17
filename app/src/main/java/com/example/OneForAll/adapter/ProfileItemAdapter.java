package com.example.OneForAll.adapter;

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

import com.example.OneForAll.R;
import com.example.OneForAll.data.model.ProductDataModel;
import com.example.OneForAll.view.activity.HomeCycleActivity;
import com.example.OneForAll.view.fragment.HomeCycle2.home.ProductsAndAddsDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.OneForAll.utils.HelperMethod.replaceFragmentWithAnimation;

public class ProfileItemAdapter extends RecyclerView.Adapter<ProfileItemAdapter.ViewHolder> {


    private Context context;
    private Activity activity;
    private List<ProductDataModel> itemList = new ArrayList<>();
//    private ClientData clientData;
//    private ApiService apiService;

    public ProfileItemAdapter(Context context,
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
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_profile_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
//        setAnimation(holder.itemView, position, holder);

    }

    @SuppressLint("ResourceAsColor")
    private void setData(ViewHolder holder, int position) {
        try {
            holder.position = position;
            holder.cardViewProfileItemTitleTv.setText(itemList.get(position).getName());
            holder.cardViewProfileItemCostTv.setText(itemList.get(position).getCost());
            holder.cardViewProfileItemPhoto.setImageResource(itemList.get(position).getPhoto());

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
        @BindView(R.id.card_view_profile_item_photo)
        ImageView cardViewProfileItemPhoto;
        @BindView(R.id.card_view_profile_item_title_tv)
        TextView cardViewProfileItemTitleTv;
        @BindView(R.id.card_view_profile_item_cost_tv)
        TextView cardViewProfileItemCostTv;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.card_view_profile_item_card_view_click)
        public void onViewClicked(View v) {

            HomeCycleActivity homeCycleActivity = (HomeCycleActivity) activity;
            Toast.makeText(v.getContext(), "Clicked Country Position = " + position, Toast.LENGTH_SHORT).show();
//                if(position==0){
            Bundle bundle = new Bundle();
            bundle.putString("ISMYPRODUCTDETAILS", "my");
            ProductsAndAddsDetailsFragment productsAndAddsDetailsFragment = new ProductsAndAddsDetailsFragment();
            productsAndAddsDetailsFragment.setArguments(bundle);
            replaceFragmentWithAnimation(homeCycleActivity.getSupportFragmentManager(), R.id.home_activity_fram, productsAndAddsDetailsFragment, "r");
//
        }
    }
}
