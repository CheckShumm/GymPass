package com.example.nathanshumm.gympass;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class yogaActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private Button yogaButton;
    private Window window;

    // Database instance
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);

        window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBurgundy));

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Yoga");

        yogaButton = (Button)findViewById(R.id.yoga_button_pay_A);

        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        yogaButton.setOnClickListener(this);

    }

    // back button functionality
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.yoga_button_pay_A:
                databaseReference.child("Users").child(firebaseUser.getUid()).child("Classes").setValue("Yoga");
                break;
        }
    }
}


