package com.example.OneForAll.adapter;

import android.annotation.SuppressLint;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.OneForAll.R;
import com.example.OneForAll.data.model.ItemObjectModel;
import com.example.OneForAll.view.activity.HomeCycleActivity;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Category2ThAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Context context;
    private Activity activity;
//    private List<ItemObjectModel> itemList = new ArrayList<>();
    // A menu item view type.
//    private static final int ITEM_VIEW_TYPE = 0;
//
//    private static final int UNIFIED_NATIVE_AD_VIEW_TYPE = 1;

    // The list of Native ads and menu items.
    private final List<Object> mRecyclerViewItems ;
//    private ClientData clientData;
//    private ApiService apiService;

    public Category2ThAdapter(Context context,
                              Activity activity,
                              List<Object> mRecyclerViewItems
    ) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.mRecyclerViewItems = mRecyclerViewItems;
//        showToast(activity, String.valueOf(mRecyclerViewItems.get(getItemCount())));

//        clientData = LoadUserData(activity);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.cardview_category_2th_item,
//                parent, false);
//
//        return new ViewHolder(view);

//        switch (viewType) {
//            case UNIFIED_NATIVE_AD_VIEW_TYPE:
        if(viewType==0){

            View unifiedNativeLayoutView = LayoutInflater.from(
                        context).inflate(R.layout.card_view_google_ad_unified_item,
                        parent, false);
                return new GoogleAdsViewHolder2("",unifiedNativeLayoutView);}
        else if(viewType ==1){

//            case ITEM_VIEW_TYPE:
                // Fall through.
//                View menuItemLayoutView2 = LayoutInflater.from(context).inflate(
//                        R.layout.cardview_category_2th_item, parent, false);
//                return new ViewHolder( menuItemLayoutView2);
//            default:
                View menuItemLayoutView = LayoutInflater.from(context).inflate(
                        R.layout.cardview_category_2th_item, parent, false);

                return new Category2ThAdapter.ViewHolder1( menuItemLayoutView);}
        return null;
//    }
}

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        setAnimation(holder.itemView, position);
        int viewType = getItemViewType(position);
        if(viewType==0) {

            UnifiedNativeAd nativeAd = (UnifiedNativeAd) mRecyclerViewItems.get(position);
            populateNativeAdView(nativeAd, ((ViewHolder1) holder).getAdView());
        } else if(viewType ==1){
//                setData(holder, position);
//                setAction(holder, position);
                // fall through
//                ViewHolder menuItemHolder = (ViewHolder) holder;
//                ItemObjectModel menuItem = (ItemObjectModel) mRecyclerViewItems.get(position);
                setData((ViewHolder1) holder, position);
                setAction((ViewHolder1) holder, position);
                // Get the menu item image resource ID.
//                String imageName = menuItem.getImageName();
//                int imageResID = mContext.getResources().getIdentifier(imageName, "drawable",
//                        mContext.getPackageName());
//
//                // Add the menu item details to the menu item view.
//                menuItemHolder.menuItemImage.setImageResource(imageResID);
//                menuItemHolder.menuItemName.setText(menuItem.getName());
//                menuItemHolder.menuItemPrice.setText(menuItem.getPrice());
//                menuItemHolder.menuItemCategory.setText(menuItem.getCategory());
//                menuItemHolder.menuItemDescription.setText(menuItem.getDescription());
        }
    }



    @SuppressLint("ResourceAsColor")
    private void setData(Category2ThAdapter.ViewHolder1 holder, int position) {
        try {
            holder.position = position;
            ItemObjectModel itemList = (ItemObjectModel) mRecyclerViewItems.get(position);
            holder.subCategory2th.setText(itemList.getName());
//            holder.subCategory2th.setBackgroundColor(R.drawable.blue_style);
//            holder.thumbnail.setBackgroundColor(R.color.photo_placeholder);

        } catch (Exception e) {

        }

    }

    private void setAnimation(View viewToAnimate, int position) {
        Animation animation = null;
        animation = AnimationUtils.loadAnimation(activity, R.anim.rv_animation_down_to_up);
        viewToAnimate.startAnimation(animation);

    }

    private void setAction(final Category2ThAdapter.ViewHolder1 holder, final int position) {

        holder.view1.setOnClickListener(new View.OnClickListener() {
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
        return mRecyclerViewItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 4 == 0){
            return 0;
        }
        return 1;
//        Object recyclerViewItem = mRecyclerViewItems.get(position);
//        if (recyclerViewItem instanceof UnifiedNativeAd) {
//            return UNIFIED_NATIVE_AD_VIEW_TYPE;
//        }
//        return ITEM_VIEW_TYPE;
    }

//    @OnClick(R.id.item_client_restaurants_list_rb_rating)
//    public void onViewClicked() {
//    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.sub_category_2th_name)
        TextView subCategory2th;
        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        private View view1;
        private int position;
        private UnifiedNativeAdView adView;

        public UnifiedNativeAdView getAdView() {
            return adView;
        }
        public ViewHolder1(View itemView) {
            super(itemView);
            view1 = itemView;
            ButterKnife.bind(this, view1);

        }

        }




    private void populateNativeAdView(UnifiedNativeAd nativeAd,
                                      UnifiedNativeAdView adView) {
        // Some assets are guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        NativeAd.Image icon = nativeAd.getIcon();

        if (icon == null) {
            adView.getIconView().setVisibility(View.INVISIBLE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(icon.getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // Assign native ad object to the native view.
        adView.setNativeAd(nativeAd);
    }

}
