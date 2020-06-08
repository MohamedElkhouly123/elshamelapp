package com.example.elshamelapp.view.fragment.HomeCycle2.notificationsMenues;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elshamelapp.R;
import com.example.elshamelapp.adapter.NotificationsAdapter;
import com.example.elshamelapp.data.model.ProductDataModel;
import com.example.elshamelapp.view.activity.HomeCycleActivity;
import com.example.elshamelapp.view.fragment.BaSeFragment;
import com.example.elshamelapp.view.fragment.HomeCycle2.home.HomeContainerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;

public class NotificationsFragment extends BaSeFragment {

    @BindView(R.id.notification_fragment_recycler_view)
    RecyclerView notificationFragmentRecyclerView;
    private LinearLayoutManager lLayout;
    private List<ProductDataModel> rowListItem;
    public NotificationsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        ButterKnife.bind(this, root);
        homeCycleActivity = (HomeCycleActivity) getActivity();
        homeCycleActivity.setToolBar(View.VISIBLE, getString(R.string.notify)
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBack();
                    }
                });
        rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getActivity());

        notificationFragmentRecyclerView.setLayoutManager(lLayout);

        NotificationsAdapter rcAdapter = new NotificationsAdapter(getContext(), getActivity(), rowListItem);
        notificationFragmentRecyclerView.setAdapter(rcAdapter);

        // 5. set item animator to DefaultAnimator
        notificationFragmentRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;
    }

    private List<ProductDataModel> getAllItemList() {

        rowListItem = new ArrayList<ProductDataModel>();
        rowListItem.add(new ProductDataModel("يوجد رساله جديده لديك", "55 ج", R.drawable.flat));
        rowListItem.add(new ProductDataModel("لقد رفع فلان تتابعه منتج جديد", "55 ج", R.drawable.chale));
        rowListItem.add(new ProductDataModel("يوجد رساله جديده لديك", "55 ج", R.drawable.flat));
        rowListItem.add(new ProductDataModel("لقد رفع فلان تتابعه منتج جديد", "55 ج", R.drawable.chale));
        rowListItem.add(new ProductDataModel("يوجد رساله جديده لديك", "55 ج", R.drawable.flat));
        rowListItem.add(new ProductDataModel("لقد رفع فلان تتابعه منتج جديد", "55 ج", R.drawable.chale));
        rowListItem.add(new ProductDataModel("يوجد رساله جديده لديك", "55 ج", R.drawable.flat));
        rowListItem.add(new ProductDataModel("لقد رفع فلان تتابعه منتج جديد", "55 ج", R.drawable.chale));
        rowListItem.add(new ProductDataModel("يوجد رساله جديده لديك", "55 ج", R.drawable.flat));
        rowListItem.add(new ProductDataModel("لقد رفع فلان تتابعه منتج جديد", "55 ج", R.drawable.chale));
        rowListItem.add(new ProductDataModel("يوجد رساله جديده لديك", "55 ج", R.drawable.flat));
        rowListItem.add(new ProductDataModel("لقد رفع فلان تتابعه منتج جديد", "55 ج", R.drawable.chale));

        return rowListItem;
    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeContainerFragment());
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }
}