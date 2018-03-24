package com.example.nathanshumm.gympass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{


    private Toolbar toolbar;
    private Window window;


    private TextView navFirstname;
    private TextView navSurname;
    private TextView navEmail;
    private BottomNavigationView mBottomNav;
    private FrameLayout mainFrame;

    // Database instance
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    // Fragments
    private HomeFragment homeFragment;
    private NotificationFragment notificationFragment;
    private ActivityFragment activityFragment;

    // user data
    private String name;
    private String surname;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("LeGym Pass");

        window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBurgundy));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        // Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        homeFragment = new HomeFragment();
        notificationFragment = new NotificationFragment();
        activityFragment = new ActivityFragment();
         setFragment(homeFragment);

        mainFrame = (FrameLayout)findViewById(R.id.m_Frame);
        mBottomNav = (BottomNavigationView)findViewById(R.id.m_navBar);

        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_activity:
                        setFragment(activityFragment);
                        return true;
                    case R.id.nav_notifications:
                        setFragment(notificationFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });


        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                name = dataSnapshot.child(firebaseUser.getUid()).child("Name").getValue(String.class);
                Log.e("check", "uid: " + firebaseUser.getUid() + " \n" + name );
                surname = dataSnapshot.child(firebaseUser.getUid()).child("Surname").getValue(String.class);
                email = dataSnapshot.child(firebaseUser.getUid()).child("Email").getValue(String.class);
                setNavDrawer();
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


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void setNavDrawer(){
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        View hview = navigationView.getHeaderView(0);
        navFirstname = (TextView)hview.findViewById(R.id.navDrawerName);
        navSurname = (TextView)hview.findViewById(R.id.navDrawerSurname);
        navEmail = (TextView)hview.findViewById(R.id.navDrawerEmail);
        Log.e("checkname","this is nam1: "+ name);
        if(name != null){
            navFirstname.setText(name);
            navSurname.setText(surname);
            navEmail.setText(email);
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.m_Frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_drawer_profile:
                break;
            case R.id.nav_drawer_membership:
                break;
            case R.id.nav_drawer_classes:
                Intent classesIntent = new Intent(this, classActivity.class);
                this.startActivity(classesIntent);
                break;
            case R.id.nav_drawer_notification:
                break;
            case R.id.nav_drawer_settings:
                break;
            case R.id.nav_drawer_log_out:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
