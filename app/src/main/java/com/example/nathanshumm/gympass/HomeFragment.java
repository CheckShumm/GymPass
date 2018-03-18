package com.example.nathanshumm.gympass;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

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

        // Register Button
        registerButton = (Button) homeView.findViewById(R.id.registerBtn);
        qrGenButton = (Button) homeView.findViewById(R.id.qrGenBtn);
        qrCodeImage = (ImageView) homeView.findViewById(R.id.qrCodeImage);
        qrGenButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        return homeView;
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
                    BitMatrix bitMatrix = multiFormatWriter.encode("helloThisIsATest", BarcodeFormat.QR_CODE, 200, 200);
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
}
