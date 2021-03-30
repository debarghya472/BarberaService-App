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
import android.widget.Toast;

import com.barbera.barberaserviceapp.R;

public class PersonalDocuments extends AppCompatActivity {
    private EditText aadhar,pan;
    private Button up_aad,up_pan,next,prev,photo;
    public static Bitmap aad_bitmap,pan_bitmap,photo_bitmap;
    private String name,number,address,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_documents);
        aadhar=(EditText) findViewById(R.id.aadhar);
        pan = (EditText) findViewById(R.id.pan);
        up_aad = findViewById(R.id.up);
        next= findViewById(R.id.Btn2);
        prev= findViewById(R.id.Btn3);

        SharedPreferences preferences = getSharedPreferences("Details",MODE_PRIVATE);
        name = preferences.getString("name",null);
        number = preferences.getString("number",null);
        address = preferences.getString("address",null);
        gender = preferences.getString("gender",null);

        EnableRuntimePermission();

        Intent intent = getIntent();
        String aadh1 = intent.getStringExtra("aadhar");
        String pan1 = intent.getStringExtra("pan");

        if(aadh1!=null){
            aadhar.setText(aadh1);
            pan.setText(pan1);
        }

        up_aad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 7);
            }
        });
        up_pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 8);
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 9);
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
                else{
                    SharedPreferences preferences = getSharedPreferences("Details",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("aadhar",aad);
                    editor.putString("pan",pn);
                    editor.apply();
                    Intent intent = new Intent(PersonalDocuments.this,ServiceDetails.class);
                    startActivity(intent);
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(PersonalDocuments.this,PersonalDetails.class);
                intent1.putExtra("name",name);
                intent1.putExtra("number",number);
                intent1.putExtra("address",address);
                startActivity(intent1);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {
            aad_bitmap = (Bitmap) data.getExtras().get("data");
        }
        if (requestCode == 8 && resultCode == RESULT_OK) {
            pan_bitmap = (Bitmap) data.getExtras().get("data");
        }
        if (requestCode == 9 && resultCode == RESULT_OK) {
            photo_bitmap = (Bitmap) data.getExtras().get("data");
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
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
        switch (requestCode) {
            case Payments.RequestPermissionCode:
                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(PersonalDocuments.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(PersonalDocuments.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}