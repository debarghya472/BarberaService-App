package com.barbera.barberaserviceapp.ui.servicedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.barbera.barberaserviceapp.R;
import com.barbera.barberaserviceapp.SplashActivity;
import com.barbera.barberaserviceapp.ui.login.LoginActivity;
import com.barbera.barberaserviceapp.ui.service.ImageVerifyActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static com.barbera.barberaserviceapp.LiveLocationService.person;

public class ServiceDetails extends AppCompatActivity {
    private CardView personal_details,personal_documents,vehicle_details;
    private Button submit;


    private ImageView img1,img2,img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        personal_details=(CardView) findViewById(R.id.pd);
        personal_documents=(CardView) findViewById(R.id.ppd);
        vehicle_details=(CardView) findViewById(R.id.vd);
        submit=(Button) findViewById(R.id.Btn);
        img1=findViewById(R.id.image);
        img2=findViewById(R.id.image2);
        img3=findViewById(R.id.image3);



        personal_documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ServiceDetails.this,PersonalDocuments.class);

                startActivity(intent);
            }
        });
        personal_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ServiceDetails.this,PersonalDetails.class);

                startActivity(intent);
            }
        });
        vehicle_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ServiceDetails.this,VehicleDetails.class);
                startActivity(intent);
            }
        });


    }

}