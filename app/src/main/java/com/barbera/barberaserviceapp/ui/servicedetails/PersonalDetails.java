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

public class PersonalDetails extends AppCompatActivity {
    private EditText name,number,address;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        name=(EditText) findViewById(R.id.name);
        number=(EditText) findViewById(R.id.contact1);
        address=(EditText) findViewById(R.id.add);
        submit=(Button) findViewById(R.id.Btn1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm = name.getText().toString();
                String num = number.getText().toString();
                String add = address.getText().toString();
                if(nm.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter your name",Toast.LENGTH_SHORT).show();
                }
                else if(add.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter your address",Toast.LENGTH_SHORT).show();
                }
                else if(num.length()!=10){
                    Toast.makeText(getApplicationContext(),"Please enter a valid mobile number",Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences preferences = getSharedPreferences("Details",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putBoolean("personal_det",true);
                    editor.putString("name",nm);
                    editor.putString("number",num);
                    editor.putString("address",add);
                    editor.apply();
                    Intent intent = new Intent(PersonalDetails.this,ServiceDetails.class);
                    startActivity(intent);
                }
            }
        });
    }
}