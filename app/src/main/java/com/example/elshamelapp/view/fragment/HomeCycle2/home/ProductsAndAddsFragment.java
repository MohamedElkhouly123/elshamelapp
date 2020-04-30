package com.example.elshamelapp.view.fragment.HomeCycle2.home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.elshamelapp.R;
import com.example.elshamelapp.adapter.UltraPagerAdapter;
import com.example.elshamelapp.view.fragment.BaSeFragment;
import com.tmall.ultraviewpager.UltraViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;


public class ProductsAndAddsFragment extends BaSeFragment {

    @BindView(R.id.ultra_viewpager)
    UltraViewPager ultraViewPager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_product, container, false);

        ButterKnife.bind(this, root);
        // init all widgets in this activity
//        UltraViewPager ultraViewPager = (UltraViewPager)findViewById(R.id.ultra_viewpager);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
//initialize UltraPagerAdapter，and add child view to UltraViewPager
        PagerAdapter adapter = new UltraPagerAdapter(false);
        ultraViewPager.setAdapter(adapter);

//initialize built-in indicator
        ultraViewPager.initIndicator();
//set style of indicators
        ultraViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.parseColor("#FC3D04"))
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()));
//set the alignment
        ultraViewPager.getIndicator().setGravity(Gravity.LEFT | Gravity.BOTTOM);
        ultraViewPager.getIndicator().setMargin(80,0,0,110);

//construct built-in indicator, and add it to  UltraViewPager
        ultraViewPager.getIndicator().build();

//set an infinite loop
        ultraViewPager.setInfiniteLoop(true);
//enable auto-scroll mode
        ultraViewPager.setAutoScroll(4000);

    
        return root;
    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,false);
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }
}