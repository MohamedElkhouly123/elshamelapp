package com.example.elshamelapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.elshamelapp.R;
import com.example.elshamelapp.data.model.CostsListModel;
import com.example.elshamelapp.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.elshamelapp.utils.CostsListIAddAndUpdateItemDialog.showDialog;


public class CostsListProductItemAdapter extends RecyclerView.Adapter<CostsListProductItemAdapter.ViewHolder> {


    private Context context;
    private BaseActivity activity;
    private List<CostsListModel> costsListDataList = new ArrayList<>();
    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
//    private ClientData clientData;
    private String lang;
    private int lastPosition = -1;
    private static final String CLIENTPROFILEIMAGE ="CLIENTPROFILEIMAGE" ;
    private   String dialogCostsListName,dialogCostsListPrice,dialogCostsListQuantity;
    public CostsListModel costsListAdapterDataListOfPossision = new CostsListModel();

    private ViewHolder holder;
    public CostsListProductItemAdapter(Activity activity, List<CostsListModel> costsListDataList) {
        this.context = activity;
        this.activity = (BaseActivity) activity;
        this.costsListDataList.clear();
        this.costsListDataList = costsListDataList;
        viewBinderHelper.setOpenOnlyOne(true);
//        clientData = LoadUserData(activity);
        lang = "eg";
    }
public CostsListProductItemAdapter(){

}
//    public CostsListProductItemAdapter(String dialogCostsListName,String dialogCostsListPrice,String dialogCostsListQuantity) {
//        this.dialogCostsListName = dialogCostsListName;
//        this.dialogCostsListPrice = dialogCostsListPrice;
//        this.dialogCostsListQuantity = dialogCostsListQuantity;
//        viewBinderHelper.setOpenOnlyOne(true);
//        lang = "eg";
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_item_costs_list,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        viewBinderHelper.setOpenOnlyOne(true);
        setData(holder, position);
        setSwipe(holder, position);
        setAction(holder, position);
        setAnimation(holder.itemView, position, holder);
        this.holder=holder;
    }

    @SuppressLint("SetTextI18n")
    private void setData(ViewHolder holder, int position) {


        try {
            holder.position = position;
            holder.itemCostsListTvProductName.setText(costsListDataList.get(position).getName());
            holder.itemCostsListTvProductPrice.setText(costsListDataList.get(position).getCost());
            holder.itemCostsListTvProductQuantity.setText(costsListDataList.get(position).getQuantity());



        } catch (Exception e) {

        }


    }

    private void setAnimation(View viewToAnimate, int position, ViewHolder holder) {
            Animation animation = null;
            animation = AnimationUtils.loadAnimation(activity, R.anim.rv_animation_down_to_up);
            viewToAnimate.startAnimation(animation);

    }

    private void setAction(ViewHolder holder, int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setSwipe(final ViewHolder holder, final int position) {
        holder.itemCostsListSwipeLayout.computeScroll();
        if (lang.equals("ar")) {
            holder.itemCostsListSwipeLayout.setDragEdge(SwipeRevealLayout.DRAG_EDGE_LEFT);
        } else {
            holder.itemCostsListSwipeLayout.setDragEdge(SwipeRevealLayout.DRAG_EDGE_RIGHT);
        }

        viewBinderHelper.bind(holder.itemCostsListSwipeLayout, String.valueOf(costsListDataList.get(position).getId()));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewBinderHelper.openLayout(String.valueOf(costsListDataList.get(position).getId()));
                holder.itemCostsListSwipeLayout.computeScroll();
            }
        });

    }

    @Override
    public int getItemCount() {
        return costsListDataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.card_view_costs_list_item_product_name_tv)
        TextView itemCostsListTvProductName;
        @BindView(R.id.card_view_costs_list_item_product_price_tv)
        TextView itemCostsListTvProductPrice;
        @BindView(R.id.card_view_costs_list_item_product_quantity_tv)
        TextView itemCostsListTvProductQuantity;
        @BindView(R.id.card_view_costs_list_item__swipe_layout)
        SwipeRevealLayout itemCostsListSwipeLayout;
        private View view;
        private int position;

        private ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }


        @OnClick({R.id.card_view_costs_list_item_edit_floating_btn, R.id.card_view_costs_list_item_item_delete_floating_btn,R.id.card_view_costs_list_item_drag_btn})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.card_view_costs_list_item_edit_floating_btn:
                  boolean  isDialogDataAddSuccess = true; // here not true
//                    CostsListIAddAndUpdateItemDialog costsListIAddAndUpdateItemDialog = new CostsListIAddAndUpdateItemDialog();
//                    costsListIAddAndUpdateItemDialog.costsListItemDataListOfPossision = costsListDataList.get(position);
                    showDialog(activity, context, "update",costsListDataList.get(position));
//                    if (dialogCostsListName != null && dialogCostsListPrice != null && dialogCostsListQuantity != null && isDialogDataAddSuccess) {
//                        showToast(activity, dialogCostsListName + "\n" + dialogCostsListPrice);
                        costsListDataList.get(position).setName(costsListAdapterDataListOfPossision.getName());
                        costsListDataList.get(position).setCost(costsListAdapterDataListOfPossision.getCost());
                        costsListDataList.get(position).setQuantity(costsListAdapterDataListOfPossision.getQuantity());
                        notifyItemChanged(position);
//                    }
                    break;
                case R.id.card_view_costs_list_item_item_delete_floating_btn:
                    showDeleteDialog();
//                    Bundle args = new Bundle();
//                    args.putSerializable("ARG_PARAM1", yourObject);
// recieve
//                    yourObj = (Match) getArguments().getSerializable(ARG_PARAM1);

                    break;
                case R.id.card_view_costs_list_item_drag_btn:
//                    viewBinderHelper.bind(holder.itemCostsListSwipeLayout, String.valueOf(costsListDataList.get(position).getId()));
//                    viewBinderHelper.openLayout(String.valueOf(costsListDataList.get(position).getId()));
//                    itemCostsListSwipeLayout.onS();
                    break;
            }
        }

//                case R.id.restaurant_add_category_dialog_img_add_photo:
////                    openGallery(activity);
//                    openGalleryِAlpom(context, alpom, new Action<ArrayList<AlbumFile>>() {
//                        @Override
//                        public void onAction(@NonNull ArrayList<AlbumFile> result) {
//                            mPath=result.get(0).getPath();
//                        }
//                    }, 1);
//
//                    break;
//                case R.id.restaurant_add_category_dialog_add_and_update_btn:
//                    RequestBody updatedCaegoryName=convertToRequestBody(restaurantAddCategoryDialogTilCategoryName.getEditText().getText().toString());
//                    RequestBody updatedCategoryApiToken=convertToRequestBody(clientData.getApiToken());
//                    RequestBody updatedCategoryId= convertToRequestBody(String.valueOf(restaurantDataList.get(position).getId()));
//                    MultipartBody.Part updatedCategoryPhoto=convertFileToMultipart(mPath,CLIENTPROFILEIMAGE);
//                    Call<RestaurantCategoryResponse> updateItemCal = getApiClient().restaurantUpdateCategory( updatedCaegoryName,updatedCategoryPhoto,updatedCategoryApiToken,updatedCategoryId);
//                    deleteAndUpdateItemCallBack(activity,updateItemCal);
//                    restaurantDataList.get(position).setName(restaurantAddCategoryDialogTilCategoryName.getEditText().getText().toString());
//                    restaurantDataList.get(position).setPhotoUrl(mPath);
//                    notifyItemChanged(position);
//                    break;



        private void showDeleteDialog(){
            try {
//                final View view = activity.getLayoutInflater().inflate(R.layout.dialog_restaurant_add_category, null);
//            alertDialog = new AlertDialog.Builder(HomeContainerFragment.this).create();
                AlertDialog alertDialog;
                 alertDialog = new AlertDialog.Builder(activity).create();
                alertDialog.setTitle("Delete");
                alertDialog.setMessage("هل انت متاكد من الحذف ؟");
                alertDialog.setCancelable(false);

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "تاكيد", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Call<RestaurantCategoryResponse> deletItemCal = getApiClient().restaurantDeleteCategory(clientData.getApiToken(), restaurantDataList.get(position).getId());
//                        deleteAndUpdateItemCallBack(activity, deletItemCal);
                        costsListDataList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, costsListDataList.size());

                    }
                });


                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "الغاء", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss() ;
                    }
                });

//                alertDialog.setView(view);
                alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onShow(DialogInterface arg0) {
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.blue);
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.blue);

                    }
                });

                                alertDialog.show();

            } catch (Exception e) {

            }
        }



    }
}

//    public void removeItem(int position){
//        mData.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, mData.size());
//    }
//    and then I would add the item at that particular position as shown below:
//
//public void addItem(int position, Landscape landscape){
//        mData.add(position, landscape);
//        notifyItemInserted(position);
//        notifyItemRangeChanged(position, mData.size());
//        }