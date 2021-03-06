package com.example.nathanshumm.gympass;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotificationFragment extends Fragment {

    LinearLayout parentLinearLayout;
    LinearLayout notificationLayout;

    Button addNotifBtn;
    //Button delNotifBtn;
    //Button delNotifBtn_field;

    // Database instance
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);

        parentLinearLayout = (LinearLayout)rootView.findViewById(R.id.parent_linear_layout);
        //notificationLayout = (LinearLayout)rootView.findViewById(R.id.notif_field);

        addNotifBtn = (Button) rootView.findViewById(R.id.add_notification_button);
        addNotifBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdd(view);
            }
        });

        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        //notificationFragment = new NotificationFragment();

        final EditText notif_text = (EditText) rootView.findViewById(R.id.number_edit_text);
        final Spinner notif_spinner = (Spinner) rootView.findViewById(R.id.type_spinner);

        if(!firebaseUser.getUid().toString().contains("T3VGSX7")){

            addNotifBtn.setVisibility(View.GONE);
            //delNotifBtn.setVisibility(View.GONE);
            //delNotifBtn_field.setVisibility(View.GONE);

            notif_text.setInputType(InputType.TYPE_NULL);
            notif_text.setKeyListener(null);

            notif_spinner.setEnabled(false);
            notif_spinner.setClickable(false);

        }

        return rootView;
    }

    //Add and remove functions
    public void onAdd(View v) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = inflater.inflate(R.layout.field, null);
        parentLinearLayout.addView(viewRow, parentLinearLayout.getChildCount()-1);
    }

}
