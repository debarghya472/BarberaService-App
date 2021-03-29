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
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        personal_details=(CardView) findViewById(R.id.pd);
        personal_documents=(CardView) findViewById(R.id.ppd);
        vehicle_details=(CardView) findViewById(R.id.vd);
        submit=(Button) findViewById(R.id.Btn);

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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("Details",MODE_PRIVATE);
                boolean ver1 = preferences.getBoolean("personal_det",false);
                boolean ver2 = preferences.getBoolean("personal_doc",false);
                boolean ver3 = preferences.getBoolean("vehicle_det",false);
                name = preferences.getString("name",null);
                String number = preferences.getString("number",null);
                String address = preferences.getString("address",null);
                String aadhar = preferences.getString("aadhar",null);
                String pan = preferences.getString("pan",null);

                Toast.makeText(getApplicationContext(),name+" "+aadhar+" "+ver1+" "+ver2+" "+ver3,Toast.LENGTH_SHORT).show();
                Bitmap rc= VehicleDetails.rc_bitmap;
                Bitmap license= VehicleDetails.lic_bitmap;
                if(ver1 && ver2 && ver3){
                    //uploadImageToFirebase(rc);
                    //uploadImageToFirebase(license);
                    Map<String,Object> mp = new HashMap<>();
                    mp.put("name",name);
                    mp.put("number",number);
                    mp.put("address",address);
                    mp.put("aadhar",aadhar);
                    mp.put("pan",pan);
                    String id =FirebaseFirestore.getInstance().collection("Barber details").document().getId();
                    FirebaseFirestore.getInstance().collection("Barber details").document(id).set(mp)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ServiceDetails.this, LoginActivity.class);
                                    SharedPreferences sharedPreferences = getSharedPreferences("Details",MODE_PRIVATE);
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putBoolean("details_conf",true);
                                    editor.apply();
                                    startActivity(intent);
                                }
                            });
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please fill in the required details",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void uploadImageToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        ProgressDialog progressDialog
                = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();


        StorageReference ref = storageReference.child("barbers/"+name);

        ref.putBytes(data)
                .addOnSuccessListener(taskSnapshot -> {
                    progressDialog.dismiss();
                    Toast.makeText(ServiceDetails.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(ServiceDetails.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                })
                .addOnProgressListener(snapshot -> {
                    double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded"+(int)progress+"%");
                });
    }
}