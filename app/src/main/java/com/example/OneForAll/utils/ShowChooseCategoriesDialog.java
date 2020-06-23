package com.example.OneForAll.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OneForAll.R;
import com.example.OneForAll.adapter.DialogCategoryAdapter;
import com.example.OneForAll.data.model.ItemObjectModel;

import java.util.ArrayList;
import java.util.List;

public class ShowChooseCategoriesDialog extends DialogFragment {

    private LinearLayoutManager lLayout;
    private TextView cancelBtn ,backBtn;

//    String[] players={"Lionel Messi","Christiano Ronaldo","Neymar","Gareth Bale"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View rootView=inflater.inflate(R.layout.dialog_categories_rv, null);

        //SET TITLE DIALOG TITLE
//        getDialog().setTitle("Best Players In The World");
        List<ItemObjectModel> dialogCategoryListItem = getAllItemList();
//        this.getDialog().setTitle("أختيار القسم العام");
//        this.getDialog().setCancelable(true);

        cancelBtn=(TextView) rootView.findViewById(R.id.dialog_categories_rv_cancel_btn_tv);
        backBtn=(TextView) rootView.findViewById(R.id.dialog_categories_rv_back_btn_tv);

        RecyclerView dialogCategoriesRvRecyclerView = (RecyclerView) rootView.findViewById(R.id.dialog_categories_rv_recycler_view);

        lLayout = new LinearLayoutManager(getActivity());

        dialogCategoriesRvRecyclerView.setLayoutManager(lLayout);

        DialogCategoryAdapter rcAdapter = new DialogCategoryAdapter(getContext(), getActivity(), dialogCategoryListItem);
        dialogCategoriesRvRecyclerView.setAdapter(rcAdapter);
        rcAdapter.notifyDataSetChanged();

        //BUTTON
        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                dismiss();
            }
        });

        return rootView;

    }
    private List<ItemObjectModel> getAllItemList() {

        List<ItemObjectModel> allItems = new ArrayList<ItemObjectModel>();
        allItems.add(new ItemObjectModel("مفقوداتى", R.drawable.category_my_losts2)); // فاقد شىء ولا واجد شىء
//        allItems.add(new ItemObjectModel("مشاكل مصر", R.drawable.chale));
        allItems.add(new ItemObjectModel("مشترواتى", R.drawable.category_my_shoping2));
        allItems.add(new ItemObjectModel("سوق الجمله", R.drawable.category_souq_elgomlah1));//   المصانع والشركات والتجار
        allItems.add(new ItemObjectModel("الحجز والايجار", R.drawable.category_egar1));
        allItems.add(new ItemObjectModel("مزاداتى", R.drawable.category_mazad4));
        allItems.add(new ItemObjectModel("الوظائف والخدمات", R.drawable.category_wazaaf4)); // ابحث عن عمل ولا ابحث عن موظفين

        allItems.add(new ItemObjectModel("مفقوداتى", R.drawable.category_my_losts2)); // فاقد شىء ولا واجد شىء
//        allItems.add(new ItemObjectModel("مشاكل مصر", R.drawable.chale));
        allItems.add(new ItemObjectModel("مشترواتى", R.drawable.category_my_shoping2));
        allItems.add(new ItemObjectModel("سوق الجمله", R.drawable.category_souq_elgomlah1));//   المصانع والشركات والتجار
        allItems.add(new ItemObjectModel("الحجز والايجار", R.drawable.category_egar1));
        allItems.add(new ItemObjectModel("مزاداتى", R.drawable.category_mazad4));
        allItems.add(new ItemObjectModel("الوظائف والخدمات", R.drawable.category_wazaaf4)); // ابحث عن عمل ولا ابحث عن موظفين

        return allItems;
    }
}