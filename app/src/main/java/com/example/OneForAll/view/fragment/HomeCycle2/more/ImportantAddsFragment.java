package com.example.OneForAll.view.fragment.HomeCycle2.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OneForAll.R;
import com.example.OneForAll.adapter.ImportantAdsAndFavouritesAdapter;
import com.example.OneForAll.data.model.ProductDataModel;
import com.example.OneForAll.view.activity.HomeCycleActivity;
import com.example.OneForAll.view.fragment.BaSeFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.home.HomeContainerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.OneForAll.utils.HelperMethod.replaceFragment;


public class ImportantAddsFragment extends BaSeFragment {



    @BindView(R.id.fragment_important_ads_recycler_view)
    RecyclerView fragmentImportantAdsRecyclerView;
    private LinearLayoutManager lLayout;
    private List<ProductDataModel> importantItemDataList;

    public ImportantAddsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_important_ads, container, false);
        ButterKnife.bind(this, root);
        homeCycleActivity = (HomeCycleActivity) getActivity();
        homeCycleActivity.setToolBar(View.VISIBLE, getString(R.string.importantAds)
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBack();
                    }
                });

        importantItemDataList = getAllItemList();
        lLayout = new LinearLayoutManager(getActivity());

        fragmentImportantAdsRecyclerView.setLayoutManager(lLayout);

        ImportantAdsAndFavouritesAdapter rcAdapter = new ImportantAdsAndFavouritesAdapter(getContext(), getActivity(), importantItemDataList,true);
        fragmentImportantAdsRecyclerView.setAdapter(rcAdapter);

        // 5. set item animator to DefaultAnimator
        fragmentImportantAdsRecyclerView.setItemAnimator(new DefaultItemAnimator());



        return root;
    }

    private List<ProductDataModel> getAllItemList() {

        importantItemDataList = new ArrayList<ProductDataModel>();
        importantItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        importantItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.chale));
        importantItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.chale));
        importantItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        importantItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        importantItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        importantItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.chale));


        return importantItemDataList;
    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeContainerFragment());
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }
}