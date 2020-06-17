package com.example.OneForAll.view.activity;

import android.os.Bundle;

import com.example.OneForAll.R;
import com.example.OneForAll.view.fragment.splashCycle.AboutAppAndIntroFragment;

import butterknife.ButterKnife;

import static com.example.OneForAll.utils.HelperMethod.replaceFragment;

public class AboutApp extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        Bundle bundle = new Bundle();
        bundle.putBoolean("INHOME", false);
        AboutAppAndIntroFragment aboutAppAndIntroFragment = new AboutAppAndIntroFragment();
        aboutAppAndIntroFragment.setArguments(bundle);
        replaceFragment(getSupportFragmentManager(), R.id.about_app_activity_fram,aboutAppAndIntroFragment);
        ButterKnife.bind(this);
    }


//    @Override
//    public void onBackPressed() {
//
//        this.finish();
//    }
}

