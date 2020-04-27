package com.example.elshamelapp.view.fragment.HomeCycle2.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import com.example.elshamelapp.R;
import com.example.elshamelapp.view.activity.HomeCycleActivity;
import com.example.elshamelapp.view.fragment.BaSeFragment;

import butterknife.ButterKnife;


public class HomeFragment extends BaSeFragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.content_main2, container, false);
        ButterKnife.bind(this, root);
        homeCycleActivity = (HomeCycleActivity) getActivity();
        homeCycleActivity.setFloatBottonAndToolBar(View.VISIBLE);

//        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        return root;
    }

    @Override
    public void onBack() {
//        DrawerLayout drawer = getView().findViewById(R.id.drawer_layout);
        if (homeCycleActivity.drawer.isDrawerOpen(GravityCompat.START)) {
            homeCycleActivity.drawer.closeDrawer(GravityCompat.START);
        } else {
        getActivity().finish();}
    }
}