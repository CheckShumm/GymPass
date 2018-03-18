package com.example.nathanshumm.gympass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PayActivity extends AppCompatActivity {

    private CheckBox student, staff, seniors, cbPublic, spinning, yoga, fusion, zumba;
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
/*
        student = (CheckBox) findViewById(R.id.cb_student);
        staff = (CheckBox) findViewById(R.id.cb_staff);
        seniors = (CheckBox) findViewById(R.id.cb_seniors);
        cbPublic = (CheckBox) findViewById(R.id.cb_public);

        spinning = (CheckBox) findViewById(R.id.cb_spinning);
        yoga = (CheckBox) findViewById(R.id.cb_yoga);
        fusion = (CheckBox) findViewById(R.id.cb_fusion);
        zumba = (CheckBox) findViewById(R.id.cb_zumba);


        student.setOnClickListener(this);
        staff.setOnClickListener(this);
        seniors.setOnClickListener(this);
        cbPublic.setOnClickListener(this);
        spinning.setOnClickListener(this);
        yoga.setOnClickListener(this);
        fusion.setOnClickListener(this);
        zumba.setOnClickListener(this);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Payment");

    }



    public void onClick(View view){
        switch (view.getId()){
            case R.id.cb_student:
                if(student.isChecked())
                    Toast.makeText(getApplicationContext(), "Total is $70.00", Toast.LENGTH_LONG).show();
                break;
            case R.id.cb_public:
                if(cbPublic.isChecked())
                    Toast.makeText(getApplicationContext(), "Total is $160.00", Toast.LENGTH_LONG).show();
                break;

            case R.id.cb_seniors:
                if(seniors.isChecked())
                    Toast.makeText(getApplicationContext(), "Total is $99.00", Toast.LENGTH_LONG).show();
                break;

            case R.id.cb_staff:
                if(staff.isChecked())
                    Toast.makeText(getApplicationContext(), "Total is $130.00", Toast.LENGTH_LONG).show();
                break;

        }

    }

    public void gymOnClick (View view){
        if(student.isChecked() || staff.isChecked() || seniors.isChecked() || cbPublic.isChecked()){

        }*/
    }


}