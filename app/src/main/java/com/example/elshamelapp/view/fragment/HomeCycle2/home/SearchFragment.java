package com.example.elshamelapp.view.fragment.HomeCycle2.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.example.elshamelapp.R;
import com.example.elshamelapp.view.fragment.BaSeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;


public class SearchFragment extends BaSeFragment {
    private static final String[] COUNTRIES = new String[]{
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola"
    };
    @BindView(R.id.search_edit_text)
    AutoCompleteTextView searchEditText;
    @BindView(R.id.back_btn)
    ImageButton backBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);

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

            }
        });

        return root;
    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,false);
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }

    @OnClick(R.id.back_btn)
    public void onViewClicked() {
    }
}