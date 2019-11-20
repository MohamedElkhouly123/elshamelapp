package com.example.elshamelapp.view.categories;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.elshamelapp.R;

public class CategoryRecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

public TextView countryName;
public ImageView countryPhoto;

public CategoryRecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        countryName = (TextView)itemView.findViewById(R.id.country_name);
        countryPhoto = (ImageView)itemView.findViewById(R.id.country_photo);
        }

@Override
public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        }
        }