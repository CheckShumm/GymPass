package com.example.nathanshumm.gympass;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.view.Window;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class classActivity extends AppCompatActivity implements View.OnClickListener{

    Button button_fusion;
    Button button_spin;
    Button button_yoga;
    Button button_zumba;

    private Window window;
    private Toolbar toolbar;


    private Date expireDate;
    private String expiry = "Aug 5, 2020";
    private Date nextMonth;

    // Database instance
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBurgundy));

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Classes");

        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        button_fusion = (Button)findViewById(R.id.fusion_button);
        button_fusion.setOnClickListener(this);

        button_spin = (Button)findViewById(R.id.spinning_button);
        button_spin.setOnClickListener(this);

        button_yoga = (Button)findViewById(R.id.yoga_button);
        button_yoga.setOnClickListener(this);

        button_zumba = (Button)findViewById(R.id.zumba_button);
        button_zumba.setOnClickListener(this);

    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fusion_button:
                Intent fusionIntent = new Intent(this, fusionActivity.class);
                this.startActivity(fusionIntent);
                break;
            case R.id.zumba_button:
                Intent zumbaIntent = new Intent(this, zumbaActivity.class);
                this.startActivity(zumbaIntent);
                break;
            case R.id.spinning_button:
                Intent spinningIntent = new Intent(this, spinningActivity.class);
                this.startActivity(spinningIntent);
                break;
            case R.id.yoga_button:
                Intent yogaIntent = new Intent(this, yogaActivity.class);
                this.startActivity(yogaIntent);
                break;
        }
    }
}