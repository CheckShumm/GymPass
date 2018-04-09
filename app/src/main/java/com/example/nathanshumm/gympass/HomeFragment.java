package com.example.nathanshumm.gympass;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    public String QRCode;

    // Database instance
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private Boolean registeredUser = false;
    private SharedPreferences sharedPreferences;
    private TextView noRegistrationTV;
    private String name = "none";
    private LinearLayout qrLayout;
    private Button registerButton;
    private Button qrGenButton;

    private ImageView qrCodeImage;
    private int qrCounter = 0;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        sharedPreferences = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        // Register Button
        noRegistrationTV = (TextView) homeView.findViewById(R.id.tv_noRegister);
        registerButton = (Button) homeView.findViewById(R.id.registerBtn);
        qrGenButton = (Button) homeView.findViewById(R.id.qrGenBtn);
        qrCodeImage = (ImageView) homeView.findViewById(R.id.qrCodeImage);
        qrLayout = (LinearLayout) homeView.findViewById(R.id.qr_layout);

        qrGenButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        return homeView;
    }

    @Override
    public void onResume() {
        if(name != null && name != "none"){
            registerButton.setVisibility(View.GONE);
            noRegistrationTV.setVisibility(View.GONE);
            generateQR();
        }
        super.onResume();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.registerBtn:
                Intent registerIntent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(registerIntent);
                break;

            case R.id.qrGenBtn:

                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(QRCode, BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    qrCodeImage.setImageBitmap(bitmap);

                }
                catch   (WriterException e){
                    e.printStackTrace();
                }

                break;
        }
    }

    public void generateQR(){
        QRCode = firebaseUser.getUid().toString();
        qrLayout.setVisibility(View.VISIBLE);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(QRCode, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrCodeImage.setImageBitmap(bitmap);

        }
        catch   (WriterException e){
            e.printStackTrace();
        }
    }

    public boolean registered(){
       // name = sharedPreferences.getString("nameKey",null);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(firebaseUser.getUid()).child("Name").exists()){
                    Log.e("child", "haschild");
                    registeredUser = true;
                }else{
                    Log.e("child", "hasNoChild");
                    registeredUser = false;
                }

                name = dataSnapshot.child(firebaseUser.getUid()).child("Name").getValue(String.class);
                Log.e("UIDCHECK",firebaseUser.getUid());
                if(name !=null){
                    Log.e("nameCheck", name);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return name != "none";
    }

    public void userRegistered(String newName){
        name = newName;
        registerButton.setVisibility(View.GONE);
        noRegistrationTV.setVisibility(View.GONE);
        generateQR();
    }


}
