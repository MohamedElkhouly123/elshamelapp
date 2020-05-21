package com.example.elshamelapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.elshamelapp.R;
import com.example.elshamelapp.view.activity.UserCycleActivity;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static com.example.elshamelapp.data.local.SharedPreferencesManger.clean;


public class LogOutDialog {
//    private ClientData clientData;
private GoogleSignInClient googleSignInClient;

    public void showDialog(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setContentView(R.layout.dialog_sign_out);
        dialog.setCanceledOnTouchOutside(true);
//        TextView text = (TextView) dialog.findViewById(R.id.text);

        TextView dialogImageOk = (TextView) dialog.findViewById(R.id.item_sign_out_dialog_btn_yes);
        dialogImageOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Call
//                clientData = LoadUserData(activity);
                clean(activity);
//                Call<RestaurantCategoryResponse> removetTokenCall = null;
//                String token=new ClientFireBaseToken().getToken();
//                String apiToken=clientData.getApiToken();
//                if (ISCLIENT.equals("true")) {
//
//                    removetTokenCall = getApiClient().clientRemoveToken(token,apiToken);
//                }  if(ISCLIENT=="false") {
//                    removetTokenCall = getApiClient().restaurantRemoveToken(token,apiToken);
//                }
//                deleteAndUpdateItemCallBack(activity,removetTokenCall);
                // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();


// Build a GoogleSignInClient with the options specified by gso.
                googleSignInClient = GoogleSignIn.getClient(activity, gso);

 /*
          Sign-out is initiated by simply calling the googleSignInClient.signOut API. We add a
          listener which will be invoked once the sign out is the successful
           */
                LoginManager.getInstance().logOut();

                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //On Succesfull signout we navigate the user back to LoginActivity
                        Intent intent = new Intent(activity, UserCycleActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivity(intent);
                    }
                });
//                Intent i = new Intent(activity, UserCycleActivity.class);
//                activity.startActivity(i);
//                // close this activity
//                activity.finish();
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        TextView dialogImageNo = (TextView) dialog.findViewById(R.id.item_sign_out_dialog_btn_no);
        dialogImageNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });


        dialog.show();

    }
}
