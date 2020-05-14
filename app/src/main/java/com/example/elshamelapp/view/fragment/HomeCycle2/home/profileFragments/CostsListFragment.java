package com.example.elshamelapp.view.fragment.HomeCycle2.home.profileFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elshamelapp.R;
import com.example.elshamelapp.adapter.CostsListProductItemAdapter;
import com.example.elshamelapp.data.model.CostsListModel;
import com.example.elshamelapp.view.fragment.BaSeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.elshamelapp.utils.CostsListIAddAndUpdateItemDialog.showDialog;
import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;


public class CostsListFragment extends BaSeFragment {

    @BindView(R.id.costs_list_category_fragment_recycler_view)
    RecyclerView costsListCategoryFragmentRecyclerView;
    private LinearLayoutManager lLayout;
    @BindView(R.id.costs_list_fragment_back_btn)
    ImageButton costsListFragmentBackBtn;
    @BindView(R.id.costs_list_fragment_bottom_ly)
    LinearLayout costsListFragmentBottomLy;
    private String productName,productQuantity,productPrice;
    public static boolean isDialogDataAddSuccess = true;
    private List<CostsListModel> costsListItemDataList;
    private CostsListProductItemAdapter costsListProductItemAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_costs_list, container, false);

        ButterKnife.bind(this, root);
        List<CostsListModel> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getActivity());

        costsListCategoryFragmentRecyclerView.setLayoutManager(lLayout);

        costsListProductItemAdapter = new CostsListProductItemAdapter(getActivity(), rowListItem);
        costsListCategoryFragmentRecyclerView.setAdapter(costsListProductItemAdapter);

        // 5. set item animator to DefaultAnimator
        costsListCategoryFragmentRecyclerView.setItemAnimator(new DefaultItemAnimator());


        return root;
    }

    private List<CostsListModel> getAllItemList() {

         costsListItemDataList = new ArrayList<CostsListModel>();
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5"));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5"));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5"));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5"));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5"));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5"));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5"));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5"));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5"));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5"));



        return costsListItemDataList;
    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new ProfileFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,false);
//        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }

    @OnClick({R.id.costs_list_fragment_back_btn, R.id.costs_list_fragment_save_btn, R.id.costs_list_fragment_add_new_item})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.costs_list_fragment_back_btn:
                break;
            case R.id.costs_list_fragment_save_btn:
                break;
            case R.id.costs_list_fragment_add_new_item:
                Bundle bundle = this.getArguments();
                productName = bundle.getString("NAME");
                productPrice = bundle.getString("COST");
                productQuantity = bundle.getString("QUANTITY");
                isDialogDataAddSuccess = true;
                showDialog(getActivity(), getContext(), "add",null);

                if (productName != null && productPrice != null && productQuantity != null && isDialogDataAddSuccess) {

                    costsListItemDataList.add(new CostsListModel(productName,productPrice,productQuantity));
                    costsListProductItemAdapter.notifyItemInserted(costsListItemDataList.size());
                }
                break;
        }
    }
}