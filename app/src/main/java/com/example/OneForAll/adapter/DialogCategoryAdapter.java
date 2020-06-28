package com.example.OneForAll.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OneForAll.R;
import com.example.OneForAll.data.model.ItemObjectModel;
import com.example.OneForAll.utils.RVAdapterCallback;
import com.example.OneForAll.view.activity.HomeCycleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogCategoryAdapter extends RecyclerView.Adapter<DialogCategoryAdapter.ViewHolder> {



    private Context context;
    private Activity activity;
    private List<ItemObjectModel> itemList = new ArrayList<>();
    private RVAdapterCallback rvAdapterCallback;

//    private ClientData clientData;
//    private ApiService apiService;

    public DialogCategoryAdapter(Context context,
                                 Activity activity,
                                 List<ItemObjectModel> itemList,RVAdapterCallback callback
    ) {
        this.context = context;
        this.activity = activity;

        this.rvAdapterCallback=callback;

//        this.clientRestaurantsDataList.clear();
        this.itemList = itemList;
//        clientData = LoadUserData(activity);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custam_text_view_for_category_dialog,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
//        setAnimation(holder.itemView, position, holder);

    }

    private void setData(ViewHolder holder, int position) {
        try {
            holder.position = position;
            holder.custamTextViewForCategoryDialogNameTv.setText(itemList.get(position).getName());

        } catch (Exception e) {

        }

    }

    private void setAnimation(View viewToAnimate, int position, ViewHolder holder) {
        Animation animation = null;
        animation = AnimationUtils.loadAnimation(activity, R.anim.rv_animation_down_to_up);
        viewToAnimate.startAnimation(animation);

    }

    private void setAction(final ViewHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeCycleActivity homeCycleActivity = (HomeCycleActivity) activity;
                Toast.makeText(v.getContext(), "Clicked Country Position = " + position, Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    itemList=getAllItemList();
                    notifyDataSetChanged();
                    rvAdapterCallback.onMethodCallback(itemList.get(position).getName());
//                    replaceFragmentWithAnimation(homeCycleActivity.getSupportFragmentManager(), R.id.home_activity_fram, new SubCategoryFragment(), "t");
//                    homeCycleActivity.setNavigationAndToolBar(View.GONE, true);
                }
            }
        });

    }

    private List<ItemObjectModel> getAllItemList() {

        List<ItemObjectModel> allItems = new ArrayList<ItemObjectModel>();
        allItems.add(new ItemObjectModel("محلات ومطاعم"));
        allItems.add(new ItemObjectModel("عقارات"));
        allItems.add(new ItemObjectModel("مجوهرات"));
        allItems.add(new ItemObjectModel("الكترونيات"));
        allItems.add(new ItemObjectModel("مركبات"));
        allItems.add(new ItemObjectModel("مستلزمات منزليه"));
        allItems.add(new ItemObjectModel("ملابس واكسسورات"));
        allItems.add(new ItemObjectModel("حيوانات"));
        allItems.add(new ItemObjectModel("مستلزمات رياضيه"));
        allItems.add(new ItemObjectModel("مستلزمات فنيه وثقافيه"));
        allItems.add(new ItemObjectModel("معدات طبيه"));
        allItems.add(new ItemObjectModel("معدات صناعه"));
        allItems.add(new ItemObjectModel("مستلزمات مطاعم"));
        allItems.add(new ItemObjectModel("اخرى"));

        return allItems;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

//    @OnClick(R.id.item_client_restaurants_list_rb_rating)
//    public void onViewClicked() {
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.custam_text_view_for_category_dialog_name_tv)
        TextView custamTextViewForCategoryDialogNameTv;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
