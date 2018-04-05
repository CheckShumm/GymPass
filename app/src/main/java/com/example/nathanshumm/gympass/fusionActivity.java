package com.example.nathanshumm.gympass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fusionActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;

    private Button fusButton_a;
    private Button fusButton_b;


    // Database instance
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fusion);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Fusion");

        fusButton_a = (Button)findViewById(R.id.fusion_button_pay_A);
        fusButton_b = (Button)findViewById(R.id.fusion_button_pay_B);


        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fusButton_a.setOnClickListener(this);
        fusButton_b.setOnClickListener(this);

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
            case R.id.fusion_button_pay_A:
                databaseReference.child("Users").child(firebaseUser.getUid()).child("Classes").setValue("Fusion");
                Intent yogaIntent_a = new Intent(fusionActivity.this, FusionPaymentActivity.class);
                startActivity(yogaIntent_a);
                break;
            case R.id.fusion_button_pay_B:
                databaseReference.child("Users").child(firebaseUser.getUid()).child("Classes").setValue("Fusion");
                Intent yogaIntent_b = new Intent(fusionActivity.this, FusionPaymentActivity.class);
                startActivity(yogaIntent_b);
                break;
        }
    }
}