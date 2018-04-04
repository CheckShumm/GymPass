package com.example.nathanshumm.gympass;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotificationFragment extends Fragment {

    LinearLayout parentLinearLayout;

    Button addNotifBtn;
    Button delNotifBtn;
    Button delNotifBtn_field;

    // Database instance
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    // Fragments
    private NotificationFragment notificationFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);

        parentLinearLayout = (LinearLayout)rootView.findViewById(R.id.parent_linear_layout);

        addNotifBtn = (Button) rootView.findViewById(R.id.add_notification_button);
        addNotifBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdd(view);
            }
        });

        delNotifBtn = (Button) rootView.findViewById(R.id.delete_notification_button);
        delNotifBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDelete(view);
            }
        });

        delNotifBtn_field = (Button) rootView.findViewById(R.id.delete_button);
        delNotifBtn_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDelete(view);
            }
        });


        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        notificationFragment = new NotificationFragment();


        if(firebaseUser.getUid().toString().contains("T3VGSX7")){
            Log.e("UID", firebaseUser.getUid().toString());
            setFragment(notificationFragment);
        }else {
            addNotifBtn.setVisibility(View.GONE);
            delNotifBtn.setVisibility(View.GONE);
            delNotifBtn_field.setVisibility(View.GONE);
        }

        return rootView;
    }

    //Add and remove functions
    public void onAdd(View v) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = inflater.inflate(R.layout.field, null);
        parentLinearLayout.addView(viewRow, parentLinearLayout.getChildCount() - 1);
    }

    public void onDelete(View v) {
        Log.e("DeleteBtn", "Enter OnDelete Function");
        parentLinearLayout.removeView((View) v.getParent());
    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.m_Frame, fragment);
        fragmentTransaction.commit();
    }


}