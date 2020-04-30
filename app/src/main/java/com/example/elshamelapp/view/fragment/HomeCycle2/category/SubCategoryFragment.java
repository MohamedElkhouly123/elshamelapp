package com.example.elshamelapp.view.fragment.HomeCycle2.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.elshamelapp.R;
import com.example.elshamelapp.adapter.CategoriesAdapter;
import com.example.elshamelapp.data.model.ItemObjectModel;
import com.example.elshamelapp.view.fragment.BaSeFragment;
import com.example.elshamelapp.view.fragment.HomeCycle2.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;

public class SubCategoryFragment extends BaSeFragment {

    private LinearLayoutManager lLayout;
    @BindView(R.id.recycler_view)
    RecyclerView rView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sub_categories, container, false);
        ButterKnife.bind(this, root);

        List<ItemObjectModel> rowListItem = getAllItemList();
//        lLayout = new LinearLayoutManager(getActivity());
        rView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

//        rView.setLayoutManager(lLayout);

        CategoriesAdapter rcAdapter = new CategoriesAdapter(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);

        // 5. set item animator to DefaultAnimator
        rView.setItemAnimator(new DefaultItemAnimator());


        return root;
    }
    private List<ItemObjectModel> getAllItemList(){

        List<ItemObjectModel> allItems = new ArrayList<ItemObjectModel>();
        allItems.add(new ItemObjectModel("عقارات", R.drawable.chale)); //دليل محلاتى
        // محلات <== بقاله وسوبرماركتات ومولات وحدايدوبوهيات ولوازم كهربه ولوازم سباكه وزجاج  ومستحضرات تجميل ومستلزمات طبيه وفاكهه وخضروات وبيع الات موسيقيه
        // وملابس (اطفالى ونسائى ورجالى ورياضيه وشبابى كجول وبدل وفساتين زفاف وشنط وجزم وكوتشى) وكوافيرات وادوات منزليه واكسسورات وهدايا ومكتبات ومستلزمات خياطه وتطريز وتصليح كويتش
        // واقمشه وسجاد وشراء خيم ومعارض سيارات ومركبات (تكاتك وتروسكلات وموتسكلات) ولوازم سيارات ومتوسكلات وبيع حيونات وبيع ورد وزهر وعطاره
        //  ومستلزمات سبوع ونجف والمونتال وموبيليا ودهب ومجوهرات واسماك وفراخ وبيع طيور للاكل وبيع حيونات اليفه
        //  وبيع طيور للزينه وبيع اكل حيوانات وطيور (علف)وحلويات وصيدليات وجزاره بيع اسمنت وحديد سيراميك وبلاط وطوب ومحجر رمل وزلط
        //  وكمبيوترات والكترونيات ولوازم الحاسوب وتليفونات وسنترالات وتحف ولوحات وفول وزيت
        allItems.add(new ItemObjectModel("دليل المطاعم", R.drawable.flat));
//        مطاعم فول وطعميه وكوشرى وطواجن ومشويات واسماك ولحوم وكبده وكباب وشيشتاوك واكل سورى واكل صينى وكريبات وفطاير ومخابز
        allItems.add(new ItemObjectModel("مجوهرات", R.drawable.flat));
        allItems.add(new ItemObjectModel("الكترونيات", R.drawable.chale));
        allItems.add(new ItemObjectModel("مركبات", R.drawable.chale));
        allItems.add(new ItemObjectModel("مستلزمات منزليه", R.drawable.flat));
        allItems.add(new ItemObjectModel("ملابس واكسسورات", R.drawable.flat));
        allItems.add(new ItemObjectModel("مستلزمات مطاعم", R.drawable.chale));
        allItems.add(new ItemObjectModel("مستلزمات رياضيه", R.drawable.chale));
        allItems.add(new ItemObjectModel("مستلزمات فنيه وثقافيه", R.drawable.flat));
        allItems.add(new ItemObjectModel("معدات طبيه", R.drawable.flat));
        allItems.add(new ItemObjectModel("معدات صناعه", R.drawable.chale));
        allItems.add(new ItemObjectModel("لوازم ومعدات زراعيه", R.drawable.chale));
        allItems.add(new ItemObjectModel("حيوانات", R.drawable.flat));
        allItems.add(new ItemObjectModel("اخرى", R.drawable.flat));
//        allItems.add(new ItemObjectModel("اخرى", R.drawable.chale));

//مهم حجز وايجار <== سينمات وتذاكر مسرح وفنادق وعربيات وكشف عيادات وشقق ومبانى ودكاكين وجراجات بدل وفساتين زفاف
// للامان اجعله يبعت اللوكيشن فى الشات ووضع صوره البطاقه او وصل نور للحمايه
//        الفرز على حسب الاحدث للوقت والاقرب للمكان والحاله(جديد او قديم)والارخص للسعر والاعلى تقييم والماركات
        return allItems;
    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,false);
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }
}
// الشات واللوكاشن وقائمه الاسعار وتعديل المعلومات الشخصيه واعلان فديوهات و3 صور والمزادات والمفقودات والمركات والتقييم