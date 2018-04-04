package com.example.nathanshumm.gympass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class StudentPayment extends AppCompatActivity {

    Button doneButton;
    Button regisClassButton;
    static TextView textDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_payment);
        doneButton=(Button)findViewById(R.id.btn_done);

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
        cal.add(Calendar.SECOND,40);
        Date nextMonth = cal.getTime();
        String nextMonthString= DateFormat.getTimeInstance().format(nextMonth);
        textDate.setText(nextMonthString);

        //textDate=(TextView) findViewById(R.id.tv_date);
        //Calendar cal = Calendar.set(Calendar.SECOND);
        //Date today = cal.getTime();
        //cal.add(Calendar.SECOND,40);
        //Date nextMonth = cal.getTime();
        //String nextMonthString= DateFormat.getTimeInstance().format(nextMonth);
        //textDate.setText(nextMonthString);

    }

}
