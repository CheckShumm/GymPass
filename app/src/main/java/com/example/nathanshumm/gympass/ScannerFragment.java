package com.example.nathanshumm.gympass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class ScannerFragment extends Fragment implements View.OnClickListener{

    // Database instance
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private String surname;
    private String name;
    private String classes;
    private String membership;
    private ImageView scannerProfileImage;
    private LinearLayout memberInfo;

    private SharedPreferences sharedPreferences;

    private Button scannerButton;
    private TextView firstNameTV;
    private TextView lastNameTV;
    private TextView membershipTV;
    private TextView classesTV;
    private Space scannerSpace;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View scannerView = inflater.inflate(R.layout.fragment_scanner, container, false);
        scannerButton = (Button)scannerView.findViewById(R.id.qrScanBtn);
        firstNameTV = (TextView)scannerView.findViewById(R.id.scannerFirstName);
        lastNameTV = (TextView)scannerView.findViewById(R.id.scannerLastName);
        membershipTV = (TextView)scannerView.findViewById(R.id.scannerMembership);
        classesTV = (TextView)scannerView.findViewById(R.id.scannerClasses);
        memberInfo = (LinearLayout)scannerView.findViewById(R.id.MemberInfoHolder);
        scannerSpace = (Space)scannerView.findViewById(R.id.scannerSpace);
        scannerProfileImage = (ImageView)scannerView.findViewById(R.id.scannerImage);


        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        sharedPreferences = this.getActivity().getSharedPreferences("profile", Context.MODE_PRIVATE);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e("HERE", "test");
                name = dataSnapshot.child(firebaseUser.getUid()).child("Name").getValue(String.class);
                surname = dataSnapshot.child(firebaseUser.getUid()).child("Surname").getValue(String.class);
                membership = dataSnapshot.child(firebaseUser.getUid()).child("Membership").getValue(String.class);
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

        scannerButton.setOnClickListener(this);
        return scannerView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.qrScanBtn:
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.admin_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.continousScan:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Log.e("fragment scan", "HERE");
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.e("SCAN", result.getContents());
                Toast.makeText(getActivity(), "You cancelled the Scanning!", Toast.LENGTH_LONG).show();
            } else {
                Log.e("SCAN", result.getContents());
                Toast.makeText(getActivity(), result.getContents(), Toast.LENGTH_LONG).show();
                firstNameTV.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void setNameTextView(String firstName, String lastName, String membership, String classes){
        firstNameTV.setText(firstName);
        lastNameTV.setText(lastName);
        membershipTV.setText(membership);
        classesTV.setText(classes);
        scannerSpace.setVisibility(View.GONE);
        memberInfo.setVisibility(View.VISIBLE);
        scannerSpace.setVisibility(View.GONE);

        String URL = sharedPreferences.getString("profileKey",null);
        if(URL != "profileKey" || URL != null) {
            Picasso.with(this.getActivity()).load(URL).fit().centerCrop().into(scannerProfileImage);
            scannerProfileImage.setRotation(270);
        }
    }
}
