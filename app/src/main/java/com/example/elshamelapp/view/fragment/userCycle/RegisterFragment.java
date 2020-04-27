package com.example.elshamelapp.view.fragment.userCycle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.elshamelapp.R;
import com.example.elshamelapp.view.activity.HomeCycleActivity;
import com.example.elshamelapp.view.fragment.BaSeFragment;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
import static com.example.elshamelapp.utils.HelperMethod.replaceFragmentWithAnimation;
import static com.facebook.FacebookSdk.getApplicationContext;


public class RegisterFragment extends BaSeFragment {

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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_regester, container, false);

        ButterKnife.bind(this, root);

        googleCheck="true";
        sharedPref = getActivity().getSharedPreferences("myKey", MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString("value", googleCheck);
        editor.commit();
        progressDialog=new ProgressDialog(getActivity());
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HomeCycleActivity.class));

            }
        });
        faceBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faceBookSignInButton.performClick();
                Toast.makeText(getApplicationContext(),name, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Regester.this, HomeCycleActivity.class);
//////                    intent.putExtra("keyName", name);
//                    startActivity(intent);
            }
        });
        return root;
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
        Intent intent = new Intent(getActivity(), HomeCycleActivity.class);
        intent.putExtra("GOOGLE_ACCOUNT", googleSignInAccount);
//        intent.putExtra("gCheck",googleCheck);
        startActivity(intent);
        getActivity().finish();
    }
    @Override
    public void onStart() {
        super.onStart();


        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (alreadyloggedAccount != null) {
            Toast.makeText(getActivity(), "Already Logged In", Toast.LENGTH_SHORT).show();
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
//        startActivity(new Intent(Regester.this, HomeCycleActivity.class));
//
//
//    }
    @OnClick(R.id.login)
    void loginButton(){
        replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(), "r");



    }

    @OnClick(R.id.signUp)
    void signUpButton(){

        replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new SignUpFragment(), "l");


    }

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
//                            Picasso.with(HomeCycleActivity.this).load(image_url).into(imageView);

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


    @Override
    public void onBack() {
        getActivity().finish();
    }
}