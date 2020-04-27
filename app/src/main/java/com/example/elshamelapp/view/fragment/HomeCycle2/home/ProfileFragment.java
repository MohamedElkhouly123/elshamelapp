package com.example.elshamelapp.view.fragment.HomeCycle2.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.elshamelapp.R;
import com.example.elshamelapp.view.fragment.BaSeFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;


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
        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE, "t");
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);


    }

    @OnClick({R.id.follow_me, R.id.chat, R.id.location, R.id.report, R.id.add, R.id.notification, R.id.location_map, R.id.more, R.id.edit_floating_action_button})
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
            case R.id.location_map:
                break;
            case R.id.more:
               bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                break;
            case R.id.edit_floating_action_button:
                break;
        }
    }
}