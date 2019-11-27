package com.example.elshamelapp.view.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.elshamelapp.R;
import com.example.elshamelapp.view.Main.MainActivity;
import com.example.elshamelapp.view.Profile.MyProfile;
import com.example.elshamelapp.view.Sign_up.SignUp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {
    @BindView(R.id.signUp)
    TextView signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.signUp)
    void signUp(){

        startActivity(new Intent(Login.this, SignUp.class));

    }
}
