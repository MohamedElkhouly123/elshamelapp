package com.example.OneForAll.view.fragment.HomeCycle2.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.OneForAll.R;
import com.example.OneForAll.view.fragment.BaSeFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.home.HomeContainerFragment;

import butterknife.ButterKnife;

import static com.example.OneForAll.utils.HelperMethod.replaceFragment;


public class ContactUSFragment extends BaSeFragment {

    public ContactUSFragment() {
        // Required empty public constructor
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contact_us, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeContainerFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,false);
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }
}