package com.example.elshamelapp.view.fragment.HomeCycle2.home.profileFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.elshamelapp.R;
import com.example.elshamelapp.view.activity.MapsActivity;
import com.example.elshamelapp.view.fragment.BaSeFragment;
import com.example.elshamelapp.view.fragment.HomeCycle2.home.HomeFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;
import static com.example.elshamelapp.utils.ToastCreator.onCreateErrorToast;
import static com.example.elshamelapp.utils.network.InternetState.isConnected;


public class ProfileFragment extends BaSeFragment {

    // BottomSheetBehavior variable
    private BottomSheetBehavior bottomSheetBehavior;

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

        View root = inflater.inflate(R.layout.bottom_sheets_abb_bar, container, false);

        ButterKnife.bind(this, root);

        bottomSheetBehavior = BottomSheetBehavior.from(root.findViewById(R.id.bottom1));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        //  go = (Button) findViewById(R.id.go);

        return root;
    }


    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE, false);
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);


    }

    @OnClick({R.id.follow_me, R.id.chat, R.id.location, R.id.report, R.id.add, R.id.notification, R.id.my_profile_fragment_location_map_fbtn, R.id.more, R.id.edit_floating_action_button,R.id.profile_bottom_sheet_costs_list_btn,R.id.profile_bottom_sheet_delete_account_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.follow_me:
                break;
            case R.id.chat:
                break;
            case R.id.location:
                break;
            case R.id.report:
                break;
            case R.id.add:
                break;
            case R.id.notification:
                break;
            case R.id.my_profile_fragment_location_map_fbtn:
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
            case R.id.more:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                break;
            case R.id.edit_floating_action_button:
                break;
            case R.id.profile_bottom_sheet_delete_account_btn:
                break;
            case R.id.profile_bottom_sheet_costs_list_btn:
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new CostsListFragment());

                break;
        }
    }


}