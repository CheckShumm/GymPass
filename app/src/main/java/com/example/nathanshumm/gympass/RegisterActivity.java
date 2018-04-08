package com.example.nathanshumm.gympass;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.microedition.khronos.egl.EGL;

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
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private StorageReference profileRef;

    private Button payButton;
    private Button photoButton;
    private ImageView imageView;
    private Window window;

    private Uri downloadUrl;

    //Button btnpic;
    //ImageView imgTakenPic;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int CAM_REQUEST=1313;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBurgundy));

        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        profileRef = storageReference.child(firebaseUser.getUid());

        email = (EditText) findViewById(R.id.et_email);
        name = (EditText) findViewById(R.id.et_name);
        phone = (EditText) findViewById(R.id.et_phoneNmb);
        surname = (EditText) findViewById(R.id.et_surname);
        payButton = (Button) findViewById(R.id.btn_next);
        //final int CAM_REQUEST= 1;
        imageView= (ImageView) findViewById(R.id.iv_picture);

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
        photoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activeTakePhoto();
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            imageView.setRotation(270);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteData = baos.toByteArray();
        UploadTask uploadTask = profileRef.putBytes(byteData);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.e("Upload", "uploading image");
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                downloadUrl = taskSnapshot.getDownloadUrl();
                SharedPreferences sharedPreferences = getSharedPreferences("profile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("profileKey",downloadUrl.toString());
                editor.apply();
            }
        });

    }


    private void activeTakePhoto() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 110);
        } else {
            takePicture();
        }

    }

    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 22);
        Log.e("CAM", "Camera Check1");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 110) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePicture();
            }
        }}


    public void register() {
        initialize();

        if (!validate()) {

            Toast.makeText(this, "Registration has failed", Toast.LENGTH_SHORT).show();
        }
        else {
            onSignupSuccess();
        }
    }
    public void onSignupSuccess(){
        databaseReference.child("Users").child(firebaseUser.getUid()).child("Name").setValue(etname);
        databaseReference.child("Users").child(firebaseUser.getUid()).child("Surname").setValue(etsurname);
        databaseReference.child("Users").child(firebaseUser.getUid()).child("profileURL").setValue(downloadUrl.toString());

        Intent i = new Intent (RegisterActivity.this, ChooseActivity.class);
        startActivity (i);
        finish();
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

        if (imageView.getDrawable()==null){
            Toast.makeText(this, "Please take a profile picture", Toast.LENGTH_SHORT).show();
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