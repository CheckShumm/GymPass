package com.example.nathanshumm.gympass;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class spinningActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private Window window;

    private Button spinButton_a;
    private Button spinButton_b;
    private Button spinButton_c;
    private Button spinButton_d;

    // Database instance
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinning);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Spinning");

        window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBurgundy));

        spinButton_a = (Button)findViewById(R.id.spinning_pay_A);
        spinButton_b = (Button)findViewById(R.id.spinning_button_pay_B);
        spinButton_c = (Button)findViewById(R.id.spinning_button_pay_C);
        spinButton_d = (Button) findViewById(R.id.spinning_button_pay_D);

        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spinButton_a.setOnClickListener(this);
        spinButton_b.setOnClickListener(this);
        spinButton_c.setOnClickListener(this);
        spinButton_d.setOnClickListener(this);

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
            case R.id.spinning_pay_A:
                databaseReference.child("Users").child(firebaseUser.getUid()).child("Classes").setValue("Spinning");
                Intent spinIntent_a = new Intent(spinningActivity.this, SpinningPaymentActivity.class);
                startActivity(spinIntent_a);
                break;
            case R.id.spinning_button_pay_B:
                databaseReference.child("Users").child(firebaseUser.getUid()).child("Classes").setValue("Spinning");
                Intent spinIntent_b = new Intent(spinningActivity.this, SpinningPaymentActivity.class);
                startActivity(spinIntent_b);
                break;
            case R.id.spinning_button_pay_C:
                databaseReference.child("Users").child(firebaseUser.getUid()).child("Classes").setValue("Spinning");
                Intent spinIntent_c = new Intent(spinningActivity.this, SpinningPaymentActivity.class);
                startActivity(spinIntent_c);
                break;
            case R.id.spinning_button_pay_D:
                databaseReference.child("Users").child(firebaseUser.getUid()).child("Classes").setValue("Spinning");
                Intent spinIntent_d = new Intent(spinningActivity.this, SpinningPaymentActivity.class);
                startActivity(spinIntent_d);
                break;
        }
    }
}