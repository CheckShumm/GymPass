package com.example.nathanshumm.gympass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class classActivity extends AppCompatActivity {

    Button button_fusion;
    Button button_spin;
    Button button_yoga;
    Button button_zumba;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Classes");

        //Fusion Button
        button_fusion = findViewById(R.id.fusion_button);

        button_fusion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(classActivity.this, fusionActivity.class);
                startActivity(myIntent);
            }
        });

        //Spinning Button
        button_spin = findViewById(R.id.spinning_button);

        button_spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(classActivity.this, fusionActivity.class);
                startActivity(myIntent);
            }
        });

        //Yoga Button
        button_yoga = findViewById(R.id.yoga_button);

        button_yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(classActivity.this, yogaActivity.class);
                startActivity(myIntent);
            }
        });

        //Zumba Button
        button_zumba = findViewById(R.id.zumba_button);

        button_zumba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent( classActivity.this, zumbaActivity.class);
                startActivity(myIntent);
            }
        });

    }


}