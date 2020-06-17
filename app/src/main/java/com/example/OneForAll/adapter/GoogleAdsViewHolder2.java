package com.example.OneForAll.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.OneForAll.R;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

public class GoogleAdsViewHolder2 extends RecyclerView.ViewHolder {
    private View view;
    private int position;
    private UnifiedNativeAdView adView;

    public UnifiedNativeAdView getAdView() {
        return adView;
    }

    public GoogleAdsViewHolder2(String S, View googleadsView) {
        super(googleadsView);
        view = googleadsView;
//            ButterKnife.bind(this, view);
        adView = (UnifiedNativeAdView) view.findViewById(R.id.card_view_item_ad_view);

        // The MediaView will display a video asset if one is present in the ad, and the
        // first image asset otherwise.
        adView.setMediaView((MediaView) googleadsView.findViewById(R.id.card_view_item_ad_media));

        // Register the view used for each individual asset.
        adView.setHeadlineView(googleadsView.findViewById(R.id.card_view_item_ad_headline));
        adView.setBodyView(googleadsView.findViewById(R.id.card_view_item_ad_body));
        adView.setCallToActionView(googleadsView.findViewById(R.id.card_view_item_ad_call_to_action));
        adView.setIconView(googleadsView.findViewById(R.id.card_view_item_ad_icon));
        adView.setPriceView(googleadsView.findViewById(R.id.card_view_item_ad_price));
        adView.setStarRatingView(googleadsView.findViewById(R.id.card_view_item_ad_stars));
        adView.setStoreView(googleadsView.findViewById(R.id.card_view_item_ad_store));
        adView.setAdvertiserView(googleadsView.findViewById(R.id.card_view_item_ad_advertiser));
    }

}
