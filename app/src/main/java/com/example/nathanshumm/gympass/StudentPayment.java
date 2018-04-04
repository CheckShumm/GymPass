package com.example.nathanshumm.gympass;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class StudentPayment extends AppCompatActivity {

    Button doneButton;
    Button regisClassButton;

    // Database instance
    private Window window;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    static TextView textDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_payment);
        doneButton=(Button)findViewById(R.id.btn_done);

        window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBurgundy));

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentPayment.this, MainActivity.class);
                startActivity (i);
            }
        });
        regisClassButton=(Button)findViewById(R.id.btn_registClass);

        regisClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentPayment.this, classActivity.class);
                startActivity (i);

            }
        });

        textDate=(TextView) findViewById(R.id.tv_date);
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.MONTH,4);
        Date nextMonth = cal.getTime();
        String nextMonthString= DateFormat.getDateInstance().format(nextMonth);
        textDate.setText(nextMonthString);


        databaseReference.child("Users").child(firebaseUser.getUid()).child("Expiration").setValue(nextMonthString);


        //textDate=(TextView) findViewById(R.id.tv_date);
        //Calendar cal = Calendar.set(Calendar.SECOND);
        //Date today = cal.getTime();
        //cal.add(Calendar.SECOND,40);
        //Date nextMonth = cal.getTime();
        //String nextMonthString= DateFormat.getTimeInstance().format(nextMonth);
        //textDate.setText(nextMonthString);

    }

}
