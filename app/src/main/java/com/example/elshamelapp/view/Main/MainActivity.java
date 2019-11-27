package com.example.elshamelapp.view.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.elshamelapp.view.About_App.AboutApp;
import com.example.elshamelapp.view.Notifications.NotificationsFragment;
import com.example.elshamelapp.R;
import com.example.elshamelapp.view.Home.HomeFragment;
import com.example.elshamelapp.view.ImportantAda.ImportantAddsFragment;
import com.example.elshamelapp.view.Profile.MyProfile;
import com.example.elshamelapp.view.Regester.Regester;
import com.example.elshamelapp.view.Upload_Product.UploodProduct;
import com.example.elshamelapp.view.categories.CategoryFragment;
import com.example.elshamelapp.view.contactUs.ContactUs;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Util;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener ,
        BottomNavigationView.OnNavigationItemSelectedListener{
    private BoomMenuButton bmb;
    private GoogleSignInClient googleSignInClient;
    @BindView(R.id.profilePhoto)
    CircleImageView profileImage;
    GridLayout gridLayout;
    String check ="true";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        String data = getIntent().getExtras().getString("keyName");
//        Toast.makeText(this,data, Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        check = sharedPreferences.getString("value","");
        Toast.makeText(this,check, Toast.LENGTH_SHORT).show();

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
        if(check=="false"){
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

    @OnClick(R.id.profilePhoto)
    void myProfile(){

        startActivity(new Intent(MainActivity.this, MyProfile.class));

    }

    private void setDataOnView(){


        GoogleSignInAccount googleSignInAccount = getIntent().getParcelableExtra("GOOGLE_ACCOUNT");
//        Picasso.get().load(googleSignInAccount.getPhotoUrl()).centerInside().fit().into(profileImage);
        String hh = String.valueOf(googleSignInAccount.getPhotoUrl());
        Toast.makeText(this, "url"+googleSignInAccount.getPhotoUrl(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, googleSignInAccount.getEmail(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, googleSignInAccount.getPhoneNumber(), Toast.LENGTH_SHORT).show();


        if (hh == "null") {
            profileImage.setImageResource(R.drawable.placeperson);


        } else {
            Picasso.get()
                    .load(hh)
                    .resize(80, 80)
                    .centerCrop()
                    .into(profileImage);
        }
//        profileName.setText(googleSignInAccount.getDisplayName());
//        profileEmail.setText(googleSignInAccount.getEmail());
    }

//    private void useLoginInformation(AccessToken accessToken) {
//        /**
//         Creating the GraphRequest to fetch user details
//         1st Param - AccessToken
//         2nd Param - Callback (which will be invoked once the request is successful)
//         **/
//        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
//            //OnCompleted is invoked once the GraphRequest is successful
//            @Override
//            public void onCompleted(JSONObject object, GraphResponse response) {
//                try {
//                    String name = object.getString("name");
//                    String email = object.getString("email");
//                    String image = object.getJSONObject("picture").getJSONObject("data").getString("url");
////                    displayName.setText(name);
////                    emailID.setText(email);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        // We set parameters to the GraphRequest using a Bundle.
//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "id,name,email,picture.width(200)");
//        request.setParameters(parameters);
//        // Initiate the GraphRequest
//        request.executeAsync();
//    }
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
        } else if (id == R.id.Contact_Us) {
            startActivity(new Intent(MainActivity.this, ContactUs.class));
        } else if (id == R.id.home) {

            fragment = new HomeFragment();
            loadFragment(fragment);
        }else if (id == R.id.aboutApp) {

            startActivity(new Intent(MainActivity.this, AboutApp.class));

        }else if (id == R.id.addProduct) {

            startActivity(new Intent(MainActivity.this, UploodProduct.class));

        } else if (id == R.id.nav_LogOut) {


            // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();


// Build a GoogleSignInClient with the options specified by gso.
            googleSignInClient = GoogleSignIn.getClient(this, gso);

 /*
          Sign-out is initiated by simply calling the googleSignInClient.signOut API. We add a
          listener which will be invoked once the sign out is the successful
           */
            LoginManager.getInstance().logOut();

            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                   @Override
                                                                   public void onComplete(@NonNull Task<Void> task) {
                                                                       //On Succesfull signout we navigate the user back to LoginActivity
                                                                       Intent intent=new Intent(MainActivity.this, Regester.class);
                                                                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                       startActivity(intent);
                                                                   }
                                                                   });




        }
//        else if (id == R.id.nav_send) {
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
