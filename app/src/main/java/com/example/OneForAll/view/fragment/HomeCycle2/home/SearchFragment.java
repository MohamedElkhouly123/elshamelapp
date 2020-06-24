package com.example.OneForAll.view.fragment.HomeCycle2.home;

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

import com.example.OneForAll.R;
import com.example.OneForAll.utils.ShowChooseCategories2Dialog;
import com.example.OneForAll.view.fragment.BaSeFragment;

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