package com.example.OneForAll.adapter;

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
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.PagerAdapter;

import com.example.OneForAll.R;
import com.example.OneForAll.data.model.ItemObjectModel;
import com.example.OneForAll.data.model.ProductDataModel;
import com.example.OneForAll.view.activity.HomeCycleActivity;
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
    private SubHomeCategoryHzRvItem2Adapter subHomeCategoryHzRvItem2Adapter;
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
        if(viewType==0){
         view = LayoutInflater.from(context).inflate(R.layout.home_sub_ver_rv_item,
                parent, false);}
        else if(viewType ==1){
            view = LayoutInflater.from(context).inflate(R.layout.home_sub_ver_rv_item2,
                    parent, false);
        }
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
            final int itemType = getItemViewType(position);
            holder.position = position;
            rowListItem = getAllItemList();
            if (itemType == 1) {
//            ((MyNormalViewHolder)holder).bindData((MyModel)myData[position]);

                holder.subHomeCategoryHzRvItem2CategoryNameTv.setText(itemList.get(position).getName());
                lLayout = new LinearLayoutManager(activity);
                holder.subHomeCategory2HzRvRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

                subHomeCategoryHzRvItem2Adapter = new SubHomeCategoryHzRvItem2Adapter(context, activity, rowListItem);
                holder.subHomeCategory2HzRvRecyclerView.setAdapter(subHomeCategoryHzRvItem2Adapter);

                // 5. set item animator to DefaultAnimator
                holder.subHomeCategory2HzRvRecyclerView.setItemAnimator(new DefaultItemAnimator());

                if( position == getItemCount() - 1 ){
                    holder.subHomeCategoryHzRvItem2PaddingTv.setVisibility(View.VISIBLE);
                }else {
                    holder.subHomeCategoryHzRvItem2PaddingTv.setVisibility(View.GONE);

                }
            } else if (itemType == 0) {
//            ((MyHeaderViewHolder)holder).setHeaderText((String)myData[position]);
                holder.subHomeCategoryHzRvItemCategoryNameTv.setText(itemList.get(position).getName());
                lLayout = new LinearLayoutManager(activity);
                holder.subHomeCategoryHzRvRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

                homeSubHzItemAdapter = new ProfileItemAdapter(context, activity, rowListItem);
                holder.subHomeCategoryHzRvRecyclerView.setAdapter(homeSubHzItemAdapter);

                // 5. set item animator to DefaultAnimator
                holder.subHomeCategoryHzRvRecyclerView.setItemAnimator(new DefaultItemAnimator());

                if( position == getItemCount() - 1 ){
                    holder.subHomeCategoryHzRvItemPaddingTv.setVisibility(View.VISIBLE);
                }else {
                    holder.subHomeCategoryHzRvItemPaddingTv.setVisibility(View.GONE);

                }

            }



            if (position==0) {
                holder.subHomeCategory2HzRvUltraViewpagerCardView.setVisibility(View.VISIBLE);
                // init all widgets in this activity
//        UltraViewPager fragmentImportantAdsUltraViewpager = (UltraViewPager)findViewById(R.id.ultra_viewpager);
                holder.subHomeCategory2HzRvUltraViewpager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
//initialize UltraPagerAdapter，and add child view to UltraViewPager
                PagerAdapter adapter = new UltraPagerAdapter(false);
                holder.subHomeCategory2HzRvUltraViewpager.setAdapter(adapter);

//initialize built-in indicator
                holder.subHomeCategory2HzRvUltraViewpager.initIndicator();
//set style of indicators
                holder.subHomeCategory2HzRvUltraViewpager.getIndicator()
                        .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                        .setFocusColor(Color.parseColor("#FC3D04"))
                        .setNormalColor(Color.WHITE)
                        .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, activity.getResources().getDisplayMetrics()));
//set the alignment
                holder.subHomeCategory2HzRvUltraViewpager.getIndicator().setGravity(Gravity.CENTER | Gravity.BOTTOM);
                holder.subHomeCategory2HzRvUltraViewpager.getIndicator().setMargin(0, 0, 0, 40);

//construct built-in indicator, and add it to  UltraViewPager
                holder.subHomeCategory2HzRvUltraViewpager.getIndicator().build();

//set an infinite loop
                holder.subHomeCategory2HzRvUltraViewpager.setInfiniteLoop(true);
//enable auto-scroll mode
                holder.subHomeCategory2HzRvUltraViewpager.setAutoScroll(4000);
            }else {
                holder.subHomeCategory2HzRvUltraViewpagerCardView.setVisibility(View.GONE);

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
        if(position % 2 == 0&&position!=0){
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
        @Nullable
        @BindView(R.id.sub_home_verv_item_category_name_tv)
        TextView subHomeCategoryHzRvItemCategoryNameTv;
        @Nullable
        @BindView(R.id.sub_home_verv_item__viewmore1_tv)
        TextView SubHomeCategoryHzRvItemSeeMoreTv;
        @Nullable
        @BindView(R.id.sub_home_verv_item_padding_tv)
        TextView subHomeCategoryHzRvItemPaddingTv;
        @Nullable
        @BindView(R.id.sub_home_verv_item__hz_rv)
        RecyclerView subHomeCategoryHzRvRecyclerView;
        @Nullable
        @BindView(R.id.sub_home_verv_item2_category_name_tv)
        TextView subHomeCategoryHzRvItem2CategoryNameTv;
        @Nullable
        @BindView(R.id.sub_home_verv_item2__viewmore1_tv)
        TextView SubHomeCategoryHzRvItem2SeeMoreTv;
        @Nullable
        @BindView(R.id.sub_home_verv_item2_padding_tv)
        TextView subHomeCategoryHzRvItem2PaddingTv;
        @Nullable
        @BindView(R.id.sub_home_verv_item2_ultra_viewpager)
        UltraViewPager subHomeCategory2HzRvUltraViewpager;
        @Nullable
        @BindView(R.id.sub_home_verv_item2_ultra_viewpager_card_view)
        CardView subHomeCategory2HzRvUltraViewpagerCardView;
        @Nullable
        @BindView(R.id.sub_home_verv_item2__hz_rv)
        RecyclerView subHomeCategory2HzRvRecyclerView;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
