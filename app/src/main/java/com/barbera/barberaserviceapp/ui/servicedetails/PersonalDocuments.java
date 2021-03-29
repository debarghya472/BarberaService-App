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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aad= aadhar.getText().toString();
                String pn= pan.getText().toString();
                if(aad.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter your aadhar card no.",Toast.LENGTH_SHORT).show();
                }
                else if(pn.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter your pan no.",Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences preferences = getSharedPreferences("Details",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putBoolean("personal_doc",true);
                    editor.apply();
                    Intent intent = new Intent(PersonalDocuments.this,ServiceDetails.class);
                    intent.putExtra("aadhar",aad);
                    intent.putExtra("pan",pn);
                    startActivity(intent);
                }
            }
        });
    }
}