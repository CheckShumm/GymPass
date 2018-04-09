package com.example.nathanshumm.gympass;


import android.app.Activity;
import android.graphics.Color;
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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;


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
    private GraphView graphView;

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
        graphView = (GraphView)activityView.findViewById(R.id.activityGraph);

        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        return activityView;
    }

    @Override
    public void onStart() {
        Activity activity = getActivity();
        if(isAdded() && activity != null) {
            updateCounter();
        }
        super.onStart();
    }

    public void updateCounter(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gymUsers = dataSnapshot.child("Counter").getValue(Integer.class);
                setGraph(gymUsers);
                if(gymUsers < 3){
                    counterTV.setText("Low Crowdedness!");
                    counterTV.setBackgroundColor(Color.rgb(66,179,244));
                }else if( gymUsers >=3 && gymUsers <5){
                    counterTV.setText("Medium Crowdedness!");
                    counterTV.setBackgroundColor(Color.rgb(255,174,0));
                }else if(gymUsers >=5){
                    counterTV.setText("High Crowdedness!");
                    counterTV.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void setGraph(int counter){

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 2),
                new DataPoint(2, 4),
                new DataPoint(3, counter),
                new DataPoint(4, 8),
                new DataPoint(5, 4),
                new DataPoint(6, 2),
        });
        graphView.addSeries(series);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(10);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        staticLabelsFormatter.setVerticalLabels(new String[] {"Low", "Med", "High"});
        staticLabelsFormatter.setHorizontalLabels(new String[] {"7a", "10a", "1p", "4p", "7p", "10p"});
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/7), 200);
            }
        });

        series.setSpacing(5);
        series.setDrawValuesOnTop(true);
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.removeAllSeries();
        graphView.addSeries(series);
    }

}
