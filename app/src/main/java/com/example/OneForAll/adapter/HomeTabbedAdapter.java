package com.example.OneForAll.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.OneForAll.view.fragment.HomeCycle2.home.HomeContainerFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.home.HomeSubFragment;

public class HomeTabbedAdapter extends FragmentPagerAdapter {
    private HomeContainerFragment context;
    private int totalTabs;
    private HomeSubFragment article;
    //    private Bundle bundle;
    public HomeTabbedAdapter(HomeContainerFragment c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        HomeSubFragment homeSubFragment1=new HomeSubFragment();

        switch (position) {
            case 0:
             Bundle bundle=new Bundle();
                bundle.putString("ISAnyHomeTabe","tabe1");
                homeSubFragment1.setArguments(bundle);
                return homeSubFragment1;
            case 1:
                Bundle bundle2=new Bundle();
                bundle2.putString("ISAnyHomeTabe","tabe2");
                HomeSubFragment homeSubFragment2=new HomeSubFragment();
                homeSubFragment2.setArguments(bundle2);
                return homeSubFragment2;
            case 2:
                Bundle bundle3=new Bundle();
                bundle3.putString("ISAnyHomeTabe","tabe3");
                HomeSubFragment homeSubFragment3=new HomeSubFragment();
                homeSubFragment3.setArguments(bundle3);
                return homeSubFragment3;
            case 3:
                Bundle bundle4=new Bundle();
                bundle4.putString("ISAnyHomeTabe","tabe4");
                HomeSubFragment homeSubFragment4=new HomeSubFragment();
                homeSubFragment4.setArguments(bundle4);
                return homeSubFragment4;
            case 4:
                bundle=new Bundle();
                bundle.putString("ISAnyHomeTabe","tabe5");
                HomeSubFragment homeSubFragment5=new HomeSubFragment();
                homeSubFragment5.setArguments(bundle);
                return homeSubFragment5;
            case 5:
                Bundle bundle5=new Bundle();
                bundle5.putString("ISAnyHomeTabe","tabe6");
                HomeSubFragment homeSubFragment6=new HomeSubFragment();
                homeSubFragment6.setArguments(bundle5);
                return homeSubFragment6;

            default:
                return  homeSubFragment1;
        }
    }
//    @Override
//    public Fragment getItem(int position2) {
//        return fragments.get(position2);
//    }

    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
