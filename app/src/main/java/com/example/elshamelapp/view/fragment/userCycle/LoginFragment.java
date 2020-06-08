package com.example.elshamelapp.view.fragment.userCycle;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.elshamelapp.R;
import com.example.elshamelapp.view.fragment.BaSeFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;
import static com.example.elshamelapp.utils.HelperMethod.replaceFragmentWithAnimation;


public class LoginFragment extends BaSeFragment {

    @BindView(R.id.fragment_login_sign_up_tv)
    TextView signUp;
    @BindView(R.id.list)
    ImageView list;
    @BindView(R.id.fragment_login_username_email_tin)
    TextInputLayout fragmentLoginUsernameEmailTin;
    @BindView(R.id.fragment_login_password_tin)
    TextInputLayout fragmentLoginPasswordTin;
    private AdView mAdView;
    public LoginFragment() {
        // Required empty public constructor
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, root);
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = root.findViewById(R.id.fragment_login_banner_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return root;
    }


    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new RegisterFragment());

    }

    @OnClick({R.id.list, R.id.fragment_login_forge_password_tv, R.id.fragment_login_btn_login, R.id.fragment_login_sign_up_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.list:
                break;
            case R.id.fragment_login_forge_password_tv:
                showForgetPasswordDialog();
                break;
            case R.id.fragment_login_btn_login:
                break;
            case R.id.fragment_login_sign_up_tv:
                Bundle bundle=new Bundle();
                bundle.putString("ISSIGNUP","signUp");
                SignUpFragment signUpFragment=new SignUpFragment();
                signUpFragment.setArguments(bundle);
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram,signUpFragment, "b");

                break;
        }
    }

    private void showForgetPasswordDialog(){
        try {
                final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_forget_password, null);
//            alertDialog = new AlertDialog.Builder(getContext()).create();
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(getContext()).create();
            alertDialog.setTitle(getString(R.string.please_enter_your_email));
//            alertDialog.setMessage(getString(R.string.please_enter_your_email));
            alertDialog.setCancelable(false);
            TextInputLayout restaurantAddCategoryDialogTilCategoryName = (TextInputLayout) view.findViewById(R.id.dialog_forget_password_login_fragment_til_email);

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.submit), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new SetNewPasswordFragment(), "r");


                }
            });


            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,  getString(R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss() ;
                }
            });

                alertDialog.setView(view);
            alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onShow(DialogInterface arg0) {
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.blue);
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.blue);

                }
            });

            alertDialog.show();

        } catch (Exception e) {

        }




}
}