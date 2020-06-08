package com.example.elshamelapp.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.elshamelapp.R;
import com.example.elshamelapp.view.activity.HomeCycleActivity;
import com.example.elshamelapp.view.fragment.HomeCycle2.category.SubCategory2ThFragment;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragmentWithAnimation;


public class CategoryRecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

public TextView countryName;
public ImageView countryPhoto;
//public TextView paddingTV;
private Activity activity;
public CategoryRecyclerViewHolders(View itemView, Activity activity) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.activity=activity;
        countryName = (TextView)itemView.findViewById(R.id.country_name);
//        paddingTV = (TextView)itemView.findViewById(R.id.cardview_category_item_padding_tv);
        countryPhoto = (ImageView)itemView.findViewById(R.id.country_photo);
        }

@Override
public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        if(getPosition()==0){
                HomeCycleActivity homeCycleActivity = (HomeCycleActivity) activity;

                replaceFragmentWithAnimation(homeCycleActivity.getSupportFragmentManager(), R.id.home_activity_fram, new SubCategory2ThFragment(), "t");
//                homeCycleActivity.setNavigationAndToolBar(View.GONE,"t");
        }
        }
        }