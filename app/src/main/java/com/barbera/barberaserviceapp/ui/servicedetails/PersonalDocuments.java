package com.barbera.barberaserviceapp.ui.servicedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.barbera.barberaserviceapp.R;

public class PersonalDocuments extends AppCompatActivity {
    private EditText aadhar,pan;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_documents);
        aadhar=(EditText) findViewById(R.id.aadhar);
        pan = (EditText) findViewById(R.id.pan);
        submit=(Button) findViewById(R.id.Btn2);

        Intent intent = getIntent();
        String aadh1 = intent.getStringExtra("aadhar");
        String pan1 = intent.getStringExtra("pan");

        if(aadh1!=null){
            aadhar.setText(aadh1);
            pan.setText(pan1);
        }

        submit.setOnClickListener(new View.OnClickListener() {
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
                    editor.putBoolean("personal_doc",true);
                    editor.putString("aadhar",aad);
                    editor.putString("pan",pn);
                    editor.apply();
                    Intent intent = new Intent(PersonalDocuments.this,ServiceDetails.class);
                    startActivity(intent);
                }
            }
        });
    }
}