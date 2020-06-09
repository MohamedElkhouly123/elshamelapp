package com.example.elshamelapp.view.fragment.HomeCycle2.category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.elshamelapp.R;
import com.example.elshamelapp.adapter.Category2ThAdapter;
import com.example.elshamelapp.data.model.ItemObjectModel;
import com.example.elshamelapp.view.activity.HomeCycleActivity;
import com.example.elshamelapp.view.fragment.BaSeFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;

public class SubCategory2ThFragment extends BaSeFragment {

    private LinearLayoutManager lLayout;
    @BindView(R.id.sub_category2_fragment_recycler_view)
    RecyclerView rView;
    // The number of native ads to load.
    public static final int NUMBER_OF_ADS = 3;
    private List<ItemObjectModel> rowListItem;
    // The AdLoader used to load ads.
    private AdLoader adLoader;
    // List of MenuItems and native ads that populate the RecyclerView.
    private List<Object> mRecyclerViewItems = new ArrayList<>();

    // List of native ads that have been successfully loaded.
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();

    public SubCategory2ThFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // retain this fragment
//        setRetainInstance(true);
//
//    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sub2_categories, container, false);
        ButterKnife.bind(this, root);
        homeCycleActivity= (HomeCycleActivity) getActivity();
        homeCycleActivity.setToolBar(View.VISIBLE, getString(R.string.the_sub_categories)
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBack();
                    }
                });
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(getActivity(), getString(R.string.admob_id));
        rowListItem = getAllItemList();
        loadNativeAds();
        insertAdsInSubCategoryItems();
        // Update the RecyclerView item's list with native ads.
//        lLayout = new LinearLayoutManager(getActivity());
        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView.
//        rView.setHasFixedSize(true);

        rView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

//        rView.setLayoutManager(lLayout);

        Category2ThAdapter rcAdapter = new Category2ThAdapter(getContext(),getActivity(), mRecyclerViewItems);
        rView.setAdapter(rcAdapter);

        // 5. set item animator to DefaultAnimator
        rView.setItemAnimator(new DefaultItemAnimator());
        toolHidden();

        return root;
    }
    private List<ItemObjectModel> getAllItemList(){

        List<ItemObjectModel> allItems = new ArrayList<ItemObjectModel>();
        allItems.add(new ItemObjectModel("محلات ومطاعم"));
        allItems.add(new ItemObjectModel("عقارات"));
        allItems.add(new ItemObjectModel("مجوهرات"));
        allItems.add(new ItemObjectModel("الكترونيات"));
        allItems.add(new ItemObjectModel("مركبات"));
        allItems.add(new ItemObjectModel("مستلزمات منزليه"));
        allItems.add(new ItemObjectModel("ملابس واكسسورات"));
        allItems.add(new ItemObjectModel("حيوانات"));
        allItems.add(new ItemObjectModel("مستلزمات رياضيه"));
        allItems.add(new ItemObjectModel("مستلزمات فنيه وثقافيه"));
        allItems.add(new ItemObjectModel("معدات طبيه"));
        allItems.add(new ItemObjectModel("معدات صناعه"));
        allItems.add(new ItemObjectModel("مستلزمات مطاعم"));
        allItems.add(new ItemObjectModel("اخرى"));

        return allItems;
    }

    private void insertAdsInSubCategoryItems() {
        if (mNativeAds.size() <= 0) {
            return;
        }

        int mRecyclerViewItemsSize = (mRecyclerViewItems.size() + mNativeAds.size()) +2;
        int gIndex = 0;        int adIndex = 0;
        int itemIndex = 0;

        for (gIndex=0;gIndex<mRecyclerViewItemsSize;gIndex++) {
            if(gIndex%4==0){
            mRecyclerViewItems.add(gIndex,mNativeAds.get(adIndex));
                adIndex++;
                gIndex++;
            }else {
                mRecyclerViewItems.add(gIndex,rowListItem.get(itemIndex));
                itemIndex++;
            }
        }
//        loadMenu();
    }
//    private void insertAdsInSubCategoryItems() {
//        if (mNativeAds.size() <= 0) {
//            return;
//        }
//
//        int offset = (mRecyclerViewItems.size() / mNativeAds.size()) + 1;
//        int index = 0;
//        for (UnifiedNativeAd ad : mNativeAds) {
//            mRecyclerViewItems.add(index, ad);
//            index = index + offset;
//        }
////        loadMenu();
//    }

//    public List<Object> getRecyclerViewItems() {
//        return mRecyclerViewItems;
//    }

    private void loadNativeAds() {

        AdLoader.Builder builder = new AdLoader.Builder(getActivity(), getString(R.string.samble_native_add));
        adLoader = builder.forUnifiedNativeAd(
                new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // A native ad loaded successfully, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        mNativeAds.add(unifiedNativeAd);
                        if (!adLoader.isLoading()) {
//                            insertAdsInSubCategoryItems();
                        }
                    }
                }).withAdListener(
                new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // A native ad failed to load, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        Log.e("MainActivity", "The previous native ad failed to load. Attempting to"
                                + " load another.");
                        if (!adLoader.isLoading()) {
//                            insertAdsInSubCategoryItems();
                        }
                    }
                }).build();

        // Load the Native ads.
        adLoader.loadAds(new AdRequest.Builder().build(), NUMBER_OF_ADS);
    }
    private void toolHidden() {
        rView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && homeCycleActivity.toolBarLay.getVisibility() == View.VISIBLE) {
                    homeCycleActivity.toolBarLay.setVisibility(View.GONE);
                } else if (dy < 0 && homeCycleActivity.toolBarLay.getVisibility() != View.VISIBLE) {
                    homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new SubCategoryFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,"t");
//        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }
}