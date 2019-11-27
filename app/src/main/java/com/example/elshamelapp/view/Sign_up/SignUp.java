package com.example.elshamelapp.view.Sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.elshamelapp.R;
import com.example.elshamelapp.view.Login.Login;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUp extends AppCompatActivity {
    @BindView(R.id.login2)
    TextView login2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.login2)
    void login2(){

        startActivity(new Intent(SignUp.this, Login.class));

    }
}
