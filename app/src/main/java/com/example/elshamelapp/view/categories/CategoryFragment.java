package com.example.elshamelapp.view.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elshamelapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends Fragment {

    private LinearLayoutManager lLayout;
    @BindView(R.id.recycler_view)
    RecyclerView rView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, root);

        List<ItemObjectModel> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getActivity());

        rView.setLayoutManager(lLayout);

        CategoriesAdapter rcAdapter = new CategoriesAdapter(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);

        // 5. set item animator to DefaultAnimator
        rView.setItemAnimator(new DefaultItemAnimator());


        return root;
    }
    private List<ItemObjectModel> getAllItemList(){

        List<ItemObjectModel> allItems = new ArrayList<ItemObjectModel>();
        allItems.add(new ItemObjectModel("United States", R.drawable.cars));
        allItems.add(new ItemObjectModel("Canada", R.drawable.chale));
        allItems.add(new ItemObjectModel("مركبات", R.drawable.dimond));
        allItems.add(new ItemObjectModel("Germany", R.drawable.flat));
        allItems.add(new ItemObjectModel("Sweden", R.drawable.gold));

        return allItems;
    }
}