package com.example.elshamelapp.view.fragment.HomeCycle2.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.elshamelapp.R;
import com.example.elshamelapp.view.fragment.BaSeFragment;
import com.example.elshamelapp.view.fragment.HomeCycle2.home.HomeFragment;

import butterknife.ButterKnife;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;


public class UploadAddFragment extends BaSeFragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_uplood_product, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,false);
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }
}