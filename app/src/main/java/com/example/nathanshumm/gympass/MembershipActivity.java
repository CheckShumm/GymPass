package com.example.nathanshumm.gympass;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MembershipActivity extends AppCompatActivity {

    Button studentButton;
    Button staffButton;
    Button seniorButton;
    Button publicButton;

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
        setContentView(R.layout.activity_membership);


        window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBurgundy));

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Membership");


        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        studentButton = (Button) findViewById(R.id.btn_student);
        staffButton = (Button) findViewById(R.id.btn_staff);
        seniorButton = (Button) findViewById(R.id.btn_seniors);
        publicButton = (Button) findViewById(R.id.btn_public);



        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference pass = rootRef.child("le-gym-pass");
        DatabaseReference users = rootRef.child("Users");
        users.addListenerForSingleValueEvent(new ValueEventListener() {


            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.child(firebaseUser.getUid()).child("Expiration").exists()) {
                   final String exp=snapshot.child(firebaseUser.getUid()).child("Expiration").getValue(String.class);
                    Toast.makeText(MembershipActivity.this, "You are already have a membership, which expires on: " +exp, Toast.LENGTH_LONG).show();


                    studentButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MembershipActivity.this, "You are already have a membership, which expires on: " +exp , Toast.LENGTH_LONG).show();

                        }
                    });

                    staffButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MembershipActivity.this, "You are already have a membership, which expires on: " +exp , Toast.LENGTH_LONG).show();

                        }
                    });


                    seniorButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MembershipActivity.this, "You are already have a membership, which expires on: " +exp , Toast.LENGTH_LONG).show();

                        }
                    });

                    publicButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MembershipActivity.this, "You are already have a membership, which expires on: " +exp , Toast.LENGTH_LONG).show();

                        }
                    });


                } else {

                    studentButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            databaseReference.child("Users").child(firebaseUser.getUid()).child("Membership").setValue("Student");
                            Intent i = new Intent(MembershipActivity.this, StudentPayment.class);
                            startActivity(i);
                            finish();
                        }
                    });


                    staffButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            databaseReference.child("Users").child(firebaseUser.getUid()).child("Membership").setValue("Staff / Faculty");
                            Intent i = new Intent(MembershipActivity.this, StaffPayment.class);
                            startActivity(i);
                            finish();
                        }
                    });


                    seniorButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            databaseReference.child("Users").child(firebaseUser.getUid()).child("Membership").setValue("Senior");
                            Intent i = new Intent(MembershipActivity.this, SeniorPayment.class);
                            startActivity(i);
                            finish();
                        }
                    });

                    publicButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MembershipActivity.this, PublicPayment.class);
                            startActivity(i);
                            finish();

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}

