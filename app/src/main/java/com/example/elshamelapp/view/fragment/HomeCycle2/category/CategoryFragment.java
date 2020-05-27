package com.example.elshamelapp.view.fragment.HomeCycle2.category;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elshamelapp.R;
import com.example.elshamelapp.adapter.CategoryAdapter;
import com.example.elshamelapp.data.model.ItemObjectModel;
import com.example.elshamelapp.view.activity.HomeCycleActivity;
import com.example.elshamelapp.view.fragment.BaSeFragment;
import com.example.elshamelapp.view.fragment.HomeCycle2.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;

public class CategoryFragment extends BaSeFragment {

    @BindView(R.id.category_fragment_make_full_screen_floating_action_btn)
    FloatingActionButton categoryFragmentMakeFullScreenFloatingActionBtn;
    //    @BindView(R.id.tool_text_hide)
//    TextView toolTextHide;
    private LinearLayoutManager lLayout;
    @BindView(R.id.category_fragment_recycler_view)
    RecyclerView rView;
    int heightDelta = 0;
    private boolean firstPress =true;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, root);
        homeCycleActivity = (HomeCycleActivity) getActivity();
        homeCycleActivity.setToolBar(View.VISIBLE, getString(R.string.the_categories)
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBack();
                    }
                });
        List<ItemObjectModel> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getActivity());

        rView.setLayoutManager(lLayout);

        CategoryAdapter rcAdapter = new CategoryAdapter(getContext(), getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);

        // 5. set item animator to DefaultAnimator
        rView.setItemAnimator(new DefaultItemAnimator());

        toolHidden();

        return root;
    }

    private List<ItemObjectModel> getAllItemList() {

        List<ItemObjectModel> allItems = new ArrayList<ItemObjectModel>();
        allItems.add(new ItemObjectModel("مفقوداتى", R.drawable.cars));
//        allItems.add(new ItemObjectModel("مشاكل مصر", R.drawable.chale));
        allItems.add(new ItemObjectModel("مشترواتى", R.drawable.dimond));
        allItems.add(new ItemObjectModel("سوق الجمله", R.drawable.chale));// المصانع والشركات
        allItems.add(new ItemObjectModel("الحجز والايجار", R.drawable.gold));
        allItems.add(new ItemObjectModel("مزاداتى", R.drawable.flat));
        allItems.add(new ItemObjectModel("الوظائف والخدمات", R.drawable.flat));
//        allItems.add(new ItemObjectModel("الصناعه والتجاره", R.drawable.flat));


        return allItems;
    }

    private void toolHidden() {
        rView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                heightDelta += dy;

                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && categoryFragmentMakeFullScreenFloatingActionBtn.getVisibility() == View.VISIBLE) {
                    categoryFragmentMakeFullScreenFloatingActionBtn.hide();

//                    homeCycleActivity.toolBarLay.animate().translationY(-heightDelta).setInterpolator(new AccelerateInterpolator()).start();
//                    homeCycleActivity.toolBarLay.setVisibility(View.GONE);

                } else if (dy < 0 && categoryFragmentMakeFullScreenFloatingActionBtn.getVisibility() != View.VISIBLE) {
                    categoryFragmentMakeFullScreenFloatingActionBtn.show();
//                    homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);
//                    homeCycleActivity.toolBarLay.animate().translationY(heightDelta).setInterpolator(new AccelerateInterpolator()).start();

                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
//        homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);
//        homeCycleActivity.toolBarLay.animate().translationY(heightDelta).setInterpolator(new AccelerateInterpolator()).start();
        categoryFragmentMakeFullScreenFloatingActionBtn.show();

    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeFragment());
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);

    }

    @SuppressLint("RestrictedApi")
    @OnClick(R.id.category_fragment_make_full_screen_floating_action_btn)
    public void onViewClicked() {
        if(firstPress){
            categoryFragmentMakeFullScreenFloatingActionBtn.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fullscreen_black_24dp));
            homeCycleActivity.toolBarLay.setVisibility(View.GONE);
//            categoryFragmentMakeFullScreenFloatingActionBtn.setImageResource(R.drawable.ic_fullscreen_black_24dp);

            firstPress=false;
        }else {
            categoryFragmentMakeFullScreenFloatingActionBtn.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fullscreen_exit_black_24dp));
            homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);
//            categoryFragmentMakeFullScreenFloatingActionBtn.setImageResource(R.drawable.ic_fullscreen_exit_black_24dp);

            firstPress=true;
        }
        categoryFragmentMakeFullScreenFloatingActionBtn.hide();
        categoryFragmentMakeFullScreenFloatingActionBtn.show();
    }
}