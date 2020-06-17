package com.example.OneForAll.view.fragment.HomeCycle2.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.example.OneForAll.R;
import com.example.OneForAll.adapter.HomeSectionsPagerAdapter;
import com.example.OneForAll.view.activity.HomeCycleActivity;
import com.example.OneForAll.view.fragment.BaSeFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeContainerFragment extends BaSeFragment {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private Bundle bundle;
    private HomeSectionsPagerAdapter adapter;
    public HomeContainerFragment() {
        // Required empty public constructor
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home_content_main2, container, false);
        ButterKnife.bind(this, root);
        homeCycleActivity = (HomeCycleActivity) getActivity();
        homeCycleActivity.setFloatBottonAndToolBar(View.VISIBLE);
        homeCycleActivity.setToolBar(View.GONE, null
                , null);
        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,false);

//        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);




//        tabLayout.addTab(tabLayout.newTab().setText("مفقوداتى"));
//        tabLayout.addTab(tabLayout.newTab().setText("مشترواتى"));
//        tabLayout.addTab(tabLayout.newTab().setText("سوق الجملة"));
//        tabLayout.addTab(tabLayout.newTab().setText("الحجز والايجار"));
//        tabLayout.addTab(tabLayout.newTab().setText("مزاداتى"));
//        tabLayout.addTab(tabLayout.newTab().setText("الوظائف والخدمات"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
         adapter = new HomeSectionsPagerAdapter(getContext(), getChildFragmentManager());
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

//        viewPager.setOffscreenPageLimit(0);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        ViewPager.OnPageChangeListener pagechangelistener = new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                adapter.notifyDataSetChanged();
//                SaveData(getActivity(),TABPOSION,String.valueOf(position));
//
////                indicator.setCurrentItem(arg0);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        };
//        viewPager.setOnPageChangeListener(pagechangelistener);

//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
////                viewPager.setCurrentItem(tab.getPosition());
////                viewPager.setAdapter(adapter);
//                if(tab.getPosition()==0)
//                {
//                    viewPager.setCurrentItem(0);
//                }else if(tab.getPosition()==1)
//                {
//                    viewPager.setCurrentItem(1);
//                }else if(tab.getPosition()==2)
//                {
//                    viewPager.setCurrentItem(2);
//                }else if(tab.getPosition()==3)
//                {
//                    viewPager.setCurrentItem(3);
//                }
//                else if(tab.getPosition()==4)
//                {
//                    viewPager.setCurrentItem(4);
//                }else if(tab.getPosition()==5)
//                {
//                    viewPager.setCurrentItem(5);
//                }
////                TableInfo tag = (TableInfo) tab.getTag();
////                for (int i=0; i<tabLayout.getScrollBarSize(); i++) {
////                    if (tabLayout.get(i) == tag) {
////                        viewPager.setCurrentItem(i);
////                    }
////                }
//                int tabPossion=tab.getPosition();
//
//                SaveData(getActivity(),TABPOSION,String.valueOf(tabPossion));
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
////                viewPager.setCurrentItem(tab.getPosition());
////                viewPager.setAdapter(adapter);
//
//            }
//        });
        return root;
    }

//    @Override
//    public void onBack() {
//
//
////        DrawerLayout drawer = getView().findViewById(R.id.drawer_layout);
//
//    }
}

//    HomeTabbsCustumAdapter adapter = new HomeTabbsCustumAdapter(getChildFragmentManager());
//        bundle=new Bundle();
//                bundle.putString("ISAnyHomeTabe","tabe1");
//                HomeSubFragment homeSubFragment1=new HomeSubFragment();
//                homeSubFragment1.setArguments(bundle);
//                adapter.addPager(homeSubFragment1, "مفقودات");
//                bundle=new Bundle();
//                bundle.putString("ISAnyHomeTabe","tabe2");
//                HomeSubFragment homeSubFragment2=new HomeSubFragment();
//                homeSubFragment2.setArguments(bundle);
//                adapter.addPager(homeSubFragment2, "مفقودات");
//                bundle=new Bundle();
//                bundle.putString("ISAnyHomeTabe",adapter.getItem);
//                HomeSubFragment homeSubFragment3=new HomeSubFragment();
//                homeSubFragment3.setArguments(bundle);
//                adapter.addPager(homeSubFragment3, "مفقودات");
//                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//                viewPager.setAdapter(adapter);
//                tabLayout.setupWithViewPager(viewPager);
//                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));