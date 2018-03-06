package com.example.nathanshumm.gympass;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class LauncherActivity extends AppCompatActivity implements View.OnClickListener{

    private Window window;
    private Button signupButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));

        signupButton = (Button)findViewById(R.id.la_signUpBtn);
        loginButton = (Button)findViewById(R.id.la_loginBtn);

        signupButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.la_signUpBtn:
                // go to signup activity
                Intent signupItent = new Intent(this,SignUpActivity.class);
                this.startActivity(signupItent);
                break;
            case R.id.la_loginBtn:
                // log in button functionality
                Intent loginIntent = new Intent(this, LogInActivity.class);
                this.startActivity(loginIntent);
                break;
        }
    }
}
