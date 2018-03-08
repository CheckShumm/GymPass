package com.example.nathanshumm.gympass;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    private Button registerButton;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        // Register Button
        registerButton = (Button) homeView.findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(this);

        return homeView;
    }


    @Override
    public void onClick(View v) {
        Log.d("ERR", "WE HERE at least?");
        switch (v.getId()){
            case R.id.registerBtn:
                Log.d("ERR", "WE HERE?");
                Intent registerIntent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(registerIntent);
                break;
        }
    }
}
