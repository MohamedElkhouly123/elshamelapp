package com.example.OneForAll.view.fragment.HomeCycle2.home.profileFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.OneForAll.R;
import com.example.OneForAll.adapter.MyProductsAndSearchAddsAdapter;
import com.example.OneForAll.data.model.ProductDataModel;
import com.example.OneForAll.view.activity.HomeCycleActivity;
import com.example.OneForAll.view.fragment.BaSeFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.home.HomeContainerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.OneForAll.utils.HelperMethod.replaceFragment;


public class MyProdactsAndSearchAddsFragment extends BaSeFragment {

    @BindView(R.id.my_products_and_search_ads_fragment_recycler_view)
    RecyclerView myProductsAndSearchAdsFragmentRecyclerView;

//    public MyProdactsAndSearchAddsFragment(String myProductsAndSearchOrOther) {
//        this.myProductsAndSearchOrOther = myProductsAndSearchOrOther;
//    }
public MyProdactsAndSearchAddsFragment() {
    // Required empty public constructor
}

    private String myProductsAndSearchOrOther = "";
    private List<ProductDataModel> profileItemDataList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if(this.getArguments()!=null)
        {
            myProductsAndSearchOrOther = this.getArguments().getString("ISMYPRODUCTSANDSEARCH");

        }
        View root = inflater.inflate(R.layout.fragment_my_prodacts_and_search_adds, container, false);

        ButterKnife.bind(this, root);
        homeCycleActivity = (HomeCycleActivity) getActivity();
        homeCycleActivity.setToolBar(View.VISIBLE, getString(R.string.the_sub_categories)
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBack();
                    }
                });
        profileItemDataList = getAllItemList();
//        lLayout = new LinearLayoutManager(getActivity());
        myProductsAndSearchAdsFragmentRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

//        rView.setLayoutManager(lLayout);

        MyProductsAndSearchAddsAdapter myProductsAndSearchAddsAdapter = new MyProductsAndSearchAddsAdapter(getContext(), getActivity(), profileItemDataList);

        myProductsAndSearchAdsFragmentRecyclerView.setAdapter(myProductsAndSearchAddsAdapter);

        // 5. set item animator to DefaultAnimator
        myProductsAndSearchAdsFragmentRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        toolHidden();
        return root;
    }

    private List<ProductDataModel> getAllItemList() {

        profileItemDataList = new ArrayList<ProductDataModel>();
        profileItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        profileItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.chale));
        profileItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.chale));
        profileItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        profileItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        profileItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        profileItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.chale));


        return profileItemDataList;
    }
    private void toolHidden() {
        myProductsAndSearchAdsFragmentRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeContainerFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,false);
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }
}