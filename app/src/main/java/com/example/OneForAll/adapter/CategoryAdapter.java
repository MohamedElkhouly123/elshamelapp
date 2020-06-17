package com.example.OneForAll.adapter;

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

import com.example.OneForAll.R;
import com.example.OneForAll.data.model.ItemObjectModel;
import com.example.OneForAll.view.activity.HomeCycleActivity;
import com.example.OneForAll.view.fragment.HomeCycle2.category.SubCategoryFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.OneForAll.utils.HelperMethod.replaceFragmentWithAnimation;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {



    private Context context;
    private Activity activity;
    private List<ItemObjectModel> itemList = new ArrayList<>();
//    private ClientData clientData;
//    private ApiService apiService;

    public CategoryAdapter(Context context,
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
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_category_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
        setAnimation(holder.itemView, position, holder);

    }

    private void setData(ViewHolder holder, int position) {
        try {
            holder.position = position;
            holder.countryName.setText(itemList.get(position).getName());
            holder.countryPhoto.setImageResource(itemList.get(position).getPhoto());
            if( position == getItemCount() - 1 ){
                holder.cardviewCategoryItemPaddingTv.setVisibility(View.VISIBLE);
            }else {
                holder.cardviewCategoryItemPaddingTv.setVisibility(View.GONE);

            }
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
                if (position == 0) {
                    replaceFragmentWithAnimation(homeCycleActivity.getSupportFragmentManager(), R.id.home_activity_fram, new SubCategoryFragment(), "t");
                    homeCycleActivity.setNavigationAndToolBar(View.GONE, true);
                }
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
        @BindView(R.id.country_photo)
        ImageView countryPhoto;
        @BindView(R.id.country_name)
        TextView countryName;
        @BindView(R.id.cardview_category_item_padding_tv)
        TextView cardviewCategoryItemPaddingTv;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
