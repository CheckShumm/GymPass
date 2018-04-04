package com.example.nathanshumm.gympass;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ZumbaPaymentActivity extends AppCompatActivity {

    Button doneButton;
    Button regisClassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zumba_payment);

        doneButton=(Button)findViewById(R.id.zumba_btn_done);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ZumbaPaymentActivity.this, MainActivity.class);
                startActivity (i);

            }
        });
        regisClassButton=(Button)findViewById(R.id.zumba_btn_registClass);

        regisClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ZumbaPaymentActivity.this, classActivity.class);
                startActivity (i);

            }
        });
    }

}
