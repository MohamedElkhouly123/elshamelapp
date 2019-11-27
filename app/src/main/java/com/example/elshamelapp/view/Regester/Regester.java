package com.example.elshamelapp.view.Regester;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elshamelapp.R;
import com.example.elshamelapp.view.Login.Login;
import com.example.elshamelapp.view.Main.MainActivity;
import com.example.elshamelapp.view.Sign_up.SignUp;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.internal.LikeButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.internal.SignInButtonImpl;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Regester extends AppCompatActivity {
    @BindView(R.id.sign_in_button)
    Button googleSignInButton;
    @BindView(R.id.facebutton)
    LoginButton faceBookSignInButton;
    @BindView(R.id.facebookLogin)
    Button faceBookButton;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.later)
    TextView later;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.signUp)
    Button signUp;
    private GoogleSignInClient googleSignInClient;
    private static final String TAG = "AndroidClarified";
    private ProgressDialog progressDialog;
    CallbackManager callbackManager;
    static String name;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    String googleCheck ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester);
        ButterKnife.bind(this);

        googleCheck="true";
         sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
         editor = sharedPref.edit();
        editor.putString("value", googleCheck);
        editor.commit();
        progressDialog=new ProgressDialog(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Regester.this, MainActivity.class));

            }
        });
        faceBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faceBookSignInButton.performClick();
                Toast.makeText(getApplicationContext(),name, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Regester.this, MainActivity.class);
//////                    intent.putExtra("keyName", name);
//                    startActivity(intent);
            }
        });
//        boolean loggedOut = AccessToken.getCurrentAccessToken() == null;


//        googleSignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressDialog.setMessage("Signing in....");
//                progressDialog.show();
//                Intent signInIntent = googleSignInClient.getSignInIntent();
//                startActivityForResult(signInIntent, 101);
//
//            }
//        });


    }

    @OnClick(R.id.sign_in_button)
    void googleSignInButton(){
        googleCheck="false";
        editor = sharedPref.edit();
        editor.putString("value", googleCheck);
        editor.commit();
        progressDialog.setMessage("Signing in....");
        progressDialog.show();
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 101);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(googleCheck=="true") {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }else
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            switch (requestCode) {
                case 101:
                    try {
                        // The Task returned from this call is always completed, no need to attach
                        // a listener.
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        onLoggedIn(account);
                    } catch (ApiException e) {
                        // The ApiException status code indicates the detailed failure reason.
                        Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                    }
                    break;
            }
        }

    }
    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
//        Intent intent = googleSignInClient.getSignInIntent();
////        startActivityForResult(intent, RC_SIGN_IN);

        progressDialog.dismiss();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("GOOGLE_ACCOUNT", googleSignInAccount);
//        intent.putExtra("gCheck",googleCheck);
        startActivity(intent);
        finish();
    }
    @Override
    public void onStart() {
        super.onStart();


        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {
            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
            onLoggedIn(alreadyloggedAccount);
        } else {
            Log.d(TAG, "Not logged in");
        }
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
//            useLoginInformation(accessToken);

        }

    }

    @OnClick(R.id.facebutton)
    void facBookLogin(){



        boolean loggedOut = AccessToken.getCurrentAccessToken() == null;

        if (!loggedOut) {
//            Picasso.with(this).load(Profile.getCurrentProfile().getProfilePictureUri(200, 200)).into(imageView);
            Log.d("TAG", "Username is: " + Profile.getCurrentProfile().getName());

            //Using Graph API
            getUserProfile(AccessToken.getCurrentAccessToken());
        }

        faceBookSignInButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        callbackManager = CallbackManager.Factory.create();

        faceBookSignInButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                //loginResult.getAccessToken();
                //loginResult.getRecentlyDeniedPermissions()
                //loginResult.getRecentlyGrantedPermissions()
                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
                Log.d("API123", loggedIn + " ??");

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

    }

//    @OnClick(R.id.later)
//    void laterButton(){
//
//        startActivity(new Intent(Regester.this, MainActivity.class));
//
//
//    }
    @OnClick(R.id.login)
    void loginButton(){

        startActivity(new Intent(Regester.this, Login.class));


    }

    @OnClick(R.id.signUp)
    void signUpButton(){

        startActivity(new Intent(Regester.this, SignUp.class));


    }

//        callbackManager = CallbackManager.Factory.create();
//        faceBookSignInButton.setReadPermissions(Arrays.asList("email", "public_profile"));
////        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
//        // Registering CallbackManager with the LoginButton
//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // Retrieving access token using the LoginResult
//                AccessToken accessToken = loginResult.getAccessToken();
//                useLoginInformation(accessToken);
//
//            }
//            @Override
//            public void onCancel() {
//
//            }
//            @Override
//            public void onError(FacebookException error) {
//            }
//
//
//        });

//        f.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Regester.this, MainActivity.class);
////                    intent.putExtra("keyName", name);
//                    startActivity(intent);
//                finish();
//            }
//        });



    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("TAG", object.toString());
                        try {
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            String email = object.getString("email");
                            String id = object.getString("id");
                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                    textView6.setText(email);
//                            txtUsername.setText("First Name: " + first_name + "\nLast Name: " + last_name);
//                            txtEmail.setText(email);
//                            Picasso.with(MainActivity.this).load(image_url).into(imageView);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }


//    private void useLoginInformation(AccessToken accessToken) {
//
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
//                     name = object.getString("name");
//                    String email = object.getString("email");
//                    String image = object.getJSONObject("picture").getJSONObject("data").getString("url");
//                    Toast.makeText(getApplicationContext(),name, Toast.LENGTH_SHORT).show();
////                    String nm=String.valueOf(name);
////                    Intent intent = new Intent(Regester.this, MainActivity.class);
////                    intent.putExtra("keyName", name);
////                    startActivity(intent);
//                    textView6.setText(email);
////                    emailID.setText(email);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        // We set parameters to the GraphRequest using a Bundle.
//
//
//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "id,name,email,picture.width(200)");
//        request.setParameters(parameters);
//        // Initiate the GraphRequest
//        request.executeAsync();
//
//
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
////        AccessToken accessToken = AccessToken.getCurrentAccessToken();
////        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//
//
//    }
}
