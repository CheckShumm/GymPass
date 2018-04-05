package com.example.nathanshumm.gympass;


import android.content.Context;
import android.content.Intent;
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

        // Register Button
        noRegistrationTV = (TextView) homeView.findViewById(R.id.tv_noRegister);
        registerButton = (Button) homeView.findViewById(R.id.registerBtn);
        qrGenButton = (Button) homeView.findViewById(R.id.qrGenBtn);
        qrCodeImage = (ImageView) homeView.findViewById(R.id.qrCodeImage);
        qrLayout = (LinearLayout) homeView.findViewById(R.id.qr_layout);

        qrGenButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);


        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e("HERE", "test");
                name = dataSnapshot.child(firebaseUser.getUid()).child("Name").getValue(String.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        databaseReference.addChildEventListener(childEventListener);

        if(registered()){
            registerButton.setVisibility(View.GONE);
            noRegistrationTV.setVisibility(View.GONE);
            generateQR();
        }

//        Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.homeFragmentID);
//        if (currentFragment instanceof HomeFragment ){
//            FragmentTransaction fragTransaction = (getActivity()).getSupportFragmentManager().beginTransaction();
//            fragTransaction.detach(currentFragment);
//            fragTransaction.attach(currentFragment);
//            fragTransaction.commit();
//        }

        return homeView;
    }

    @Override
    public void onResume() {
        if(registered()){
            registerButton.setVisibility(View.GONE);
            noRegistrationTV.setVisibility(View.GONE);
            generateQR();
        }
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        if(registered()){
            registerButton.setVisibility(View.GONE);
            noRegistrationTV.setVisibility(View.GONE);
            generateQR();
        }
        super.onAttach(context);
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
        Log.e("name:   ", name);
        return name != "none";
    }

}
