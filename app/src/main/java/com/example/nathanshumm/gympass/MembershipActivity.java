package com.example.nathanshumm.gympass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MembershipActivity extends AppCompatActivity {

    Button studentButton;
    Button staffButton;
    Button seniorButton;
    Button publicButton;

    // Database instance
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    TextView textView;

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
            }
        });

        staffButton=(Button)findViewById(R.id.btn_staff);

        staffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Users").child(firebaseUser.getUid()).child("Membership").setValue("Staff / Faculty");
                Intent i=new Intent(MembershipActivity.this, StaffPayment.class);
                startActivity (i);
            }
        });

        seniorButton=(Button)findViewById(R.id.btn_seniors);

        seniorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Users").child(firebaseUser.getUid()).child("Membership").setValue("Senior");
                Intent i=new Intent(MembershipActivity.this, SeniorPayment.class);
                startActivity (i);
            }
        });

        publicButton=(Button)findViewById(R.id.btn_public);

        publicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MembershipActivity.this, PublicPayment.class);
                startActivity (i);

            }
        });

    }
}
