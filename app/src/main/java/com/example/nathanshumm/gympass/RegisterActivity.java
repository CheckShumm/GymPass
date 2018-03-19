package com.example.nathanshumm.gympass;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText surname;
    private EditText email;
    private EditText phone;
    private Toolbar toolbar;
    private String etname, etsurname, etemail, etphone;

    // Database instance
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private Button payButton;
    private Button photoButton;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        email = (EditText) findViewById(R.id.et_email);
        name = (EditText) findViewById(R.id.et_name);
        phone = (EditText) findViewById(R.id.et_phoneNmb);
        surname = (EditText) findViewById(R.id.et_surname);
        payButton = (Button) findViewById(R.id.btn_next);
        final int CAM_REQUEST= 1;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registration");
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

            }
        });




        photoButton = (Button) findViewById(R.id.btn_picture);
        imageView = (ImageView) findViewById(R.id.iv_picture);

        photoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file =getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent,CAM_REQUEST);
            }
        });
    }

    private File getFile(){
        File folder= new File ("sdcard/camera_app");

        if(!folder.exists()){
            folder.mkdir();
        }

        File image_file = new File(folder,"cam_image.jpg");
        return image_file;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        String path = "sdcard/camera_app/cam_image.jpg";

        imageView.setImageDrawable(Drawable.createFromPath(path));
    }

        public void register() {
            initialize();

        if (!validate()) {

            Toast.makeText(this, "Signup has Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            onSignupSuccess();
        }
        }
        public void onSignupSuccess(){
            databaseReference.child("Users").child(firebaseUser.getUid()).child("Name").setValue(etname);
            databaseReference.child("Users").child(firebaseUser.getUid()).child("Surname").setValue(etsurname);
            Intent i = new Intent (RegisterActivity.this, PayActivity.class);
            startActivity (i);
    }

    public boolean validate(){
        boolean valid=true;
        if (etname.isEmpty() || etname.length()>32) {
        name.setError("Please enter valid name");
        valid=false;
        }
        if (etsurname.isEmpty() || etsurname.length()>32) {
            surname.setError("Please enter valid name");
            valid=false;
        }
        if (etemail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(etemail).matches()) {
            email.setError("Please enter valid email address");
            valid = false;
        }

        if (etphone.isEmpty() ||etphone.length() !=10 ) {

            phone.setError("Please enter valid phone number");
            valid=false;
        }


        return valid;
}
    public void initialize(){
        etname=name.getText().toString().trim();
        etsurname=surname.getText().toString().trim();
        etemail=email.getText().toString().trim();
        etphone=phone.getText().toString().trim();

    }

    public void saveToMemory(){
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nameKey",etname);
        editor.apply();
        editor.putString("surnameKey", etsurname);
        editor.commit();
    }

}