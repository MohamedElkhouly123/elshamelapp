package com.example.OneForAll.view.fragment.userCycle;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.OneForAll.R;
import com.example.OneForAll.view.activity.HomeCycleActivity;
import com.example.OneForAll.view.fragment.BaSeFragment;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.internal.ImageRequest;
import com.facebook.login.LoginManager;
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

import static com.example.OneForAll.data.local.SharedPreferencesManger.GOOGLECHECK;
import static com.example.OneForAll.data.local.SharedPreferencesManger.SaveData;
import static com.example.OneForAll.utils.HelperMethod.replaceFragmentWithAnimation;


public class RegisterFragment extends BaSeFragment {

    @BindView(R.id.sign_in_button)
    Button googleSignInButton;
    @BindView(R.id.register_fragment_real_facebutton)
    LoginButton faceBookSignInButton;
    @BindView(R.id.facebookLogin)
    Button faceBookButton;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.later)
    TextView later;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.fragment_login_sign_up_tv)
    Button signUp;
    private GoogleSignInClient googleSignInClient;
    private static final String TAG = "AndroidClarified";
    private ProgressDialog progressDialog;
    CallbackManager callbackManager;
    static String name;
//    SharedPreferences sharedPref;
//    SharedPreferences.Editor editor;
    String googleCheck ="";
//    private FirebaseAuth mAuth;

    public RegisterFragment() {
        // Required empty public constructor
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_regester, container, false);

        ButterKnife.bind(this, root);
        googleCheck ="";
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
// ...
// Initialize Firebase Auth
//        mAuth = FirebaseAuth.getInstance();
        googleCheck="true";
        SaveData(getActivity(), GOOGLECHECK, googleCheck);

//        sharedPref = getActivity().getSharedPreferences("myKey", MODE_PRIVATE);
//        editor = sharedPref.edit();
//        editor.putString("value", googleCheck);
//        editor.commit();
        progressDialog=new ProgressDialog(getActivity());
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HomeCycleActivity.class));
                getActivity().finish();
            }
        });
        faceBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faceBookSignInButton.performClick();
//                updateUI(null);

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
        SaveData(getActivity(), GOOGLECHECK, googleCheck);
//        editor = sharedPref.edit();
//        editor.putString("value", googleCheck);
//        editor.commit();
        progressDialog.setMessage("Signing in....");
        progressDialog.show();
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 101);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(googleCheck=="true") {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }else{
        if (resultCode == Activity.RESULT_OK){
            switch (requestCode) {
                case 101:
                    try {
                        // The Task returned from this call is always completed, no need to attach
                        // a listener.
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        onLoggedInByGoogle(account);
                    } catch (ApiException e) {
                        // The ApiException status code indicates the detailed failure reason.
                        Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                    }
                    break;
            }
        }}

    }

    private void onLoggedInByGoogle(GoogleSignInAccount googleSignInAccount) {
//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
//        if (acct != null) {
//            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
//            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
//            Toast.makeText(getContext(), "url" + personPhoto, Toast.LENGTH_SHORT).show();
//
//        }
        getPhone();
        Intent intent2 = googleSignInClient.getSignInIntent();
        startActivityForResult(intent2, Activity.RESULT_OK);

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
        if(googleCheck=="true") {

        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser!=null){
//        updateUI(currentUser);}
//            updateUI();
        }else  if(googleCheck=="false"){
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (alreadyloggedAccount != null) {
            Toast.makeText(getActivity(), "Already Logged In", Toast.LENGTH_SHORT).show();
            onLoggedInByGoogle(alreadyloggedAccount);
        } else {
            Log.d(TAG, "Not logged in");
        }
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
//            useLoginInformation(accessToken);

        }
    }

    }

    @OnClick(R.id.register_fragment_real_facebutton)
    void facBookLogin(){



//        boolean loggedOut = AccessToken.getCurrentAccessToken() == null;
//
//        if (!loggedOut) {
////            Picasso.with(this).load(Profile.getCurrentProfile().getProfilePictureUri(200, 200)).into(imageView);
//            Log.d("TAG", "Username is: " + Profile.getCurrentProfile().getName());
//
//            //Using Graph API
//            getUserProfile(AccessToken.getCurrentAccessToken());
//        }
        faceBookSignInButton.setFragment(this);
        faceBookSignInButton.setEnabled(false);
//        faceBookButton.setEnabled(false);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("public_profile", "email", "user_birthday", "user_photos"));
//        LoginManager.getInstance().logInWithReadPermissions(getActivity(),Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:محمد احمد" + loginResult);
                Toast.makeText(getActivity(), "Already Logged In face", Toast.LENGTH_SHORT).show();


//                handleFacebookAccessToken(loginResult.getAccessToken());
                getUserProfile(loginResult.getAccessToken());
//                updateUI();

            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
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

    @OnClick(R.id.fragment_login_sign_up_tv)
    void signUpButton(){
        Bundle bundle=new Bundle();
        bundle.putString("ISSIGNUP","signUp");
        SignUpFragment signUpFragment=new SignUpFragment();
        signUpFragment.setArguments(bundle);
        replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, signUpFragment, "l");

    }

    private void getPhone() {
//        AccountManager am = AccountManager.get(getActivity());
//        Account[] accounts = am.getAccounts();
//
//        for (Account ac : accounts) {
//            String acname = ac.name;
//            String actype = ac.type;
//            String phoneNumber="";
//            if(actype.equals("com.whatsapp")){
//                phoneNumber = ac.name;
//            }
//        2

        String phoneNumber="";
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

// Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS)) {
                TelephonyManager tMgr = (TelephonyManager)getContext().getSystemService(Context.TELEPHONY_SERVICE);
                 phoneNumber = tMgr.getLine1Number();
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {


//                ActivityCompat.requestPermissions(getActivity(),
//                        new String[]{Manifest.permission.READ_CONTACTS},
//                        READ_CALL_LOG );

            }
        }
        // Take your time to look at all available accounts
            Toast.makeText(getActivity(), "Accounts : " + phoneNumber, Toast.LENGTH_SHORT).show();

//            System.out.println("Accounts : " + acname + ", " + actype+", " + phoneNumber);
        }





    // [START auth_with_facebook]
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        // [START_EXCLUDE silent]
//        showProgressBar();
//        progressDialog.setMessage("Signing in....");
//        progressDialog.show();
        // [END_EXCLUDE]

//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            Toast.makeText(getActivity(), "Authentication success.", Toast.LENGTH_SHORT).show();
//
////                                String first_name = object.getString("first_name");
////                                String last_name = object.getString("last_name");
////                                name = first_name + " " + last_name;
////                                String email = object.getString("email");
////                                String id = object.getString("id");
////                                String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
////                                textView6.setText(email);
////                            txtUsername.setText("First Name: " + first_name + "\nLast Name: " + last_name);
////                            txtEmail.setText(email);
////                            Picasso.with(HomeCycleActivity.this).load(image_url).into(imageView);
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            faceBookSignInButton.setEnabled(true);
//                            faceBookButton.setEnabled(true);
//
//                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            Toast.makeText(getActivity(), "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            faceBookSignInButton.setEnabled(true);
//                            faceBookButton.setEnabled(true);
//
//                            updateUI(null);
//
//                        }
//
//                        // [START_EXCLUDE]
////                        hideProgressBar();
////                        progressDialog.dismiss();
//                        // [END_EXCLUDE]
//
//
//                    }
//
//                });
    }

    private void updateUI() {
//        FirebaseUser user
                        Intent intent = new Intent(getActivity(), HomeCycleActivity.class);
                startActivity(intent);
                getActivity().finish();
                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
                Log.d("API123", loggedIn + " ??");
    }

    // [END auth_with_facebook]
    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("TAG", object.toString());
                        final Profile profile = Profile.getCurrentProfile();

                        if ((object != null) && (profile != null)) {

                            AccessToken accessToken = AccessToken.getCurrentAccessToken();

                            if (accessToken.getDeclinedPermissions().isEmpty()) {


                                try {
                                    faceBookSignInButton.setEnabled(true);
//                            faceBookButton.setEnabled(true);
                                    String first_name = object.getString("first_name");
                                    String last_name = object.getString("last_name");
                                    name = first_name + " " + last_name;
//                                    String email = object.get("email").toString();

//                            String email = object.getString("email");
//                            String phone = object.optString("phone");
                                    String id = object.getString("id");
                                    String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
//                            String email = response.getJSONObject().getString("email");
                                    if (Profile.getCurrentProfile() != null) {
                                        image_url = ImageRequest.getProfilePictureUri(Profile.getCurrentProfile().getId(), 400, 400).toString();
                                    }
                                    String email = null;
                                    String phone = null;
                                    if (object.has("email")) {
                                        email = object.getString("email");
                                    }
                                    if (object.has("display_phone_number")) {
                                        phone = object.getString("display_phone_number");
                                    }
                                    String fullName = Profile.getCurrentProfile().getName();
//                                    textView6.setText(phone);

//                            Toast.makeText(getActivity(), "Email" +first_name+" "+ last_name, Toast.LENGTH_SHORT).show();

                                    updateUI();

//                            txtUsername.setText("First Name: " + first_name + "\nLast Name: " + last_name);
//                            txtEmail.setText(email);
//                            Picasso.with(HomeCycleActivity.this).load(image_url).into(imageView);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            }
                        }
                    });

        Bundle parameters = new Bundle();
//        parameters.putString("fields", "first_name,last_name,email,id");
        parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
        request.setParameters(parameters);
        request.executeAsync();
//        updateUI();

    }


    @Override
    public void onBack() {
        getActivity().finish();
    }
}