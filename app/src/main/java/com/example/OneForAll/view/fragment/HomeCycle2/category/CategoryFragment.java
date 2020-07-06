package com.example.OneForAll.view.fragment.HomeCycle2.category;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OneForAll.R;
import com.example.OneForAll.adapter.CategoryAdapter;
import com.example.OneForAll.data.model.ItemObjectModel;
import com.example.OneForAll.view.activity.HomeCycleActivity;
import com.example.OneForAll.view.fragment.BaSeFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.home.HomeContainerFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.OneForAll.utils.HelperMethod.replaceFragment;
import static com.example.OneForAll.utils.HelperMethod.showToast;

public class CategoryFragment extends BaSeFragment {

    private static final int NUMBER_OF_ADS =3 ;
    @BindView(R.id.category_fragment_make_full_screen_floating_action_btn)
    FloatingActionButton categoryFragmentMakeFullScreenFloatingActionBtn;
    private AdLoader adLoader;
    //    @BindView(R.id.tool_text_hide)
//    TextView toolTextHide;
    private LinearLayoutManager lLayout;
    @BindView(R.id.category_fragment_recycler_view)
    RecyclerView rView;
    int heightDelta = 0;
    private boolean firstPress =true;
    // List of native ads that have been successfully loaded.
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();
    public CategoryFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, root);
//        rView.setNestedScrollingEnabled(false);
//        rView.setHasFixedSize(false);
        loadNativeAds();
        homeCycleActivity = (HomeCycleActivity) getActivity();
        homeCycleActivity.setToolBar(View.VISIBLE, getString(R.string.the_categories)
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBack();
                    }
                });
        List<ItemObjectModel> rowListItem = getAllItemList();

//        add null items for adds
        rowListItem.add(0,new ItemObjectModel(""));
//        rowListItem.add(3,new ItemObjectModel(""));
        rowListItem.add(new ItemObjectModel(""));
        lLayout = new LinearLayoutManager(getActivity());
//////////////////////////////////////////////////////////////////
        rView.setLayoutManager(lLayout);

        CategoryAdapter rcAdapter = new CategoryAdapter(getContext(), getActivity(), rowListItem,mNativeAds);
        rView.setAdapter(rcAdapter);

        // 5. set item animator to DefaultAnimator
        rView.setItemAnimator(new DefaultItemAnimator());

        toolHidden();

        return root;
    }

    private List<ItemObjectModel> getAllItemList() {

        List<ItemObjectModel> allItems = new ArrayList<ItemObjectModel>();
        allItems.add(new ItemObjectModel("مفقوداتى", R.drawable.category_my_losts2)); // فاقد شىء ولا واجد شىء
//        اوراق مهمه وبطاقات شخصيه وبطاقات فيزا وائتمان ومحافظ وسعات وملابس واحذيه واغراض ثمينه اخرى والكترونات ومبيلات ولابتوب ومجوهرات وذهب واموال اشخاص واطفال تائهه او مفقوده ومركبات ودرجات مفقوده
//        allItems.add(new ItemObjectModel("مشاكل مصر", R.drawable.chale));
        allItems.add(new ItemObjectModel("مشترواتى", R.drawable.category_my_shoping2));
        allItems.add(new ItemObjectModel("سوق الجمله", R.drawable.category_souq_elgomlah1));//   المصانع والشركات والتجار
        allItems.add(new ItemObjectModel("الحجز والايجار", R.drawable.category_egar1));
        allItems.add(new ItemObjectModel("مزاداتى", R.drawable.category_mazad4));
        allItems.add(new ItemObjectModel("الوظائف والخدمات", R.drawable.category_wazaaf4)); // ابحث عن عمل ولا ابحث عن موظفين
//        allItems.add(new ItemObjectModel("الصناعه والتجاره", R.drawable.flat));
        allItems.add(new ItemObjectModel("سوق الجرافيك والسوفتوير", R.drawable.software_category));
//        وانيماشن وجرافيكس وفوتوشوب وموشن جرافيك وتطبيقات ديسكتوب وموبيل app (اندرويد وايفون) ومواقع والعاب وافلام ومسرحيات ومسلسلات ,انمى و مانجا

        return allItems;
    }

    private void toolHidden() {
        rView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                heightDelta += dy;

                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && categoryFragmentMakeFullScreenFloatingActionBtn.getVisibility() == View.VISIBLE) {
                    categoryFragmentMakeFullScreenFloatingActionBtn.hide();

//                    homeCycleActivity.toolBarLay.animate().translationY(-heightDelta).setInterpolator(new AccelerateInterpolator()).start();
//                    homeCycleActivity.toolBarLay.setVisibility(View.GONE);

                } else if (dy < 0 && categoryFragmentMakeFullScreenFloatingActionBtn.getVisibility() != View.VISIBLE) {
                    categoryFragmentMakeFullScreenFloatingActionBtn.show();
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
        categoryFragmentMakeFullScreenFloatingActionBtn.show();

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



    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeContainerFragment());
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);

    }

    @SuppressLint("RestrictedApi")
    @OnClick(R.id.category_fragment_make_full_screen_floating_action_btn)
    public void onViewClicked() {
        if(firstPress){
            categoryFragmentMakeFullScreenFloatingActionBtn.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fullscreen_black_24dp));
            homeCycleActivity.toolBarLay.setVisibility(View.GONE);
//            categoryFragmentMakeFullScreenFloatingActionBtn.setImageResource(R.drawable.ic_fullscreen_black_24dp);

            firstPress=false;
        }else {
            categoryFragmentMakeFullScreenFloatingActionBtn.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fullscreen_exit_black_24dp));
            homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);
//            categoryFragmentMakeFullScreenFloatingActionBtn.setImageResource(R.drawable.ic_fullscreen_exit_black_24dp);

            firstPress=true;
        }
        categoryFragmentMakeFullScreenFloatingActionBtn.hide();
        categoryFragmentMakeFullScreenFloatingActionBtn.show();
    }
}