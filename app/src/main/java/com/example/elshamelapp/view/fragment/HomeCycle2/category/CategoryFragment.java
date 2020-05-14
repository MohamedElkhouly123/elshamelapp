package com.example.elshamelapp.view.fragment.HomeCycle2.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elshamelapp.R;
import com.example.elshamelapp.adapter.CategoryAdapter;
import com.example.elshamelapp.data.model.ItemObjectModel;
import com.example.elshamelapp.view.fragment.BaSeFragment;
import com.example.elshamelapp.view.fragment.HomeCycle2.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;

public class CategoryFragment extends BaSeFragment {

    private LinearLayoutManager lLayout;
    @BindView(R.id.category_fragment_recycler_view)
    RecyclerView rView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, root);
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

        CategoryAdapter rcAdapter = new CategoryAdapter(getContext(),getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);

        // 5. set item animator to DefaultAnimator
        rView.setItemAnimator(new DefaultItemAnimator());


        return root;
    }
    private List<ItemObjectModel> getAllItemList(){

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

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeFragment());
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);

    }
}