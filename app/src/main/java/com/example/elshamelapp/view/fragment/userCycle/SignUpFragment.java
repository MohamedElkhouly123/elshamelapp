package com.example.elshamelapp.view.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.elshamelapp.R;
import com.example.elshamelapp.view.fragment.BaSeFragment;
import com.example.elshamelapp.view.fragment.HomeCycle2.home.profileFragments.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;
import static com.example.elshamelapp.utils.HelperMethod.replaceFragmentWithAnimation;


public class SignUpFragment extends BaSeFragment {

    @BindView(R.id.login2)
    TextView login2;

//    public SignUpFragment(String signUpOrEditProfile) {
//        this.signUpOrEditProfile = signUpOrEditProfile;
//    }

    private String signUpOrEditProfile="";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if(this.getArguments()!=null)
        {
            signUpOrEditProfile = this.getArguments().getString("ISSIGNUP");

        }
        View root = inflater.inflate(R.layout.fragment_sign_up_and_edit_profile, container, false);

        ButterKnife.bind(this, root);

        return root;
    }
    @OnClick(R.id.login2)
    void signUp(){
        replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(), "b");


    }
    @Override
    public void onBack() {
        if(signUpOrEditProfile.equalsIgnoreCase("signUp")){
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new RegisterFragment());}
        else {
            Bundle bundle=new Bundle();
            bundle.putString("ISMYPROFILE","myProfile");
            ProfileFragment profileFragment=new ProfileFragment();
            profileFragment.setArguments(bundle);
            replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, profileFragment);
            }

    }
}