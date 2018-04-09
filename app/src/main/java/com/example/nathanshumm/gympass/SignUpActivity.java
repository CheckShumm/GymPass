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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private Window window;
    private EditText email;
    private EditText password;
    private EditText passwordVerify;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
    private DatabaseReference  databaseReference;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));

        email = (EditText)findViewById(R.id.et_signupEmail);
        password = (EditText)findViewById(R.id.et_signupPassword);
        passwordVerify = (EditText)findViewById(R.id.et_signupPassword2);
        loginButton = (Button)findViewById(R.id.signUpBtn);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseUser = firebaseAuth.getCurrentUser();

        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(SignUpActivity.this , "Username or password field is empty!", Toast.LENGTH_SHORT).show();
        } else if(!password.getText().toString().equals(passwordVerify.getText().toString())) {
            Log.e("password", password.getText().toString() + "\n" + passwordVerify.getText().toString());
            Toast.makeText(SignUpActivity.this , "Passwords do not match please try again!", Toast.LENGTH_SHORT).show();
            password.setText("");
            passwordVerify.setText("");
        }else{
                final ProgressDialog progressDialog = ProgressDialog.show(SignUpActivity.this, "Please wait...", "Processing...", true);
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();

                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    writeNewUser();
                                    Intent loginActivityIntent = new Intent(SignUpActivity.this, LogInActivity.class);
                                    SignUpActivity.this.startActivity(loginActivityIntent);
                                    SignUpActivity.this.finish();
                                } else {
                                    Log.e("Registration_Err", task.getException().getMessage());
                                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });

        }
    }

    public void writeNewUser(){
        String userId;
        String email;
        userId = firebaseUser.getUid();
        email = firebaseUser.getEmail();
        Log.e("email err: ", email);
        databaseReference.child("Users").child(userId).child("Email").setValue(email);
    }
}