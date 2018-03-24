package com.example.nathanshumm.gympass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MembershipActivity extends AppCompatActivity {

    Button studentButton;
    Button staffButton;
    Button seniorButton;
    Button publicButton;
    TextView textView;
   // EditText etID;
    //String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);

      //  etID=(EditText)findViewById(R.id.et_id);

        staffButton=(Button)findViewById(R.id.btn_staff);
        staffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MembershipActivity.this, StaffPayment.class);
                startActivity (i);
            }
        });

        seniorButton=(Button)findViewById(R.id.btn_seniors);
        seniorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        studentButton=(Button)findViewById(R.id.btn_student);
        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MembershipActivity.this, StudentPayment.class);
                startActivity (i);
            }
        });

    }

  /*  public boolean validate(){
        boolean valid=true;
        if (ID.isEmpty()||ID.length() != 8) {
            etID.setError("Please put a valid Student ID");
            valid=false;
        }
        else{
            studentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(MembershipActivity.this, StudentPayment.class);
                    startActivity (i);
                }
            });
        }

        return valid;
    }

    public void initialize() {
        ID = etID.getText().toString().trim();
    }
    */
}