package com.example.nathanshumm.gympass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class yogaActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private Button yogaButton_a;
    private Button yogaButton_b;
    private Button yogaButton_c;

    // Database instance
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Yoga");

        yogaButton_a = (Button)findViewById(R.id.yoga_button_pay_A);
        yogaButton_b = (Button)findViewById(R.id.yoga_button_pay_B);
        yogaButton_c = (Button)findViewById(R.id.yoga_button_pay_C);

        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        yogaButton_a.setOnClickListener(this);
        yogaButton_b.setOnClickListener(this);
        yogaButton_c.setOnClickListener(this);

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
                Intent yogaIntent_a = new Intent(yogaActivity.this, YogaPaymentActivity.class);
                startActivity(yogaIntent_a);
                break;
            case R.id.yoga_button_pay_B:
                databaseReference.child("Users").child(firebaseUser.getUid()).child("Classes").setValue("Yoga");
                Intent yogaIntent_b = new Intent(yogaActivity.this, YogaPaymentActivity.class);
                startActivity(yogaIntent_b);
                break;
            case R.id.yoga_button_pay_C:
                databaseReference.child("Users").child(firebaseUser.getUid()).child("Classes").setValue("Yoga");
                Intent yogaIntent_c = new Intent(yogaActivity.this, YogaPaymentActivity.class);
                startActivity(yogaIntent_c);
                break;
        }
    }
}
