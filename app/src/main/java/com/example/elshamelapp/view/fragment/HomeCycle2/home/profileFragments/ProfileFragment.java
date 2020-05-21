package com.example.elshamelapp.view.fragment.HomeCycle2.home.profileFragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.elshamelapp.R;
import com.example.elshamelapp.adapter.ProfileItemAdapter;
import com.example.elshamelapp.data.model.ProductDataModel;
import com.example.elshamelapp.view.activity.MapsActivity;
import com.example.elshamelapp.view.fragment.BaSeFragment;
import com.example.elshamelapp.view.fragment.HomeCycle2.home.HomeFragment;
import com.example.elshamelapp.view.fragment.HomeCycle2.more.UploadAddFragment;
import com.example.elshamelapp.view.fragment.userCycle.SignUpFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;
import static com.example.elshamelapp.utils.HelperMethod.replaceFragmentWithAnimation;
import static com.example.elshamelapp.utils.HelperMethod.showToast;
import static com.example.elshamelapp.utils.ToastCreator.onCreateErrorToast;
import static com.example.elshamelapp.utils.network.InternetState.isConnected;
import static com.facebook.FacebookSdk.getApplicationContext;


public class ProfileFragment extends BaSeFragment {

    @BindView(R.id.fragment_my_profile_photo_circularImageView)
    CircleImageView fragmentMyProfilePhotoCircularImageView;
    @BindView(R.id.fragment_my_profile_name_tv)
    TextView fragmentMyProfileNameTv;
    @BindView(R.id.fragment_my_profile_is_shop_owner_tv)
    TextView fragmentMyProfileIsShopOwnerTv;
    @BindView(R.id.fragment_my_profile_num_products_tv)
    TextView fragmentMyProfileNumProductsTv;
    @BindView(R.id.fragment_my_profile_follower_number_tv)
    TextView fragmentMyProfileFollowerNumberTv;
    @BindView(R.id.fragment_my_profile_rate_tv)
    TextView fragmentMyProfileRateTv;
    @BindView(R.id.fragment_my_profile_setting_in_other_profile_ly)
    LinearLayout fragmentMyProfileSettingInOtherProfileLy;
    @BindView(R.id.fragment_my_profile_setting_in_my_profile_ly)
    LinearLayout fragmentMyProfileSettingInMyProfileLy;
    @BindView(R.id.fragment_my_profile_tv_email)
    TextView fragmentMyProfileTvEmail;
    @BindView(R.id.fragment_my_profile_tv_address)
    TextView fragmentMyProfileTvAddress;
    @BindView(R.id.fragment_my_profile_tv_phone)
    TextView fragmentMyProfileTvPhone;
    @BindView(R.id.fragment_my_profile_my_products_hz_rv)
    RecyclerView fragmentMyProfileMyProductsHzRv;
    @BindView(R.id.fragment_my_profile_my_fav_hz_rv)
    RecyclerView fragmentMyProfileMyFavHzRv;
    @BindView(R.id.fragment_my_profile_edit_flt_btn)
    FloatingActionButton fragmentMyProfileEditFltBtn;
    @BindView(R.id.profile_bottom_sheet_my_profile_lay)
    LinearLayout profileBottomSheetMyProfileLay;
    @BindView(R.id.profile_bottom_sheet_other_profile_lay)
    LinearLayout profileBottomSheetOtherProfileLay;
    @BindView(R.id.fragment_my_profile_my_fav_lay)
    CardView fragmentMyProfileMyFavLay;
    // BottomSheetBehavior variable
    private BottomSheetBehavior bottomSheetBehavior;
    private List<ProductDataModel> profileItemDataList;
    private ProfileItemAdapter profileProductItemAdapter;
    private LinearLayoutManager lLayout;
    private boolean openSheet = false;
    private Bundle bundle;
    private CostsListFragment costsListFragment;
    private MyProdactsAndSearchAddsFragment myProdactsAndSearchAddsFragment;

//    public ProfileFragment(String myProfileOrOther) {
//        this.myProfileOrOther = myProfileOrOther;
//    }

    private String myProfileOrOther = "";
    // TextView variable
//    private TextView bottomSheetHeading;

    // Button variables
//    private Button expandBottomSheetButton;
//    private Button collapseBottomSheetButton;
//    private Button hideBottomSheetButton;
//    private Button showBottomSheetDialogButton;
//    private Button go;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if(this.getArguments()!=null)
        {
            myProfileOrOther = this.getArguments().getString("ISMYPROFILE");

        }
        View root = inflater.inflate(R.layout.bottom_sheets_abb_bar, container, false);

        ButterKnife.bind(this, root);
        showToast(getActivity(), myProfileOrOther);

        setUpUI();
        bottomSheetBehavior = BottomSheetBehavior.from(root.findViewById(R.id.bottom1));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        //  go = (Button) findViewById(R.id.go);
        myProducts_rv();
        if (myProfileOrOther.equalsIgnoreCase("myProfile")) {
            myFav_rv();
        }
        return root;
    }

    @SuppressLint("RestrictedApi")
    private void setUpUI() {
        if (myProfileOrOther.equalsIgnoreCase("myProfile")) {
            fragmentMyProfileSettingInMyProfileLy.setVisibility(View.VISIBLE);
            fragmentMyProfileMyFavLay.setVisibility(View.VISIBLE);
            fragmentMyProfileEditFltBtn.setVisibility(View.VISIBLE);
            profileBottomSheetMyProfileLay.setVisibility(View.VISIBLE);


        } else {
            fragmentMyProfileSettingInOtherProfileLy.setVisibility(View.VISIBLE);
            profileBottomSheetOtherProfileLay.setVisibility(View.VISIBLE);

        }
    }

    private void myProducts_rv() {
        List<ProductDataModel> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getActivity());

//        fragmentMyProfileMyProductsHzRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        fragmentMyProfileMyProductsHzRv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

        profileProductItemAdapter = new ProfileItemAdapter(getContext(), getActivity(), rowListItem);
        fragmentMyProfileMyProductsHzRv.setAdapter(profileProductItemAdapter);

        // 5. set item animator to DefaultAnimator
        fragmentMyProfileMyProductsHzRv.setItemAnimator(new DefaultItemAnimator());
    }

    private void myFav_rv() {
        List<ProductDataModel> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getActivity());

        fragmentMyProfileMyFavHzRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//        fragmentMyProfileMyProductsHzRv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));

        profileProductItemAdapter = new ProfileItemAdapter(getContext(), getActivity(), rowListItem);
        fragmentMyProfileMyFavHzRv.setAdapter(profileProductItemAdapter);

        // 5. set item animator to DefaultAnimator
        fragmentMyProfileMyFavHzRv.setItemAnimator(new DefaultItemAnimator());
    }

    private List<ProductDataModel> getAllItemList() {

        profileItemDataList = new ArrayList<ProductDataModel>();
        profileItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        profileItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.chale));
        profileItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.chale));
        profileItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        profileItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        profileItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.flat));
        profileItemDataList.add(new ProductDataModel("سكر", "55 ج", R.drawable.chale));


        return profileItemDataList;
    }


    @Override
    public void onBack() {
        if (openSheet) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//            replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new ProfileFragment());
            openSheet = false;
        } else {
            replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeFragment());
            homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);

        }
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE, false);


    }


    @OnClick({R.id.fragment_my_profile_follow_me_flbtn_ly, R.id.fragment_my_profile_chat_flbtn_ly, R.id.fragment_my_profile_location_flbtn_ly, R.id.fragment_my_profile_in_other_more_flbtn_ly, R.id.fragment_my_profile_add_ads_flbtn_ly, R.id.fragment_my_profile_notifies_flbtn_ly, R.id.fragment_my_profile_edit_site_flbtn_ly, R.id.fragment_my_profile_more_flbtn_ly, R.id.fragment_my_profile_my_products_viewmore1_tv, R.id.fragment_my_profile_my_fav_viewmore2_tv, R.id.fragment_my_profile_edit_flt_btn, R.id.profile_bottom_sheet_delete_account_btn, R.id.profile_bottom_sheet_costs_list_btn, R.id.profile_bottom_sheet_report_flbtn_btn, R.id.profile_bottom_sheet_other_profile_costs_list_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_my_profile_follow_me_flbtn_ly:
                break;
            case R.id.fragment_my_profile_chat_flbtn_ly:
                break;
            case R.id.fragment_my_profile_location_flbtn_ly:
                if (isConnected(getActivity())) {
                    Intent intent = new Intent(getActivity(), MapsActivity.class);
                    startActivity(intent);
                } else {
                    try {
                        onCreateErrorToast(getActivity(), getString(R.string.error_inter_net));
                    } catch (Exception e) {

                    }

                }
                break;
            case R.id.fragment_my_profile_in_other_more_flbtn_ly:
                openSheet = true;
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.fragment_my_profile_add_ads_flbtn_ly:
                bundle=new Bundle();
                bundle.putString("ISADDSFROMHOME","profile");
                UploadAddFragment uploadAddFragment=new UploadAddFragment();
                uploadAddFragment.setArguments(bundle);
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.home_activity_fram,uploadAddFragment, "r");

                break;
            case R.id.fragment_my_profile_notifies_flbtn_ly:
                break;
            case R.id.fragment_my_profile_edit_site_flbtn_ly:
                if (isConnected(getActivity())) {
                    Intent intent = new Intent(getActivity(), MapsActivity.class);
                    startActivity(intent);
                } else {
                    try {
                        onCreateErrorToast(getActivity(), getString(R.string.error_inter_net));
                    } catch (Exception e) {

                    }

                }
                break;
            case R.id.fragment_my_profile_more_flbtn_ly:
                openSheet = true;
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.fragment_my_profile_my_products_viewmore1_tv:
                bundle=new Bundle();
                bundle.putString("ISMYPRODUCTSANDSEARCH","my");
                myProdactsAndSearchAddsFragment=new MyProdactsAndSearchAddsFragment();
                myProdactsAndSearchAddsFragment.setArguments(bundle);
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, myProdactsAndSearchAddsFragment, "t");
                break;
            case R.id.fragment_my_profile_my_fav_viewmore2_tv:
                bundle=new Bundle();
                bundle.putString("ISMYPRODUCTSANDSEARCH","my");
                myProdactsAndSearchAddsFragment=new MyProdactsAndSearchAddsFragment();
                myProdactsAndSearchAddsFragment.setArguments(bundle);
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, myProdactsAndSearchAddsFragment, "t");
                break;
            case R.id.fragment_my_profile_edit_flt_btn:
                 bundle=new Bundle();
                bundle.putString("ISSIGNUP","editProfile");
                SignUpFragment signUpFragment=new SignUpFragment();
                signUpFragment.setArguments(bundle);
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.home_activity_fram,signUpFragment, "b");

                break;
            case R.id.profile_bottom_sheet_delete_account_btn:
                break;
            case R.id.profile_bottom_sheet_costs_list_btn:
                 bundle=new Bundle();
                bundle.putString("ISMYCOSTSLIST","myCostsList");
                costsListFragment=new CostsListFragment();
                costsListFragment.setArguments(bundle);
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, costsListFragment);
                break;
            case R.id.profile_bottom_sheet_report_flbtn_btn:
                break;
            case R.id.profile_bottom_sheet_other_profile_costs_list_btn:
                 bundle=new Bundle();
                bundle.putString("ISMYCOSTSLIST","otherCostsList");
                 costsListFragment=new CostsListFragment();
                costsListFragment.setArguments(bundle);
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, costsListFragment);
                break;
        }
    }


}