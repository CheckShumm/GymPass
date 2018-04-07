package com.example.nathanshumm.gympass;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class SeniorPayment extends AppCompatActivity {

    Button doneButton;
    Button regisClassButton;
    private Window window;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_payment);

        window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBurgundy));

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Senior Payment");


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
