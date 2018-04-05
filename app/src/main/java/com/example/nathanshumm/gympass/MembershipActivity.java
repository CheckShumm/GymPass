package com.example.nathanshumm.gympass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MembershipActivity extends AppCompatActivity {

    Button studentButton;
    Button staffButton;
    Button seniorButton;
    Button publicButton;
    private String expiry = "none";
    //TextView textDate;

    // Database instance
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);



        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        studentButton=(Button)findViewById(R.id.btn_student);

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Users").child(firebaseUser.getUid()).child("Membership").setValue("Student");
                Intent i=new Intent(MembershipActivity.this, StudentPayment.class);
                startActivity (i);
                finish();
            }
        });

        staffButton=(Button)findViewById(R.id.btn_staff);

        staffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Users").child(firebaseUser.getUid()).child("Membership").setValue("Staff / Faculty");
                Intent i=new Intent(MembershipActivity.this, StaffPayment.class);
                startActivity (i);
                finish();
            }
        });

        seniorButton=(Button)findViewById(R.id.btn_seniors);

        seniorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Users").child(firebaseUser.getUid()).child("Membership").setValue("Senior");
                Intent i=new Intent(MembershipActivity.this, SeniorPayment.class);
                startActivity (i);
                finish();
            }
        });

        publicButton=(Button)findViewById(R.id.btn_public);


        publicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MembershipActivity.this, PublicPayment.class);
                startActivity (i);
                finish();

            }
        });


        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.MONTH,0);
        Date nextMonth = cal.getTime();
        String nextMonthString= DateFormat.getDateInstance().format(nextMonth);



        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e("HERE", "test");
                expiry = dataSnapshot.child(firebaseUser.getUid()).child("Expiration").getValue(String.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addChildEventListener(childEventListener);

        if(expiry != nextMonthString){
            Toast.makeText(MembershipActivity.this, "You already a member", Toast.LENGTH_LONG).show();
        }





        //DateFormat newDate = new SimpleDateFormat("MM/dd/yyyy");
        //Date date = new Date ();
        //startDate = newDate.format(date);
/*
        textDate=(TextView) findViewById(R.id.tv_date);
      //  String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
      // textDate.setText(currentDateTimeString);

        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.MONTH,4);
        Date nextMonth = cal.getTime();
        String nextMonthString= DateFormat.getDateInstance().format(nextMonth);
        textDate.setText(nextMonthString);
  */
    }
}
