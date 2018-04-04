package com.example.nathanshumm.gympass;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class PublicPayment extends AppCompatActivity {

    private Window window;
    Button doneButton;
    Button regisClassButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_payment);

        window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBurgundy));

        doneButton=(Button)findViewById(R.id.btn_done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PublicPayment.this, MainActivity.class);
                startActivity (i);
            }
        });
        regisClassButton=(Button)findViewById(R.id.btn_registClass);
        regisClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PublicPayment.this, classActivity.class);
                startActivity (i);
            }
        });
    }
}