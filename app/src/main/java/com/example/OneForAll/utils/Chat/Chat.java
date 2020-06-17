package com.example.OneForAll.utils.Chat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.OneForAll.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Chat extends AppCompatActivity {
    private static final String CURRENT_USER_KEY = "CURRENT_USER_KEY";
    AuthenticationRepository authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        authentication = new AuthenticationRepository(FirebaseFirestore.getInstance());

        authenticate();
    }

    private void authenticate() {
        String currentUserKey = getCurrentUserKey();
        if (currentUserKey.isEmpty()) {
            authentication.createNewUser(
                    new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            saveCurrentUserKey(documentReference.getId());
                            Toast.makeText(Chat.this, "New user created", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(
                                    Chat.this,
                                    "Error creating user. Check your internet connection",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
            );
        } else {
            authentication.login(
                    currentUserKey,
                    new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(Chat.this, "Logged in", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(
                                    Chat.this,
                                    "Error signing in. Check your internet connection",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
            );
        }
    }


    private String getCurrentUserKey() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString(CURRENT_USER_KEY, "");
    }

    private void saveCurrentUserKey(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CURRENT_USER_KEY, key);
        editor.apply();
    }
}
