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
import static com.example.elshamelapp.utils.HelperMethod.showToast;


public class CostsListFragment extends BaSeFragment {

    @BindView(R.id.costs_list_category_fragment_recycler_view)
    RecyclerView costsListCategoryFragmentRecyclerView;
    private LinearLayoutManager lLayout;
    @BindView(R.id.costs_list_fragment_back_btn)
    ImageButton costsListFragmentBackBtn;
    @BindView(R.id.costs_list_fragment_bottom_ly)
    LinearLayout costsListFragmentBottomLy;
    public CostsListModel costsListDataListOfPossision = new CostsListModel();
    public static boolean isDialogDataAddSuccess = true;
    private List<CostsListModel> costsListItemDataList;
    private CostsListProductItemAdapter costsListProductItemAdapter;

//    public CostsListFragment(String myCostsListOrOther) {
//        this.myCostsListOrOther = myCostsListOrOther;
//    }

    private String myCostsListOrOther = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if(this.getArguments()!=null)
        {
            myCostsListOrOther = this.getArguments().getString("ISMYCOSTSLIST");

        }
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
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5",0));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5",1));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5",2));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5",3));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5",4));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5",5));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5",6));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5",7));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5",8));
        costsListItemDataList.add(new CostsListModel("سكر", "55 ج","5",9));



        return costsListItemDataList;
    }

//    public void setItemData(String productName, String productQuantity, String productPrice) {
//        this.productName = productName;
//        this.productQuantity = productQuantity;
//        this.productPrice = productPrice;
//    }

    @Override
    public void onBack() {
        Bundle bundle=new Bundle();
        bundle.putString("ISMYPROFILE","myProfile");
        ProfileFragment profileFragment=new ProfileFragment();
        profileFragment.setArguments(bundle);
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, profileFragment);
          }

    @OnClick({R.id.costs_list_fragment_back_btn, R.id.costs_list_fragment_save_btn, R.id.costs_list_fragment_add_new_item})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.costs_list_fragment_back_btn:
                break;
            case R.id.costs_list_fragment_save_btn:
                break;
            case R.id.costs_list_fragment_add_new_item:

                isDialogDataAddSuccess = true;
                showDialog(getActivity(), getContext(), "add",null);


                if (costsListDataListOfPossision.getName() != null && costsListDataListOfPossision.getCost() != null && costsListDataListOfPossision.getQuantity() != null && isDialogDataAddSuccess) {
                    showToast(getActivity(), costsListDataListOfPossision.getName());
                    costsListItemDataList.add(costsListDataListOfPossision);
                    costsListProductItemAdapter.notifyItemInserted(costsListItemDataList.size());
                }
                break;
        }
    }
}