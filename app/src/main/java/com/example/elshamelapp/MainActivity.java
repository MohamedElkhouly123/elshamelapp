package com.example.elshamelapp;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.elshamelapp.view.categories.CategoryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Util;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener ,
        BottomNavigationView.OnNavigationItemSelectedListener{
    private BoomMenuButton bmb;

    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView2;

        BottomNavigationView navigation =  findViewById(R.id.nav_viewb);
        navigation.setOnNavigationItemSelectedListener(this);

        navigationView2 = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView2.getHeaderView(0);
        loadFragment(new HomeFragment());


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


//        gridLayout=(GridLayout)findViewById(R.id.mainGrid);
//
//        setSingleEvent(gridLayout);



        bmb = (BoomMenuButton) findViewById(R.id.bmb);


        bmb.setButtonEnum(ButtonEnum.Ham);

//        bmb.addBuilder(new SimpleCircleButton.Builder().normalImageRes(R.drawable.ic_add_blue_24dp));

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
//            new SimpleCircleButton.Builder().normalImageRes(R.drawable.ic_add_blue_24dp);
            HamButton.Builder builder = new HamButton.Builder()

//                    .normalImageRes(getImageResource())
//                    .normalText("moh")
//                    .subNormalTextRes(R.string.text_outside_circle_button_text_normal)
                    .normalTextColorRes(R.color.white)
                    .normalColorRes(R.color.blue)
                    .highlightedColorRes(R.color.blueLight)

//                    .textGravity(Gravity.CENTER_HORIZONTAL)
                    .textGravity(Gravity.CENTER_VERTICAL)
                    .textSize(20)
                    .rotateImage(true)
                    .buttonCornerRadius(Util.dp2px(5))
//                    .textPadding(new Rect(10, 0, 10, 0))
//                    .unableImageRes(R.drawable.markafhjbaharea)
//                    .imageRect(new Rect(0, 0, Util.dp2px(60), Util.dp2px(60)))
//                    .imagePadding(new Rect(5, 0, 5, 0))
//                    .textRect(new Rect(Util.dp2px(70), Util.dp2px(10), Util.dp2px(280), Util.dp2px(40)))
                    ;

//                    .pieceColor(Color.WHITE)
//                    .shadowEffect(true)
//                    .shadowRadius(Util.dp2px(100))
            if(i==0){builder.normalText("mohamed")

            ; }
            if(i==1){builder.normalText("ahmed"); }
            if(i==2){builder.normalTextRes(R.string.text_ham_button_text_normal) ;}
            if(i==3){builder.normalText("mohamed"); }

            builder .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    // When the boom-button corresponding this builder is clicked.
                    Toast.makeText(MainActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
//                            if(index==0){// do this}
//                            if(index==1){ }
//                            if(index==2){ }
//                            if(index==3){}

                }
//                       @Override
//                       public void onBackgroundClick() {
//                           textViewForAnimation.setText("Click background!!!");
//                       }
//                       @Override
//                       public void onBoomWillHide() {
//                           Log.d("BMB", "onBoomWillHide: " + bmb.isBoomed() + " " + bmb.isReBoomed());
//                           textViewForAnimation.setText("Will RE-BOOM!!!");
//                       }
//                       @Override
//                       public void onBoomDidHide() {
//                           Log.d("BMB", "onBoomDidHide: " + bmb.isBoomed() + " " + bmb.isReBoomed());
//                           textViewForAnimation.setText("Did RE-BOOM!!!");
//                       }
//                       @Override
//                       public void onBoomWillShow() {
//                           Log.d("BMB", "onBoomWillShow: " + bmb.isBoomed() + " " + bmb.isReBoomed());
//                           textViewForAnimation.setText("Will BOOM!!!");
//                       }
//                       @Override
//                       public void onBoomDidShow() {
//                           Log.d("BMB", "onBoomDidShow: " + bmb.isBoomed() + " " + bmb.isReBoomed());
//                           textViewForAnimation.setText("Did BOOM!!!");
//                       }
            });
            bmb.addBuilder(builder);

        }
    }

    // we are setting onClickListener for each element
//    private void setSingleEvent(GridLayout gridLayout) {
//        for(int i = 0; i<gridLayout.getChildCount();i++){
//            CardView cardView=(CardView)gridLayout.getChildAt(i);
//            final int finalI= i;
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(Main2Activity.this,"Clicked at index "+ finalI,
//                            Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//
//    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment;
        if (id == R.id.navigation_home) {
            // Handle the camera action
            fragment = new HomeFragment();
            loadFragment(fragment);
        } else if (id == R.id.navigation_notifications) {
            fragment = new NotificationsFragment();
            loadFragment(fragment);
        } else if (id == R.id.navigation_category) {
            fragment = new CategoryFragment();
            loadFragment(fragment);
        } else if (id == R.id.importantAds) {
            fragment = new ImportantAddsFragment();
            loadFragment(fragment);
        }
//        else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
