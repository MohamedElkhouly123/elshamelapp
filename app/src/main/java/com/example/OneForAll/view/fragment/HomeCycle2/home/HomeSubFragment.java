package com.example.OneForAll.view.fragment.HomeCycle2.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OneForAll.R;
import com.example.OneForAll.adapter.SubHomeCategoryHzRvAdapter;
import com.example.OneForAll.data.model.ItemObjectModel;
import com.example.OneForAll.view.activity.HomeCycleActivity;
import com.example.OneForAll.view.fragment.BaSeFragment;
import com.example.OneForAll.view.viewModel.HomePageViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeSubFragment extends BaSeFragment {


    @BindView(R.id.fragment_sub_home_recycler_view)
    RecyclerView fragmentSubHomeRecyclerView;
    private String tabeNum;
    //    private TabNumModel tabNumModel;
    @BindView(R.id.section_label)
    TextView sectionLabel;
    private LinearLayoutManager lLayout;

    public HomeSubFragment() {
        // Required empty public constructor
    }

    private static final String ARG_SECTION_NUMBER = "section_number";

    private HomePageViewModel pageViewModel;

    public static HomeSubFragment newInstance(int index) {
        HomeSubFragment fragment = new HomeSubFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(HomePageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        if(this.getArguments()!=null)
//        {
//            tabeNum = this.getArguments().getString("ISAnyHomeTabe");
//            showToast(getActivity(), tabeNum);
//
//        }
//        tabeNum = LoadData(getActivity(), TABPOSION);


        View root = inflater.inflate(R.layout.fragment_home_sub, container, false);
        ButterKnife.bind(this, root);

        homeCycleActivity = (HomeCycleActivity) getActivity();
//        tabNumModel=new TabNumModel();
//        tabeNum=tabNumModel.getTabeNum();
        List<ItemObjectModel> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getActivity());

        fragmentSubHomeRecyclerView.setLayoutManager(lLayout);

        SubHomeCategoryHzRvAdapter rcAdapter = new SubHomeCategoryHzRvAdapter(getContext(), getActivity(), rowListItem);
        fragmentSubHomeRecyclerView.setAdapter(rcAdapter);

        // 5. set item animator to DefaultAnimator
        fragmentSubHomeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                sectionLabel.setText(s);
                tabeNum = sectionLabel.getText().toString().trim();
//
//                showToast(getActivity(), tabeNum);
            }
        });




        return root;
    }

    private List<ItemObjectModel> getAllItemList() {

        List<ItemObjectModel> allItems = new ArrayList<ItemObjectModel>();
        allItems.add(new ItemObjectModel("مفقوداتى", R.drawable.category_my_losts2));
//        allItems.add(new ItemObjectModel("مشاكل مصر", R.drawable.chale));
        allItems.add(new ItemObjectModel("مشترواتى", R.drawable.category_my_shoping2));
        allItems.add(new ItemObjectModel("سوق الجمله", R.drawable.category_souq_elgomlah1));// المصانع والشركات
        allItems.add(new ItemObjectModel("الحجز والايجار", R.drawable.category_egar1));
        allItems.add(new ItemObjectModel("مزاداتى", R.drawable.category_mazad4));
        allItems.add(new ItemObjectModel("الوظائف والخدمات", R.drawable.category_wazaaf4));
//        allItems.add(new ItemObjectModel("الصناعه والتجاره", R.drawable.flat));


        return allItems;
    }

    @Override
    public void onBack() {
        if (homeCycleActivity.drawer.isDrawerOpen(GravityCompat.START)) {
            homeCycleActivity.drawer.closeDrawer(GravityCompat.START);
        } else {

//            if (getChildFragmentManager().getBackStackEntryCount() > 0) {
////                fm.popBackStack();
//                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeContainerFragment());
//////        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,false);
//                homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
//            }else {
            getActivity().finish();
//            }

//            if (tabeNum.equalsIgnoreCase("tabe1")) {
//                getActivity().finish();
//            } else {
//                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeContainerFragment());
////        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,false);
//                homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
//            }
        }

    }
}