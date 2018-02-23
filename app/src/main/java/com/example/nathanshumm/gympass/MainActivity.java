package com.example.nathanshumm.gympass;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private Window window;

    private BottomNavigationView mBottomNav;
    private FrameLayout mainFrame;

    // Fragments
    private HomeFragment homeFragment;
    private NotificationFragment notificationFragment;
    private ActivityFragment activityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("LeGym Pass");

        window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorDarkGrey));

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
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.m_Frame, fragment);
        fragmentTransaction.commit();
    }
}
