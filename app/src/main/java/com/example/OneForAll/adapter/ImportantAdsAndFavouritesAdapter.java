package com.example.OneForAll.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.OneForAll.R;
import com.example.OneForAll.data.model.ProductDataModel;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class ImportantAdsAndFavouritesAdapter extends RecyclerView.Adapter<ImportantAdsAndFavouritesAdapter.ViewHolder> {



    private Context context;
    private Activity activity;
    private List<ProductDataModel> importantItemList = new ArrayList<>();
    private boolean favourites;
    private List<ProductDataModel> rowListItem;

//    private ClientData clientData;
//    private ApiServices apiService;

    public ImportantAdsAndFavouritesAdapter(Context context, Activity activity,
                                            List<ProductDataModel> importantItemList,
                                            boolean favourites) {
        this.context = context;
        this.activity = activity;
        this.importantItemList = importantItemList;
        this.favourites = favourites;
//        clientData = LoadUserData(activity);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_important_ads_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        try {

//            onLoadImageFromUrl(holder.postsItemIvPostPhoto, importantItemList.get(position).getThumbnailFullPath(), context);
            holder.cardViewImportentAdsItemTvStatusOrName.setText(importantItemList.get(position).getName());

                if (position == getItemCount() - 1) {
                holder.cardViewImportentAdsItemPaddingTv.setVisibility(View.VISIBLE);
            } else {
                holder.cardViewImportentAdsItemPaddingTv.setVisibility(View.GONE);

            }
//            if (importantItemList.get(position).getIsFavourite()) {
//                holder.postsItemIvIsFavourite.setImageResource(R.drawable.ic_favorite_black_24dp);
//            } else {
//                holder.postsItemIvIsFavourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
//            }

            if (position==0) {
                holder.fragmentImportantAdsUltraViewpagerCardView.setVisibility(View.VISIBLE);
                // init all widgets in this activity
//        UltraViewPager fragmentImportantAdsUltraViewpager = (UltraViewPager)findViewById(R.id.ultra_viewpager);
                holder.fragmentImportantAdsUltraViewpager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
//initialize UltraPagerAdapter，and add child view to UltraViewPager
                PagerAdapter adapter = new UltraPagerAdapter(false);
                holder.fragmentImportantAdsUltraViewpager.setAdapter(adapter);

//initialize built-in indicator
                holder.fragmentImportantAdsUltraViewpager.initIndicator();
//set style of indicators
                holder.fragmentImportantAdsUltraViewpager.getIndicator()
                        .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                        .setFocusColor(Color.parseColor("#FC3D04"))
                        .setNormalColor(Color.WHITE)
                        .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, activity.getResources().getDisplayMetrics()));
//set the alignment
                holder.fragmentImportantAdsUltraViewpager.getIndicator().setGravity(Gravity.CENTER | Gravity.BOTTOM);
                holder.fragmentImportantAdsUltraViewpager.getIndicator().setMargin(0, 0, 0, 40);

//construct built-in indicator, and add it to  UltraViewPager
                holder.fragmentImportantAdsUltraViewpager.getIndicator().build();

//set an infinite loop
                holder.fragmentImportantAdsUltraViewpager.setInfiniteLoop(true);
//enable auto-scroll mode
                holder.fragmentImportantAdsUltraViewpager.setAutoScroll(4000);
            }else {
                holder.fragmentImportantAdsUltraViewpagerCardView.setVisibility(View.GONE);

            }

        } catch (Exception e) {

        }

    }

    private void setAction(final ViewHolder holder, final int position) {
        holder.cardViewImportentAdsItemPhotoIsFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavourite(holder, position);
            }
        });


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                HomeCycleActivity navigationActivity = (HomeCycleActivity) activity;
//                ArticleItemDetailsFragment postDetails = new ArticleItemDetailsFragment();
//                postDetails.postsData = importantItemList.get(position);
//                HelperMethod.replaceFragment(navigationActivity.getSupportFragmentManager(), R.id.user_activity_fram, postDetails);

            }
        });

    }

    private void toggleFavourite(final ViewHolder holder, final int position) {
//        final PostsData postsData = importantItemList.get(position);
//
//        importantItemList.get(position).setIsFavourite(!importantItemList.get(position).getIsFavourite());
//
//        if (importantItemList.get(position).getIsFavourite()) {
//            holder.postsItemIvIsFavourite.setImageResource(R.drawable.ic_favorite_black_24dp);
//            ToastCreator.onCreateSuccessToast(activity, context.getResources().getString(R.string.add_to_favourite));
//
//
//        } else {
//            holder.postsItemIvIsFavourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
//
//            ToastCreator.onCreateSuccessToast(activity, context.getResources().getString(R.string.remove_from_favourite));
//            if (favourites) {
//                importantItemList.remove(position);
//                notifyDataSetChanged();
////                if (importantItemList.size() == 0) {
////                    articlesFragmentTvNoItems.setVisibility(View.VISIBLE);
////                }
//            }
//        }
//        Call<PostToggleFavourite> call = getApiClient().getPostToggleFavourite(postsData.getId(), clientData.getApiToken());
//        call.enqueue(new Callback<PostToggleFavourite>() {
//            @Override
//            public void onResponse(Call<PostToggleFavourite> call, Response<PostToggleFavourite> response) {
//                try {
//                    if (response.body().getStatus() == 1) {
//
//                    } else {
//                        importantItemList.get(position).setIsFavourite(!importantItemList.get(position).getIsFavourite());
//                        if (importantItemList.get(position).getIsFavourite()) {
//                            holder.postsItemIvIsFavourite.setImageResource(R.drawable.ic_favorite_black_24dp);
//                            if (favourites) {
//                                importantItemList.add(position, postsData);
//                                notifyDataSetChanged();
//                            }
//                        } else {
//                            holder.postsItemIvIsFavourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
//                        }
//                    }
//
//                } catch (Exception e) {
//
//                }

//            }
//
//            @Override
//            public void onFailure(Call<PostToggleFavourite> call, Throwable t) {
//                try {
//                    importantItemList.get(position).setIsFavourite(!importantItemList.get(position).getIsFavourite());
//                    if (importantItemList.get(position).getIsFavourite()) {
//                        holder.postsItemIvIsFavourite.setImageResource(R.drawable.ic_favorite_black_24dp);
//                        if (favourites) {
//                            importantItemList.add(position, postsData);
//                            notifyDataSetChanged();
//                        }
//                    } else {
//                        holder.postsItemIvIsFavourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
//                    }
//                } catch (Exception e) {
//
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return importantItemList.size();
    }

    @OnClick({R.id.card_view_importent_ads_item_like_btn, R.id.card_view_importent_ads_item_chat_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_view_importent_ads_item_like_btn:
                break;
            case R.id.card_view_importent_ads_item_chat_btn:
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view_importent_ads_item_imgView_proPic)
        CircleImageView cardViewImportentAdsItemImgViewProPic;
//        @BindView(R.id.card_view_importent_ads_item_imgView_proPic_cv)
//        CardView cardViewImportentAdsItemImgViewProPicCv;
        @BindView(R.id.card_view_importent_ads_item_tv_name)
        TextView cardViewImportentAdsItemTvName;
        @BindView(R.id.card_view_importent_ads_item_tv_time)
        TextView cardViewImportentAdsItemTvTime;
        @BindView(R.id.card_view_importent_ads_item_rellay1)
        RelativeLayout cardViewImportentAdsItemRellay1;
        @BindView(R.id.card_view_importent_ads_item_tv_status_or_name)
        TextView cardViewImportentAdsItemTvStatusOrName;
        @BindView(R.id.card_view_importent_ads_item_cost_tv)
        TextView cardViewImportentAdsItemCostTv;
        @BindView(R.id.card_view_importent_ads_item_imgView_postPic)
        ImageView cardViewImportentAdsItemImgViewPostPic;
        @BindView(R.id.card_view_importent_ads_item_photo_is_favourite)
        ImageView cardViewImportentAdsItemPhotoIsFavourite;
        @BindView(R.id.card_view_importent_ads_item_imgView_postPic_frm)
        FrameLayout cardViewImportentAdsItemImgViewPostPicFrm;
        @BindView(R.id.card_view_importent_ads_item_imgView_line)
        ImageView cardViewImportentAdsItemImgViewLine;
        @BindView(R.id.card_view_importent_ads_item_like_btn)
        ImageView cardViewImportentAdsItemLikeBtn;
        @BindView(R.id.card_view_importent_ads_item_chat_btn)
        ImageView cardViewImportentAdsItemChatBtn;
        @BindView(R.id.card_view_importent_ads_item_padding_tv)
        TextView cardViewImportentAdsItemPaddingTv;
        @BindView(R.id.fragment_important_ads_ultra_viewpager)
        UltraViewPager fragmentImportantAdsUltraViewpager;
        @BindView(R.id.fragment_important_ads_ultra_viewpager_card_view)
        CardView fragmentImportantAdsUltraViewpagerCardView;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
            cardViewImportentAdsItemImgViewProPic.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        }
    }
}
