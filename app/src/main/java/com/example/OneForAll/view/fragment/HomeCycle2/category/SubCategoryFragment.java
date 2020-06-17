package com.example.OneForAll.view.fragment.HomeCycle2.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.OneForAll.R;
import com.example.OneForAll.adapter.CategoriesAdapter;
import com.example.OneForAll.data.model.ItemObjectModel;
import com.example.OneForAll.view.activity.HomeCycleActivity;
import com.example.OneForAll.view.fragment.BaSeFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.home.HomeContainerFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.OneForAll.utils.HelperMethod.replaceFragment;

public class SubCategoryFragment extends BaSeFragment {

    @BindView(R.id.sub_category_fragment_make_full_screen_floating_action_btn)
    FloatingActionButton subCategoryFragmentMakeFullScreenFloatingActionBtn;
    private LinearLayoutManager lLayout;
    @BindView(R.id.sub_category_fragment_recycler_view)
    RecyclerView rView;
    private static final int HIDE_THRESHOLD = 20;
    private static final int HIDE_THRESHOLD2 = 22;
    private int scrolledDistance = 0;
    private boolean controlsVisible = true;
    int heightDelta = 0;
    private boolean firstPress =true;

    public SubCategoryFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sub_categories, container, false);
        ButterKnife.bind(this, root);
        homeCycleActivity = (HomeCycleActivity) getActivity();
        homeCycleActivity.setToolBar(View.VISIBLE, getString(R.string.the_sub_categories)
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBack();
                    }
                });
        List<ItemObjectModel> rowListItem = getAllItemList();
//        lLayout = new LinearLayoutManager(getActivity());
        rView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

//        rView.setLayoutManager(lLayout);

        CategoriesAdapter rcAdapter = new CategoriesAdapter(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);

        // 5. set item animator to DefaultAnimator
        rView.setItemAnimator(new DefaultItemAnimator());
        toolHidden();

        return root;
    }

    private List<ItemObjectModel> getAllItemList() {

        List<ItemObjectModel> allItems = new ArrayList<ItemObjectModel>();
//        شقق ومبانى وشركات ومصانع ومحلات وعقارات تجاريه وشاليهات وفلل وجراجات واراضى مبانى واراضى زراعيه
        allItems.add(new ItemObjectModel("عقارات", R.drawable.chale)); //دليل محلاتى
        // محلات <== بقاله وسوبرماركتات ومولات وحدايدوبوهيات ولوازم كهربه ولوازم سباكه وزجاج  ومستحضرات تجميل ومستلزمات طبيه وفاكهه وخضروات وبيع الات موسيقيه
        // وملابس (اطفالى ونسائى ورجالى ورياضيه وشبابى كجول وبدل وفساتين زفاف وشنط وجزم وكوتشى) وكوافيرات وادوات منزليه واكسسورات وهدايا ومكتبات ومستلزمات خياطه وتطريز وتصليح كويتش
        // واقمشه وسجاد وشراء خيم ومعارض سيارات ومركبات (تكاتك وتروسكلات وموتسكلات) ولوازم سيارات ومتوسكلات وبيع حيونات وبيع ورد وزهر وعطاره
        //  ومستلزمات سبوع ونجف والمونتال وموبيليا ودهب ومجوهرات واسماك وفراخ وبيع طيور للاكل وبيع حيونات اليفه
        //  وبيع طيور للزينه وبيع اكل حيوانات وطيور (علف)وحلويات وصيدليات وجزاره بيع اسمنت وحديد سيراميك وبلاط وطوب ومحجر رمل وزلط
        //  وكمبيوترات والكترونيات ولوازم الحاسوب وتليفونات وسنترالات وتحف ولوحات وفول وزيت
        allItems.add(new ItemObjectModel("دليل المطاعم والمشروبات", R.drawable.flat));
//        مطاعم فول وطعميه وكوشرى وطواجن ومشويات واسماك وماكولات بحريه ولحوم وكبده وكباب وشيشتاوك وكنتاكى واكل سورى واكل صينى وكريبات وشويرما وفطاير ومخابز وحلويات وكفتريه وكوفى شوب
        allItems.add(new ItemObjectModel("مجوهرات", R.drawable.flat));
//        الماس والفضه والذهب (حلى وسبائك وعملات)
        allItems.add(new ItemObjectModel("الكترونيات", R.drawable.chale));
//       موبايلات ومستلزماته وتليفونات وكمبيوتر وتابلت ولابتوب والعاب الكترونيه وتلفاز وريسيفر وكاميرات وقطع غيار ومستلزمات الكترونيه واخرى
        allItems.add(new ItemObjectModel("مركبات", R.drawable.chale));
//        سيارات ودراجات ناريه ورجات هوائيه قوارب ولنش وسفن اتوبيسات ومركبات نقل ثقيل قطع غيار ومستلزمات مركبات واخرى
        allItems.add(new ItemObjectModel("مكونات المنزل", R.drawable.flat));
//       اثاث واجهزه منزليه ومستلزمات الحديقه ومكونات ومعدات المطبخ والسجاد والنجف والستائر واكسسورات وديكور المنزل واخرى
        allItems.add(new ItemObjectModel("ملابس واكسسورات ورياضه", R.drawable.flat));
//       ساعات واكسسورات وملابس رجالى وملابس حريمى وملابس رياضيه وكوتشى وجزم ومعدات رياضيه ومستلزمات اطفالى (لعب اطفال) واخرى
        allItems.add(new ItemObjectModel("ادوات ومعدات مطاعم", R.drawable.chale));
//        allItems.add(new ItemObjectModel("مستلزمات رياضيه", R.drawable.chale));
        allItems.add(new ItemObjectModel("مستلزمات فنيه وثقافيه", R.drawable.flat));
//        كتب ومكتبات والات موسيقيه وادوات الدراسه وتحف وانتيكات وهدايا
        allItems.add(new ItemObjectModel("معدات طبيه", R.drawable.flat));
        allItems.add(new ItemObjectModel("معدات صناعه", R.drawable.chale));
        allItems.add(new ItemObjectModel("لوازم ومعدات زراعيه", R.drawable.chale));
        allItems.add(new ItemObjectModel("حيوانات اليفه", R.drawable.flat));
        allItems.add(new ItemObjectModel("اخرى", R.drawable.flat));
//        allItems.add(new ItemObjectModel("اخرى", R.drawable.chale));

//مهم حجز وايجار <== سينمات وتذاكر مسرح وفنادق وسفر ورحلات وعربيات ومركبات ومعدات ثقيله وكشف عيادات وشقق ومبانى ودكاكين وجراجات بدل وفساتين زفاف وقاعات افراح ومناسبات

// للامان اجعله يبعت اللوكيشن فى الشات ووضع صوره البطاقه او وصل نور للحمايه

//        الفرز على حسب الاحدث للوقت والاقرب للمكان والحاله(جديد او قديم)والارخص للسعر والاعلى تقييم والماركات
        return allItems;
    }


    private void toolHidden() {
        rView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                heightDelta += dy;

                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && subCategoryFragmentMakeFullScreenFloatingActionBtn.getVisibility() == View.VISIBLE) {
                    subCategoryFragmentMakeFullScreenFloatingActionBtn.hide();

//                    homeCycleActivity.toolBarLay.animate().translationY(-heightDelta).setInterpolator(new AccelerateInterpolator()).start();
//                    homeCycleActivity.toolBarLay.setVisibility(View.GONE);

                } else if (dy < 0 && subCategoryFragmentMakeFullScreenFloatingActionBtn.getVisibility() != View.VISIBLE) {
                    subCategoryFragmentMakeFullScreenFloatingActionBtn.show();
//                    homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);
//                    homeCycleActivity.toolBarLay.animate().translationY(heightDelta).setInterpolator(new AccelerateInterpolator()).start();

                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
//        homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);
//        homeCycleActivity.toolBarLay.animate().translationY(heightDelta).setInterpolator(new AccelerateInterpolator()).start();
        subCategoryFragmentMakeFullScreenFloatingActionBtn.show();

    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeContainerFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,false);
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }

    @OnClick(R.id.sub_category_fragment_make_full_screen_floating_action_btn)
    public void onViewClicked() {
        if(firstPress){
            subCategoryFragmentMakeFullScreenFloatingActionBtn.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fullscreen_black_24dp));
            homeCycleActivity.toolBarLay.setVisibility(View.GONE);
//            categoryFragmentMakeFullScreenFloatingActionBtn.setImageResource(R.drawable.ic_fullscreen_black_24dp);

            firstPress=false;
        }else {
            subCategoryFragmentMakeFullScreenFloatingActionBtn.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fullscreen_exit_black_24dp));
            homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);
//            categoryFragmentMakeFullScreenFloatingActionBtn.setImageResource(R.drawable.ic_fullscreen_exit_black_24dp);

            firstPress=true;
        }
        subCategoryFragmentMakeFullScreenFloatingActionBtn.hide();
        subCategoryFragmentMakeFullScreenFloatingActionBtn.show();
    }
}
//والعروض والخصومات  الشات واللوكاشن وقائمه الاسعار وتعديل المعلومات الشخصيه واعلان فديوهات و3 صور والمزادات والمفقودات والمركات والتقييم


//    private void toolHidden() {
//        rView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
////                if (dy > 0 && homeCycleActivity.toolBarLay.getVisibility() == View.VISIBLE) {
////                    homeCycleActivity.toolBarLay.setVisibility(View.GONE);
////                } else if (dy < 0 && homeCycleActivity.toolBarLay.getVisibility() != View.VISIBLE) {
////                    homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);
////
////                }
//
//                if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
//                    homeCycleActivity.toolBarLay.setVisibility(View.GONE);
//                    onHide();
//                    controlsVisible = false;
//                    scrolledDistance = 0;
//
//                } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
//                    homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);
//                    onShow();
//                    controlsVisible = true;
//                    scrolledDistance = 0;
//
//                }
//                if((controlsVisible && dy>0) || (!controlsVisible && dy<0)) {
//                    scrolledDistance += dy;
//                }
//
//            }
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//        });
//
////        homeCycleActivity.toolBarLay.setVisibility(View.VISIBLE);
//
//
////        @Override
////        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
////            super.onScrolled(recyclerView, dx, dy);
////
////            int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
////            //show views if first item is first visible position and views are hidden
////            if (firstVisibleItem == 0) {
////                if(!controlsVisible) {
////                    onShow();
////                    controlsVisible = true;
////                }
////            } else {
////                if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
////                    onHide();
////                    controlsVisible = false;
////                    scrolledDistance = 0;
////                } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
////                    onShow();
////                    controlsVisible = true;
////                    scrolledDistance = 0;
////                }
////            }
////
////            if((controlsVisible && dy>0) || (!controlsVisible && dy<0)) {
////                scrolledDistance += dy;
////            }
////        }
//    }
//
//    private void onHide() {
//        homeCycleActivity.toolBarLay.animate().translationY(-homeCycleActivity.toolBarLay.getHeight()).setInterpolator(new AccelerateInterpolator(2));
//
//    }
//
//    private void onShow() {
//        homeCycleActivity.toolBarLay.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
//    }