package com.example.elshamelapp.view.activity;

import android.os.Bundle;

import com.example.elshamelapp.R;
import com.example.elshamelapp.view.fragment.splashCycle.AboutAppAndIntroFragment;

import butterknife.ButterKnife;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;

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

