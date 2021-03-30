package com.barbera.barberaserviceapp.ui.servicedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.barbera.barberaserviceapp.R;

public class PersonalDocuments extends AppCompatActivity {
    private EditText aadhar,pan;
    private Button next,prev;
    private ImageButton up_aad,up_pan,photo;
    public static Bitmap aad_bitmap,pan_bitmap,photo_bitmap;
    private String aadh1,pan1;
    private ImageView img1,img2,img3;
    private boolean check1=false,check2=false,check3=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_documents);
        aadhar=(EditText) findViewById(R.id.aadharInp1);
        pan = (EditText) findViewById(R.id.panInp1);
        up_aad = findViewById(R.id.aadharUp);
        up_pan=findViewById(R.id.panUp);
        next= findViewById(R.id.Btn2);
        prev= findViewById(R.id.Btn3);
        photo=findViewById(R.id.photoUp);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);

        SharedPreferences preferences = getSharedPreferences("Details",MODE_PRIVATE);
        aadh1 = preferences.getString("aadhar",null);
        pan1 = preferences.getString("pan",null);
        if(aadh1!=null){
            aadhar.setText(aadh1);
            pan.setText(pan1);
        }

        EnableRuntimePermission();
        up_aad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 6);
            }
        });
        up_pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 5);
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 4);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aad= aadhar.getText().toString();
                String pn= pan.getText().toString();
                if(aad.length()!=14){
                    Toast.makeText(getApplicationContext(),"Please enter valid aadhar card no.",Toast.LENGTH_SHORT).show();
                }
                else if(pn.length()!=10){
                    Toast.makeText(getApplicationContext(),"Please enter valid pan no.",Toast.LENGTH_SHORT).show();
                }
                else if(!check1){
                    Toast.makeText(getApplicationContext(),"Please enter your aadhar photo",Toast.LENGTH_SHORT).show();
                }
                else if(!check2){
                    Toast.makeText(getApplicationContext(),"Please enter your pan photo",Toast.LENGTH_SHORT).show();
                }
                else if(!check3){
                    Toast.makeText(getApplicationContext(),"Please enter your photo",Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences preferences = getSharedPreferences("Details",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("aadhar",aad);
                    editor.putString("pan",pn);
                    editor.apply();
                    Intent intent = new Intent(PersonalDocuments.this,VehicleDetails.class);
                    startActivity(intent);
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(PersonalDocuments.this,PersonalDetails.class);
                startActivity(intent1);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4 && resultCode == RESULT_OK) {
            aad_bitmap = (Bitmap) data.getExtras().get("data");
            img1.setVisibility(View.VISIBLE);
            check1=true;
        }
        if (requestCode == 5 && resultCode == RESULT_OK) {
            pan_bitmap = (Bitmap) data.getExtras().get("data");
            img2.setVisibility(View.VISIBLE);
            check2=true;
        }
        if (requestCode == 6 && resultCode == RESULT_OK) {
            photo_bitmap = (Bitmap) data.getExtras().get("data");
            img3.setVisibility(View.VISIBLE);
            check3=true;
        }
    }

    public void EnableRuntimePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(PersonalDocuments.this,
                Manifest.permission.CAMERA)) {
            Toast.makeText(PersonalDocuments.this,"CAMERA permission allows us to Access CAMERA app",     Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(PersonalDocuments.this,new String[]{
                    Manifest.permission.CAMERA}, Payments.RequestPermissionCode);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
        switch (requestCode) {
            case Payments.RequestPermissionCode:
                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(PersonalDocuments.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(PersonalDocuments.this, "You must grant permission to use the app", Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(PersonalDocuments.this,new String[]{
                            Manifest.permission.CAMERA}, Payments.RequestPermissionCode);
                }
                break;
        }

    }
}