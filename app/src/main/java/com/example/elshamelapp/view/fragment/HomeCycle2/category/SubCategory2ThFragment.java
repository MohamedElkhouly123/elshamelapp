package com.example.elshamelapp.view.fragment.HomeCycle2.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.elshamelapp.R;
import com.example.elshamelapp.adapter.Category2ThAdapter;
import com.example.elshamelapp.data.model.ItemObjectModel;
import com.example.elshamelapp.view.activity.HomeCycleActivity;
import com.example.elshamelapp.view.fragment.BaSeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;

public class SubCategory2ThFragment extends BaSeFragment {

    private LinearLayoutManager lLayout;
    @BindView(R.id.sub_category2_fragment_recycler_view)
    RecyclerView rView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sub2_categories, container, false);
        ButterKnife.bind(this, root);
        homeCycleActivity= (HomeCycleActivity) getActivity();
        homeCycleActivity.setToolBar(View.VISIBLE, getString(R.string.the_sub_categories)
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBack();
                    }
                });
        List<ItemObjectModel> rowListItem = getAllItemList();
//        lLayout = new LinearLayoutManager(getActivity());
        rView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

//        rView.setLayoutManager(lLayout);

        Category2ThAdapter rcAdapter = new Category2ThAdapter(getContext(),getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);

        // 5. set item animator to DefaultAnimator
        rView.setItemAnimator(new DefaultItemAnimator());
        toolHidden();

        return root;
    }
    private List<ItemObjectModel> getAllItemList(){

        List<ItemObjectModel> allItems = new ArrayList<ItemObjectModel>();
        allItems.add(new ItemObjectModel("محلات ومطاعم"));
        allItems.add(new ItemObjectModel("عقارات"));
        allItems.add(new ItemObjectModel("مجوهرات"));
        allItems.add(new ItemObjectModel("الكترونيات"));
        allItems.add(new ItemObjectModel("مركبات"));
        allItems.add(new ItemObjectModel("مستلزمات منزليه"));
        allItems.add(new ItemObjectModel("ملابس واكسسورات"));
        allItems.add(new ItemObjectModel("حيوانات"));
        allItems.add(new ItemObjectModel("مستلزمات رياضيه"));
        allItems.add(new ItemObjectModel("مستلزمات فنيه وثقافيه"));
        allItems.add(new ItemObjectModel("معدات طبيه"));
        allItems.add(new ItemObjectModel("معدات صناعه"));
        allItems.add(new ItemObjectModel("مستلزمات مطاعم"));
        allItems.add(new ItemObjectModel("اخرى"));

        return allItems;
    }

    private void toolHidden() {
        rView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && homeCycleActivity.toolBarLay.getVisibility() == View.VISIBLE) {
                    homeCycleActivity.toolBarLay.setVisibility(View.GONE);
                } else if (dy < 0 && homeCycleActivity.toolBarLay.getVisibility() != View.VISIBLE) {
                    homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new SubCategoryFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,"t");
//        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }
}