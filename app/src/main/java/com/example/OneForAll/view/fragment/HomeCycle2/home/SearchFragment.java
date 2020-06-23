package com.example.OneForAll.view.fragment.HomeCycle2.home;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OneForAll.R;
import com.example.OneForAll.adapter.DialogCategoryAdapter;
import com.example.OneForAll.data.model.ItemObjectModel;
import com.example.OneForAll.utils.ShowChooseCategories2Dialog;
import com.example.OneForAll.view.fragment.BaSeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.OneForAll.utils.HelperMethod.replaceFragment;


public class SearchFragment extends BaSeFragment {
    private static final String[] COUNTRIES = new String[]{
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola"
    };
    @BindView(R.id.search_edit_text)
    AutoCompleteTextView searchEditText;
    @BindView(R.id.back_btn)
    ImageButton backBtn;
    @BindView(R.id.fragment_uplood_product_category_et)
    EditText fragmentUploodProductCategoryEt;

    private LinearLayoutManager lLayout;
    View root;

    public SearchFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_search, container, false);

        ButterKnife.bind(this, root);
        String[] countries = getResources().getStringArray(R.array.countries);

//        AutoCompleteTextView editText = (AutoCompleteTextView) root.findViewById(R.id.search_edit_text);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.custam_text_view, R.id.text_view_list_item, countries);
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,countries);

        searchEditText.setAdapter(adapter);
        //editText.setThreshold(1);

        //get the input like for a normal EditText
        //String input = editText.getText().toString();
        searchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
//                                             Your code .........

            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(getContext(), "text changed", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        return root;
    }

    private void showChooseCategoriesDialog() {
        try {
            Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT)
                    .show();
            List<ItemObjectModel> dialogCategoryListItem = getAllItemList();

//            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_categories_rv, null);
//            alertDialog = new AlertDialog.Builder(HomeContainerFragment.this).create();
            AlertDialog.Builder builder = new AlertDialog
                    .Builder(new ContextThemeWrapper(getActivity(), R.style.Theme_AppCompat_Dialog));
            AlertDialog alertDialog;
            builder.setView(view);
            builder.setTitle("أختيار القسم العام");
            builder.setCancelable(false);
//            alertDialog.setMessage("أختار القسم العام");


            RecyclerView dialogCategoriesRvRecyclerView = (RecyclerView) root.findViewById(R.id.dialog_categories_rv_recycler_view);

            lLayout = new LinearLayoutManager(getActivity());

            dialogCategoriesRvRecyclerView.setLayoutManager(lLayout);

            DialogCategoryAdapter rcAdapter = new DialogCategoryAdapter(getContext(), getActivity(), dialogCategoryListItem);
            dialogCategoriesRvRecyclerView.setAdapter(rcAdapter);
            rcAdapter.notifyDataSetChanged();
            alertDialog = builder.create();


//            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "تراجع", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
////                        Call<RestaurantCategoryResponse> deletItemCal = getApiClient().restaurantDeleteCategory(clientData.getApiToken(), restaurantDataList.get(position).getId());
////                        deleteAndUpdateItemCallBack(activity, deletItemCal);
////                    costsListDataList.remove(position);
////                    notifyItemRemoved(position);
////                    notifyItemRangeChanged(position, costsListDataList.size());
//
//                }
//            });


            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "الغاء", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });

            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onShow(DialogInterface arg0) {

//                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.blue);
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.blue);

                }
            });
//            builder.show();

            alertDialog.show();


//            dialogCategoriesRvRecyclerView.setHasFixedSize(true);

        } catch (Exception e) {

        }
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


        return allItems;
    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeContainerFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,false);
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }


    @OnClick({R.id.fragment_uplood_product_category_et, R.id.fragment_uplood_product_category_tin, R.id.back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_uplood_product_category_et:
                final ShowChooseCategories2Dialog dialog =new ShowChooseCategories2Dialog();
                dialog.show(getActivity().getSupportFragmentManager(), "example");
                fragmentUploodProductCategoryEt.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_black_24dp, 0);
//                showChooseCategoriesDialog();
                break;
            case R.id.fragment_uplood_product_category_tin:

                break;
            case R.id.back_btn:
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeContainerFragment());
                homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
                break;
        }
    }
}