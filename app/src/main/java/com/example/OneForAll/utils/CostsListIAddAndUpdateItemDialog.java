package com.example.OneForAll.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.OneForAll.R;
import com.example.OneForAll.adapter.CostsListProductItemAdapter;
import com.example.OneForAll.data.model.CostsListModel;
import com.example.OneForAll.view.fragment.HomeCycle2.home.profileFragments.CostsListFragment;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.OneForAll.utils.HelperMethod.showToast;


public class CostsListIAddAndUpdateItemDialog {

    private Activity activity;
//    private static ClientData clientData;
    static TextInputLayout costsListAddOrUpdateItemDialogProductNameTInLy,costsListAddOrUpdateItemDialogProductPriceTInLy,costsListAddOrUpdateItemDialogProductQuantityTInLy;
    static Button costsListAddOrUpdateItemDialogAddBtn;
//    private static CostsListModel costsListItemDataListOfPossision;
    private static String addOrUpdate2;
//    static AlertDialog alertDialog;
    private Context context;
//    private boolean Cancelable;
//    public static String dialogItemProductName,dialogItemProductPrice,dialogItemProductQuantity;

//    public CostsListIAddAndUpdateItemDialog(CostsListModel costsListItemDataListOfPossision) {
//        this.costsListItemDataListOfPossision = costsListItemDataListOfPossision;
//    }
public CostsListIAddAndUpdateItemDialog() {
    // Required empty public constructor
}

    public static void showDialog(@NonNull Activity activity,@NonNull Context context, @Nullable String addOrUpdate,@Nullable CostsListModel costsListItemDataListOfPossision) {
//        final RestaurantCategoryFiltterData restaurantDataListOfPossision
        try {
//            ButterKnife.bind(activity);
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.dialog_costs_list_add_item, null);
            alertDialogBuilder.setView(view);
            alertDialogBuilder.setCancelable(true);
            final AlertDialog dialog = alertDialogBuilder.create();
//            addOrUpdate2=addOrUpdate;
             costsListAddOrUpdateItemDialogProductNameTInLy = (TextInputLayout) view.findViewById(R.id.costs_list_dialog_product_name_tily);
            costsListAddOrUpdateItemDialogProductPriceTInLy = (TextInputLayout) view.findViewById(R.id.costs_list_dialog_product_cost_tily);
            costsListAddOrUpdateItemDialogProductQuantityTInLy = (TextInputLayout) view.findViewById(R.id.costs_list_dialog_product_quality_tily);
            costsListAddOrUpdateItemDialogAddBtn = (Button) view.findViewById(R.id.costs_list_dialog_add_and_update_btn);
            if (addOrUpdate.equalsIgnoreCase("update")) {
//                restaurantAddCategoryDialogAddBtn.setText(R.string.update_dialog);
                costsListAddOrUpdateItemDialogProductNameTInLy.getEditText().setText(costsListItemDataListOfPossision.getName());
                costsListAddOrUpdateItemDialogProductPriceTInLy.getEditText().setText(costsListItemDataListOfPossision.getCost());
                costsListAddOrUpdateItemDialogProductQuantityTInLy.getEditText().setText(costsListItemDataListOfPossision.getQuantity());


            }else {
//                restaurantAddCategoryDialogAddBtn.setText(R.string.add);

            }
            dialog.show();
//            clientData = LoadUserData(activity);
            costsListAddOrUpdateItemDialogAddBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (restaurantAddCategoryDialogTilCategoryName.getEditText().getText().toString()==null||mPath==null){}
                    if(costsListAddOrUpdateItemDialogProductNameTInLy.getEditText().getText().toString()!=""&&costsListAddOrUpdateItemDialogProductPriceTInLy.getEditText().getText().toString()!=""&&costsListAddOrUpdateItemDialogProductQuantityTInLy.getEditText().getText().toString()!=""){
                                                showToast(activity, String.valueOf(addOrUpdate));
//                        Call<RestaurantCategoryResponse> updateAndAddItemCal=null;
                        String  dialogItemProductName =costsListAddOrUpdateItemDialogProductNameTInLy.getEditText().getText().toString();
                        String  dialogItemProductPrice =costsListAddOrUpdateItemDialogProductPriceTInLy.getEditText().getText().toString();
                        String dialogItemProductQuantity =costsListAddOrUpdateItemDialogProductQuantityTInLy.getEditText().getText().toString();
                        if (addOrUpdate.equalsIgnoreCase("update")) {
//                            updateAndAddItemCal= getApiClient().restaurantUpdateCategory( updatedCaegoryName,updatedCategoryPhoto,updatedCategoryApiToken,updatedCategoryId);
                            CostsListProductItemAdapter costsListProductItemAdapter=new CostsListProductItemAdapter();
//                            costsListProductItemAdapter.costsListAdapterDataListOfPossision.setId(costsListItemDataListOfPossision.getId());
                            costsListProductItemAdapter.costsListAdapterDataListOfPossision.setName(costsListAddOrUpdateItemDialogProductNameTInLy.getEditText().getText().toString());
                            costsListProductItemAdapter.costsListAdapterDataListOfPossision.setCost(costsListAddOrUpdateItemDialogProductPriceTInLy.getEditText().getText().toString());
                            costsListProductItemAdapter.costsListAdapterDataListOfPossision.setQuantity(costsListAddOrUpdateItemDialogProductQuantityTInLy.getEditText().getText().toString());

//                          new CostsListProductItemAdapter(dialogItemProductName,dialogItemProductPrice,dialogItemProductQuantity);
                        }else {
//                            updateAndAddItemCal= getApiClient().restaurantNewCategory( updatedCaegoryName,updatedCategoryPhoto,updatedCategoryApiToken);
//                            new HomeContainerFragment().restaurantDataListOfPossision.setName(restaurantAddCategoryDialogTilCategoryName.getEditText().getText().toString());

                            CostsListFragment costsListFragment=new CostsListFragment();
//                            costsListFragment.setItemData(dialogItemProductName,dialogItemProductQuantity,dialogItemProductPrice);
//                            Bundle bundle=new Bundle();
//                            bundle.putString("NAME",costsListAddOrUpdateItemDialogProductNameTInLy.getEditText().getText().toString());
//                            bundle.putString("COST",costsListAddOrUpdateItemDialogProductPriceTInLy.getEditText().getText().toString());
//                            bundle.putString("QUANTITY",costsListAddOrUpdateItemDialogProductQuantityTInLy.getEditText().getText().toString());
//                            costsListFragment.setArguments(bundle);
                            int i=10;
                            costsListFragment.costsListDataListOfPossision.setId(i);
                            costsListFragment.costsListDataListOfPossision.setName(costsListAddOrUpdateItemDialogProductNameTInLy.getEditText().getText().toString());
                            costsListFragment.costsListDataListOfPossision.setCost(costsListAddOrUpdateItemDialogProductPriceTInLy.getEditText().getText().toString());
                            costsListFragment.costsListDataListOfPossision.setQuantity(costsListAddOrUpdateItemDialogProductQuantityTInLy.getEditText().getText().toString());

                             i++;
                        }
//                        deleteAndUpdateItemCallBack(activity,updateAndAddItemCal);
                        dialog.dismiss();
                }else {
                                            showToast(activity, "لابد من اكمال البيانات الفارغه اولا");
                        return;
                    }

                }
            });



        } catch (Exception e) {

        }
    }

}
