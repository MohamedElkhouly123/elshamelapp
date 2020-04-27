package com.example.elshamelapp.Product_details;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.example.elshamelapp.R;
import com.example.elshamelapp.adapter.UltraPagerAdapter;
import com.tmall.ultraviewpager.UltraViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyProduct extends AppCompatActivity {
    @BindView(R.id.ultra_viewpager)
    UltraViewPager ultraViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_product);
        ButterKnife.bind(this);

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

    }
}
