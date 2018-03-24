package com.example.nathanshumm.gympass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class ChooseActivity extends AppCompatActivity {

    private Button memberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        memberButton=(Button)findViewById(R.id.btn_membership);

        memberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ChooseActivity.this, MembershipActivity.class);
                startActivity (i);
            }
        });
    }
}

