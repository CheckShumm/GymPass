package com.example.nathanshumm.gympass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeniorPayment extends AppCompatActivity {

    Button doneButton;
    Button regisClassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_payment);

        doneButton=(Button)findViewById(R.id.btn_done);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SeniorPayment.this, MainActivity.class);
                startActivity (i);

            }
        });
        regisClassButton=(Button)findViewById(R.id.btn_registClass);

        regisClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SeniorPayment.this, classActivity.class);
                startActivity (i);

            }
        });
    }

}
