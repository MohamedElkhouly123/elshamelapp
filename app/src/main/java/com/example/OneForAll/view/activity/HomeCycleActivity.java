package com.example.OneForAll.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.OneForAll.R;
import com.example.OneForAll.utils.LogOutDialog;
import com.example.OneForAll.view.fragment.HomeCycle2.category.CategoryFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.home.HomeContainerFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.home.ProductsAndAddsDetailsFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.home.SearchFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.home.profileFragments.ProfileFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.more.ContactUSFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.more.ImportantAddsFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.more.UploadAddFragment;
import com.example.OneForAll.view.fragment.HomeCycle2.notificationsMenues.NotificationsFragment;
import com.example.OneForAll.view.fragment.splashCycle.AboutAppAndIntroFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.OneForAll.data.local.SharedPreferencesManger.GOOGLECHECK;
import static com.example.OneForAll.data.local.SharedPreferencesManger.LoadData;
import static com.example.OneForAll.utils.HelperMethod.replaceFragment;
import static com.example.OneForAll.utils.HelperMethod.replaceFragmentWithAnimation;


public class HomeCycleActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.card_view_tool)
    CardView cardViewTool;
    @BindView(R.id.bottom_lay)
    LinearLayout bottomLay;
    @BindView(R.id.back_btn)
    ImageButton backBtn;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_sub_view)
    ConstraintLayout toolbarSubView;
    @BindView(R.id.app_bar_layout_crimg_profilePhoto)
    CircleImageView appBarLayoutCrimgProfilePhoto;
    @BindView(R.id.card_view_nav_viewb)
    CardView cardViewNavViewb;
    private BoomMenuButton bmb;
    private GoogleSignInClient googleSignInClient;
    GridLayout gridLayout;
    String check = "true";
    public ConstraintLayout toolBarLay;
    public BottomNavigationView buttonNavigation;
    private NavigationView navigationViewSide;
    public DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
//    private FirebaseAuth mAuth;

    //    private Menu nav_Menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        appBarLayoutCrimgProfilePhoto.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

//        String data = getIntent().getExtras().getString("keyName");
//        Toast.makeText(this,data, Toast.LENGTH_SHORT).show();
        drawer = findViewById(R.id.drawer_layout);
//        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
//        check = sharedPreferences.getString("value", "");
        String check = LoadData(this, GOOGLECHECK);
        Toast.makeText(this, check, Toast.LENGTH_SHORT).show();

        Toolbar toolbar = findViewById(R.id.app_bar_main2_toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
        toolBarLay = (ConstraintLayout) findViewById(R.id.toolbar_sub_view);

        buttonNavigation = findViewById(R.id.nav_viewb);
        buttonNavigation.setOnNavigationItemSelectedListener(this);
//        buttonNavigation.getMenu().getItem(0).setChecked(true);
        navigationViewSide = (NavigationView) findViewById(R.id.nav_view_side);
//         nav_Menu = navigationViewSide.getMenu();
//        nav_Menu.findItem(R.id.nav_view_side).setVisible(false);
        View headerLayout = navigationViewSide.getHeaderView(0);
        CircleImageView imageViewAccountNaveHeader= (de.hdodenhof.circleimageview.CircleImageView) headerLayout.findViewById(R.id.imageView_account);
        imageViewAccountNaveHeader.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        replaceFragment(getSupportFragmentManager(), R.id.home_activity_fram, new HomeContainerFragment());
        buttonNavigation.getMenu().getItem(0).setChecked(true);

//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view_side);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationViewSide.setNavigationItemSelectedListener(this);


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
            if (i == 0) {
                builder.normalText("mohamed")

                ;
            }
            if (i == 1) {
                builder.normalText("ahmed");
            }
            if (i == 2) {
                builder.normalTextRes(R.string.text_ham_button_text_normal);
            }
            if (i == 3) {
                builder.normalText("mohamed");
            }

            builder.listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    // When the boom-button corresponding this builder is clicked.
                    Toast.makeText(HomeCycleActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                            if(index==0){// do this
                                 }
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
        if (check == "false") {
            // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();


// Build a GoogleSignInClient with the options specified by gso.
            googleSignInClient = GoogleSignIn.getClient(this, gso);

            setDataOnView();
        }
//        setDataOnView();

    }

    public void setToolBar(int visibility, String title, View.OnClickListener backActionBtn) {
        toolbarSubView.setVisibility(visibility);

        if (visibility == View.VISIBLE) {
            toolbarTitle.setText(title);
            backBtn.setOnClickListener(backActionBtn);
        }

    }

    public void setNavigationAndToolBar(int visibility, boolean hide) {

        cardViewTool.setVisibility(visibility);
        bottomLay.setVisibility(visibility);

        toggleNav(hide);
//        toggle.setDrawerIndicatorEnabled(false);
//        toggle.isDrawerSlideAnimationEnabled();
    }

    public void setFloatBottonAndToolBar(int visibility) {

        cardViewTool.setVisibility(visibility);
        bmb.setVisibility(visibility);
        if (visibility == View.GONE) {
            toggleNav(true);
        } else {
            toggleNav(false);
        }


    }

    public void setFloatBottonOnly(int visibility) {
        bmb.setVisibility(visibility);
    }

    @SuppressLint("WrongConstant")
    private void toggleNav(boolean side) {
        if (side) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.setDrawerIndicatorEnabled(false);
            toggle.syncState();

//        navigationViewSide.setVisibility(visibility);
        } else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
            toggle.setDrawerIndicatorEnabled(true);
            toggle.syncState();
        }
    }


    private void setDataOnView() {

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
//            String phone = acct.getAccount().getPhoneNumber();


//            String personPhotoUrl = String.valueOf(acct.getPhotoUrl());

            Toast.makeText(this, "url " + personEmail, Toast.LENGTH_SHORT).show();

        }
//        GoogleSignInAccount googleSignInAccount = getIntent().getParcelableExtra("GOOGLE_ACCOUNT");
//        Picasso.get().load(googleSignInAccount.getPhotoUrl()).centerInside().fit().into(profileImage);
//        String hh = String.valueOf(googleSignInAccount.getPhotoUrl());
//        Toast.makeText(this, googleSignInAccount.getEmail(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, googleSignInAccount.getPhoneNumber(), Toast.LENGTH_SHORT).show();

//
//        if (hh.equalsIgnoreCase("null")) {
//            appBarLayoutCrimgProfilePhoto.setImageResource(R.drawable.placeperson);
//
//
//        } else {
//            Picasso.get()
//                    .load(hh)
//                    .resize(80, 80)
//                    .centerCrop()
//                    .into(appBarLayoutCrimgProfilePhoto);
//        }
//        profileName.setText(googleSignInAccount.getDisplayName());
//        profileEmail.setText(googleSignInAccount.getEmail());
    }




//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }


    @Override
    public void onStart() {
        super.onStart();

//        mAuth = FirebaseAuth.getInstance();

            // Check if user is signed in (non-null) and update UI accordingly.
//            FirebaseUser currentUser = mAuth.getCurrentUser();
//            if(currentUser!=null){
////                updateUI();
//            }
            }



    private void updateUI() {
        Intent intent = new Intent(this, UserCycleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
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
            toolbarSubView.setVisibility(View.GONE);
            setNavigationAndToolBar(View.VISIBLE, false);
            replaceFragment(getSupportFragmentManager(), R.id.home_activity_fram, new HomeContainerFragment());

        } else if (id == R.id.navigation_notifications) {
            backBtn.setVisibility(View.GONE);
            replaceFragment(getSupportFragmentManager(), R.id.home_activity_fram, new NotificationsFragment());
            setFloatBottonAndToolBar(View.GONE);
        } else if (id == R.id.navigation_category) {
            backBtn.setVisibility(View.GONE);
            replaceFragment(getSupportFragmentManager(), R.id.home_activity_fram, new CategoryFragment());
            setFloatBottonAndToolBar(View.GONE);
        } else if (id == R.id.importantAds) {
            backBtn.setVisibility(View.GONE);
            replaceFragment(getSupportFragmentManager(), R.id.home_activity_fram, new ImportantAddsFragment());
            setFloatBottonAndToolBar(View.GONE);
        } else if (id == R.id.Contact_Us) {
            toolbarSubView.setVisibility(View.GONE);
            replaceFragmentWithAnimation(getSupportFragmentManager(), R.id.home_activity_fram, new ContactUSFragment(), "r");
            setNavigationAndToolBar(View.GONE, true);
        } else if (id == R.id.brands) {
            toolbarSubView.setVisibility(View.GONE);
            replaceFragmentWithAnimation(getSupportFragmentManager(), R.id.home_activity_fram, new ProductsAndAddsDetailsFragment(), "r");
            setNavigationAndToolBar(View.GONE, true);
//            toolbarSubView.setVisibility(View.GONE);
//            startActivity(new Intent(HomeCycleActivity.this, MyProduct.class));
        } else if (id == R.id.home) {
            toolbarSubView.setVisibility(View.GONE);
            replaceFragment(getSupportFragmentManager(), R.id.home_activity_fram, new HomeContainerFragment());
        } else if (id == R.id.aboutApp) {
            toolbarSubView.setVisibility(View.GONE);
            Bundle bundle = new Bundle();
            bundle.putBoolean("INHOME", true);
            AboutAppAndIntroFragment aboutAppAndIntroFragment = new AboutAppAndIntroFragment();
            aboutAppAndIntroFragment.setArguments(bundle);
            replaceFragmentWithAnimation(getSupportFragmentManager(), R.id.home_activity_fram,aboutAppAndIntroFragment,"r");
            setNavigationAndToolBar(View.GONE, true);

        } else if (id == R.id.addProduct) {
            toolbarSubView.setVisibility(View.GONE);
            Bundle bundle = new Bundle();
            bundle.putString("ISADDSFROMHOME", "home");
            UploadAddFragment uploadAddFragment = new UploadAddFragment();
            uploadAddFragment.setArguments(bundle);
            replaceFragmentWithAnimation(getSupportFragmentManager(), R.id.home_activity_fram, uploadAddFragment, "r");
            setNavigationAndToolBar(View.GONE, true);
        } else if (id == R.id.nav_LogOut) {
            new LogOutDialog().showDialog(this);
        }
//        else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.app_bar_layout_search_tv, R.id.app_bar_layout_crimg_profilePhoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.app_bar_layout_search_tv:
                toolbarSubView.setVisibility(View.GONE);
                replaceFragment(getSupportFragmentManager(), R.id.home_activity_fram, new SearchFragment());
                setNavigationAndToolBar(View.GONE, true);
                break;
            case R.id.app_bar_layout_crimg_profilePhoto:
                toolbarSubView.setVisibility(View.GONE);
                Bundle bundle = new Bundle();
                bundle.putString("ISMYPROFILE", "myProfile");
                ProfileFragment profileFragment = new ProfileFragment();
                profileFragment.setArguments(bundle);
                replaceFragmentWithAnimation(getSupportFragmentManager(), R.id.home_activity_fram, profileFragment, "t");
                setNavigationAndToolBar(View.GONE, true);

                break;
        }
    }
//    private void loadFragment(Fragment fragment) {
//        // load fragment
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
}
