package com.example.elshamelapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.PagerAdapter;

import com.example.elshamelapp.R;
import com.example.elshamelapp.data.model.ItemObjectModel;
import com.example.elshamelapp.data.model.ProductDataModel;
import com.example.elshamelapp.view.activity.HomeCycleActivity;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubHomeCategoryHzRvAdapter extends RecyclerView.Adapter<SubHomeCategoryHzRvAdapter.ViewHolder> {



    private Context context;
    private Activity activity;
    private List<ItemObjectModel> itemList = new ArrayList<>();
    private LinearLayoutManager lLayout;
    private ProfileItemAdapter homeSubHzItemAdapter;
    List<ProductDataModel> rowListItem;
//    private ClientData clientData;
//    private ApiService apiService;

    public SubHomeCategoryHzRvAdapter(Context context,
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
        View view = null;
//        if(viewType==0){
//         view = LayoutInflater.from(context).inflate(R.layout.home_sub_ver_rv_item,
//                parent, false);}
//        else if(viewType ==1){
//            view = LayoutInflater.from(context).inflate(R.layout.card_view_important_ads_item,
//                    parent, false);
//        }
        view = LayoutInflater.from(context).inflate(R.layout.home_sub_ver_rv_item,
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
//            final int itemType = getItemViewType(position);
//            if (itemType == 0) {
//            ((MyNormalViewHolder)holder).bindData((MyModel)myData[position]);
//            } else if (itemType == 1) {
//            ((MyHeaderViewHolder)holder).setHeaderText((String)myData[position]);
                holder.position = position;
                holder.subHomeCategoryHzRvItemCategoryNameTv.setText(itemList.get(position).getName());
                rowListItem = getAllItemList();
                lLayout = new LinearLayoutManager(activity);

                holder.subHomeCategoryHzRvRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

                homeSubHzItemAdapter = new ProfileItemAdapter(context, activity, rowListItem);
                holder.subHomeCategoryHzRvRecyclerView.setAdapter(homeSubHzItemAdapter);

                // 5. set item animator to DefaultAnimator
                holder.subHomeCategoryHzRvRecyclerView.setItemAnimator(new DefaultItemAnimator());

//            }


            if( position == getItemCount() - 1 ){
                holder.subHomeCategoryHzRvItemPaddingTv.setVisibility(View.VISIBLE);
            }else {
                holder.subHomeCategoryHzRvItemPaddingTv.setVisibility(View.GONE);

            }
            if (position==0) {
                holder.subHomeCategoryHzRvUltraViewpagerCardView.setVisibility(View.VISIBLE);
                // init all widgets in this activity
//        UltraViewPager fragmentImportantAdsUltraViewpager = (UltraViewPager)findViewById(R.id.ultra_viewpager);
                holder.subHomeCategoryHzRvUltraViewpager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
//initialize UltraPagerAdapter，and add child view to UltraViewPager
                PagerAdapter adapter = new UltraPagerAdapter(false);
                holder.subHomeCategoryHzRvUltraViewpager.setAdapter(adapter);

//initialize built-in indicator
                holder.subHomeCategoryHzRvUltraViewpager.initIndicator();
//set style of indicators
                holder.subHomeCategoryHzRvUltraViewpager.getIndicator()
                        .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                        .setFocusColor(Color.parseColor("#FC3D04"))
                        .setNormalColor(Color.WHITE)
                        .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, activity.getResources().getDisplayMetrics()));
//set the alignment
                holder.subHomeCategoryHzRvUltraViewpager.getIndicator().setGravity(Gravity.CENTER | Gravity.BOTTOM);
                holder.subHomeCategoryHzRvUltraViewpager.getIndicator().setMargin(0, 0, 0, 40);

//construct built-in indicator, and add it to  UltraViewPager
                holder.subHomeCategoryHzRvUltraViewpager.getIndicator().build();

//set an infinite loop
                holder.subHomeCategoryHzRvUltraViewpager.setInfiniteLoop(true);
//enable auto-scroll mode
                holder.subHomeCategoryHzRvUltraViewpager.setAutoScroll(4000);
            }else {
                holder.subHomeCategoryHzRvUltraViewpagerCardView.setVisibility(View.GONE);

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
//                Toast.makeText(v.getContext(), "Clicked Country Position = " + position, Toast.LENGTH_SHORT).show();
//                if (position == 0) {
//                    replaceFragmentWithAnimation(homeCycleActivity.getSupportFragmentManager(), R.id.home_activity_fram, new SubCategoryFragment(), "t");
//                    homeCycleActivity.setNavigationAndToolBar(View.GONE, true);
//                }
            }
        });

    }

    private List<ProductDataModel> getAllItemList() {

        rowListItem = new ArrayList<ProductDataModel>();
        rowListItem.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        rowListItem.add(new ProductDataModel("سكر", "55 ج", R.drawable.chale));
        rowListItem.add(new ProductDataModel("سكر", "55 ج", R.drawable.chale));
        rowListItem.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        rowListItem.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        rowListItem.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        rowListItem.add(new ProductDataModel("سكر", "55 ج", R.drawable.chale));


        return rowListItem;
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 3 == 0){
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @OnClick(R.id.sub_home_verv_item__viewmore1_tv)
    public void onViewClicked() {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sub_home_verv_item_category_name_tv)
        TextView subHomeCategoryHzRvItemCategoryNameTv;
        @BindView(R.id.sub_home_verv_item__viewmore1_tv)
        TextView SubHomeCategoryHzRvItemSeeMoreTv;
        @BindView(R.id.sub_home_verv_item_padding_tv)
        TextView subHomeCategoryHzRvItemPaddingTv;
        @BindView(R.id.sub_home_verv_item_ultra_viewpager)
        UltraViewPager subHomeCategoryHzRvUltraViewpager;
        @BindView(R.id.sub_home_verv_item_ultra_viewpager_card_view)
        CardView subHomeCategoryHzRvUltraViewpagerCardView;
        @BindView(R.id.sub_home_verv_item__hz_rv)
        RecyclerView subHomeCategoryHzRvRecyclerView;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
