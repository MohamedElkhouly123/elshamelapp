package com.example.OneForAll.view.fragment.HomeCycle2.category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OneForAll.R;
import com.example.OneForAll.adapter.CategorySub2Adapter;
import com.example.OneForAll.data.model.ItemObjectModel;
import com.example.OneForAll.view.activity.HomeCycleActivity;
import com.example.OneForAll.view.fragment.BaSeFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.OneForAll.utils.HelperMethod.replaceFragment;
import static com.example.OneForAll.utils.HelperMethod.showToast;

public class SubCategory2ThFragment extends BaSeFragment {

    @BindView(R.id.sub_category2_fragment_make_full_screen_floating_action_btn)
    FloatingActionButton subCategory2FragmentMakeFullScreenFloatingActionBtn;
    private GridLayoutManager gLayout;
    @BindView(R.id.sub_category2_fragment_recycler_view)
    RecyclerView rView;
    // The number of native ads to load.
    public static final int NUMBER_OF_ADS = 3;
    private List<ItemObjectModel> rowListItem;
    // The AdLoader used to load ads.
    private AdLoader adLoader;
    // List of MenuItems and native ads that populate the RecyclerView.
    private List<Object> mRecyclerViewItems = new ArrayList<>();
    int heightDelta = 0;
    private boolean firstPress =true;

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
        loadNativeAds();
        homeCycleActivity = (HomeCycleActivity) getActivity();
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
        rowListItem.add(0,new ItemObjectModel(""));
        rowListItem.add(new ItemObjectModel(""));
        // Update the RecyclerView item's list with native ads.
//        lLayout = new LinearLayoutManager(getActivity());
        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView.
//        rView.setHasFixedSize(true);

//        rView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        gLayout = new GridLayoutManager(getContext(), 2);
        gLayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                                     @Override
                                                     public int getSpanSize(int position) {
                                                         if (position == 0 || position ==rowListItem.size()-1) {
                                                             return 2;
                                                         }
                                                         return 1;
                                                     }
                                                 });
          rView.setLayoutManager(gLayout);

        CategorySub2Adapter rcAdapter = new CategorySub2Adapter(getContext(), getActivity(), rowListItem,mNativeAds);
        rView.setAdapter(rcAdapter);

        // 5. set item animator to DefaultAnimator
        rView.setItemAnimator(new DefaultItemAnimator());
        toolHidden();

        return root;
    }

    private List<ItemObjectModel> getAllItemList() {

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

        int mRecyclerViewItemsSize = (mRecyclerViewItems.size() + mNativeAds.size()) + 2;
        int gIndex = 0;
        int adIndex = 0;
        int itemIndex = 0;

        for (gIndex = 0; gIndex < mRecyclerViewItemsSize; gIndex++) {
            if (gIndex % 4 == 0) {
                mRecyclerViewItems.add(gIndex, mNativeAds.get(adIndex));
                adIndex++;
                gIndex++;
            } else {
                mRecyclerViewItems.add(gIndex, rowListItem.get(itemIndex));
                itemIndex++;
            }
        }
//        loadMenu();
    }

    private void loadNativeAds() {

        AdLoader.Builder builder = new AdLoader.Builder(getActivity(), getString(R.string.samble_native_add));
        adLoader = builder.forUnifiedNativeAd(
                new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // A native ad loaded successfully, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        mNativeAds.add(unifiedNativeAd);
//                        populateNativeAdView(unifiedNativeAd);
                        if (!adLoader.isLoading()) {
                            mNativeAds.add(unifiedNativeAd);
//                            populateNativeAdView(unifiedNativeAd);
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
                            showToast(getActivity(), "load ads faild" );
                            loadNativeAds();
//                            insertAdsInSubCategoryItems();
//                            populateNativeAdView(unifiedNativeAd);

                        }
                    }
                }).build();

        // Load the Native ads.
        adLoader.loadAds(new AdRequest.Builder().build(), NUMBER_OF_ADS);
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

//    private void loadNativeAds() {
//
//        AdLoader.Builder builder = new AdLoader.Builder(getActivity(), getString(R.string.samble_native_add));
//        adLoader = builder.forUnifiedNativeAd(
//                new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
//                    @Override
//                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
//                        // A native ad loaded successfully, check if the ad loader has finished loading
//                        // and if so, insert the ads into the list.
//                        mNativeAds.add(unifiedNativeAd);
//                        if (!adLoader.isLoading()) {
////                            insertAdsInSubCategoryItems();
//                        }
//                    }
//                }).withAdListener(
//                new AdListener() {
//                    @Override
//                    public void onAdFailedToLoad(int errorCode) {
//                        // A native ad failed to load, check if the ad loader has finished loading
//                        // and if so, insert the ads into the list.
//                        Log.e("MainActivity", "The previous native ad failed to load. Attempting to"
//                                + " load another.");
//                        if (!adLoader.isLoading()) {
//                            loadNativeAds();
////                            insertAdsInSubCategoryItems();
//                        }
//                    }
//                }).build();
//
//        // Load the Native ads.
//        adLoader.loadAds(new AdRequest.Builder().build(), NUMBER_OF_ADS);
//    }

    private void toolHidden() {
        rView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                heightDelta += dy;

                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && subCategory2FragmentMakeFullScreenFloatingActionBtn.getVisibility() == View.VISIBLE) {
                    subCategory2FragmentMakeFullScreenFloatingActionBtn.hide();

//                    homeCycleActivity.toolBarLay.animate().translationY(-heightDelta).setInterpolator(new AccelerateInterpolator()).start();
//                    homeCycleActivity.toolBarLay.setVisibility(View.GONE);

                } else if (dy < 0 && subCategory2FragmentMakeFullScreenFloatingActionBtn.getVisibility() != View.VISIBLE) {
                    subCategory2FragmentMakeFullScreenFloatingActionBtn.show();
//                    homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);
//                    homeCycleActivity.toolBarLay.animate().translationY(heightDelta).setInterpolator(new AccelerateInterpolator()).start();

                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
//        homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);
//        homeCycleActivity.toolBarLay.animate().translationY(heightDelta).setInterpolator(new AccelerateInterpolator()).start();
        subCategory2FragmentMakeFullScreenFloatingActionBtn.show();

    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new SubCategoryFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,"t");
//        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }

    @OnClick(R.id.sub_category2_fragment_make_full_screen_floating_action_btn)
    public void onViewClicked() {
        if(firstPress){
            subCategory2FragmentMakeFullScreenFloatingActionBtn.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fullscreen_black_24dp));
            homeCycleActivity.toolBarLay.setVisibility(View.GONE);
//            categoryFragmentMakeFullScreenFloatingActionBtn.setImageResource(R.drawable.ic_fullscreen_black_24dp);

            firstPress=false;
        }else {
            subCategory2FragmentMakeFullScreenFloatingActionBtn.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fullscreen_exit_black_24dp));
            homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);
//            categoryFragmentMakeFullScreenFloatingActionBtn.setImageResource(R.drawable.ic_fullscreen_exit_black_24dp);

            firstPress=true;
        }
        subCategory2FragmentMakeFullScreenFloatingActionBtn.hide();
        subCategory2FragmentMakeFullScreenFloatingActionBtn.show();
    }
}