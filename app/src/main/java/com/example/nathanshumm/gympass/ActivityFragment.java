package com.example.nathanshumm.gympass;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFragment extends Fragment {

    private TextView counterTV;
    // Database instance
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private int gymUsers;

    public ActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View activityView = inflater.inflate(R.layout.fragment_activity, container, false);
        counterTV = (TextView)activityView.findViewById(R.id.counterTV);

        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        updateCounter();
        return activityView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void updateCounter(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gymUsers = dataSnapshot.child("Counter").getValue(Integer.class);
                if(gymUsers < 3){
                    counterTV.setText("Low Crowdedness!");
                    counterTV.setBackgroundColor(getResources().getColor(R.color.colorLightBlue));
                }else if( gymUsers >=3 && gymUsers <5){
                    counterTV.setText("Medium Crowdedness!");
                    counterTV.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                }else if(gymUsers >=5){
                    counterTV.setText("High Crowdedness!");
                    counterTV.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
