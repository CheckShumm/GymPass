package com.example.nathanshumm.gympass;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private Window window;
    private EditText email;
    private EditText password;
    private FirebaseAuth firebaseAuth;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));

        email = (EditText)findViewById(R.id.et_loginEmail);
        password = (EditText)findViewById(R.id.et_loginPassword);
        loginButton = (Button)findViewById(R.id.loginBtn);
        firebaseAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        password.setText(null);
    }

    @Override
    public void onClick(View v) {
        final ProgressDialog progressDialog = ProgressDialog.show(LogInActivity.this, "Please wait...", "Processing...", true);
        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(LogInActivity.this, "Username or password field is empty!", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if (task.isSuccessful()) {
                                Toast.makeText(LogInActivity.this, "Log in Successful", Toast.LENGTH_SHORT).show();
                                Intent mainActivityIntent = new Intent(LogInActivity.this, MainActivity.class);
                                LogInActivity.this.startActivity(mainActivityIntent);
                            } else {
                                Log.e("Auth_Err", task.getException().getMessage());
                                Toast.makeText(LogInActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
