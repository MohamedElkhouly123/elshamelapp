package com.example.OneForAll.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OneForAll.R;
import com.example.OneForAll.data.model.ItemObjectModel;
import com.example.OneForAll.view.activity.HomeCycleActivity;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategorySub2Adapter extends RecyclerView.Adapter<CategorySub2Adapter.ViewHolder> {



    private Context context;
    private Activity activity;
    private List<ItemObjectModel> itemList = new ArrayList<>();
    private final List<UnifiedNativeAd> mNativeAds;

//    private ClientData clientData;
//    private ApiService apiService;

    public CategorySub2Adapter(Context context,
                               Activity activity,
                               List<ItemObjectModel> itemList,
                               List<UnifiedNativeAd> mNativeAds) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.itemList = itemList;
        this.mNativeAds = mNativeAds;
//        clientData = LoadUserData(activity);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
//            googAds
            View googAdsView = LayoutInflater.from(context).inflate(R.layout.card_view_medium_google_ad_unified_item,
                    parent, false);
            return new ViewHolder(googAdsView, "");
        } else if (viewType == 1) {
          View  view = LayoutInflater.from(context).inflate(R.layout.cardview_category_2th_blue_item,
                    parent, false);
            return new ViewHolder(view);
        }
        return null;
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
            int i = 0;
            int viewType = getItemViewType(position);
            if (viewType == 0) {

                UnifiedNativeAd nativeAd = (UnifiedNativeAd) mNativeAds.get(i);
                populateNativeAdView(nativeAd, holder);
                i++;

            } else if (viewType == 1){
                holder.subCategory2thName.setText(itemList.get(position).getName());
                holder.thumbnailPhoto.setImageResource(itemList.get(position).getPhoto());


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
//                    replaceFragmentWithAnimation(homeCycleActivity.getSupportFragmentManager(), R.id.home_activity_fram, new SubCategoryFragment(), "t");
//                    homeCycleActivity.setNavigationAndToolBar(View.GONE, true);
                }
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == getItemCount() - 1) {
            return 0;
        }
        return 1;
//        Object recyclerViewItem = mRecyclerViewItems.get(position);
//        if (recyclerViewItem instanceof UnifiedNativeAd) {
//            return UNIFIED_NATIVE_AD_VIEW_TYPE;
//        }
//        return ITEM_VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

//    @OnClick(R.id.item_client_restaurants_list_rb_rating)
//    public void onViewClicked() {
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.sub_category_2th_name)
        TextView subCategory2thName;
        @Nullable
        @BindView(R.id.thumbnail_photo)
        ImageView thumbnailPhoto;
        private View view;
        private int position;
        private UnifiedNativeAdView adView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }

        public ViewHolder(View itemView, String s) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
            MobileAds.initialize(activity, String.valueOf(R.string.admob_id));
            adView = (UnifiedNativeAdView) view.findViewById(R.id.card_view_item_ad_view);

            // The MediaView will display a video asset if one is present in the ad, and the
            // first image asset otherwise.
            adView.setMediaView((MediaView) view.findViewById(R.id.card_view_item_ad_media));

            // Register the view used for each individual asset.
            adView.setHeadlineView(view.findViewById(R.id.card_view_item_ad_headline));
            adView.setBodyView(view.findViewById(R.id.card_view_item_ad_body));
            adView.setCallToActionView(view.findViewById(R.id.card_view_item_ad_call_to_action));
            adView.setIconView(view.findViewById(R.id.card_view_item_ad_icon));
            adView.setPriceView(view.findViewById(R.id.card_view_item_ad_price));
            adView.setStarRatingView(view.findViewById(R.id.card_view_item_ad_stars));
            adView.setStoreView(view.findViewById(R.id.card_view_item_ad_store));
            adView.setAdvertiserView(view.findViewById(R.id.card_view_item_ad_advertiser));
        }
    }
    private void populateNativeAdView(UnifiedNativeAd nativeAd, ViewHolder holder) {
        // Some assets are guaranteed to be in every UnifiedNativeAd.
        ((TextView) holder.adView.getHeadlineView()).setText(nativeAd.getHeadline());
        ((TextView) holder.adView.getBodyView()).setText(nativeAd.getBody());
        ((Button) holder.adView.getCallToActionView()).setText(nativeAd.getCallToAction());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        NativeAd.Image icon = nativeAd.getIcon();

        if (icon == null) {
            holder.adView.getIconView().setVisibility(View.INVISIBLE);
        } else {
            ((ImageView) holder.adView.getIconView()).setImageDrawable(icon.getDrawable());
            holder.adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            holder.adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            holder.adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) holder.adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            holder.adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            holder.adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) holder.adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            holder.adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) holder.adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            holder.adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            holder.adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) holder.adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            holder.adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // Assign native ad object to the native view.
        holder.adView.setNativeAd(nativeAd);

    }
}