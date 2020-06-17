package com.example.OneForAll.view.fragment.HomeCycle2.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.OneForAll.R;
import com.example.OneForAll.view.fragment.BaSeFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.home.HomeContainerFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.home.profileFragments.ProfileFragment;

import butterknife.ButterKnife;

import static com.example.OneForAll.utils.HelperMethod.replaceFragment;


public class UploadAddFragment extends BaSeFragment {

//    public UploadAddFragment(String addFromHomeOrProfile) {
//        this.addFromHomeOrProfile = addFromHomeOrProfile;
//    }

    private String addFromHomeOrProfile="";

    public UploadAddFragment() {
        // Required empty public constructor
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if(this.getArguments()!=null)
        {
            addFromHomeOrProfile = this.getArguments().getString("ISADDSFROMHOME");

        }
        View root = inflater.inflate(R.layout.fragment_uplood_product, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onBack() {
        if (addFromHomeOrProfile.equalsIgnoreCase("home")) {
            replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeContainerFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,false);
            homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
        }else
        {
            Bundle bundle=new Bundle();
            bundle.putString("ISMYPROFILE","myProfile");
            ProfileFragment profileFragment=new ProfileFragment();
            profileFragment.setArguments(bundle);
            replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, profileFragment);

            }

    }
}