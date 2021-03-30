package com.barbera.barberaserviceapp.ui.servicedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.barbera.barberaserviceapp.R;

import retrofit2.http.HEAD;

public class PersonalDetails extends AppCompatActivity {
    private EditText name,number,address;
    private Button next;
    private CheckBox male,female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        name=(EditText) findViewById(R.id.name);
        number=(EditText) findViewById(R.id.contact1);
        address=(EditText) findViewById(R.id.add);

        next=findViewById(R.id.Btn2);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        Intent intent = getIntent();
        String name1 = intent.getStringExtra("name");
        String num1 = intent.getStringExtra("number");
        String add1 = intent.getStringExtra("address");
        String gender = intent.getStringExtra("gender");

        if(name1!=null){
            name.setText(name1);
            number.setText(num1);
            address.setText(add1);
            if(gender.equals("Male")){
                male.setChecked(true);
                female.setChecked(false);
            }
            else{
                male.setChecked(false);
                female.setChecked(true);
            }
        }
        if(male.isChecked()){
            female.setChecked(false);
        }
        if(female.isChecked()){
            male.setChecked(false);
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm = name.getText().toString();
                String num = number.getText().toString();
                String add = address.getText().toString();
                if(!(male.isChecked() || female.isChecked())){
                    Toast.makeText(getApplicationContext(),"Please select your gender",Toast.LENGTH_SHORT).show();
                }
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
                    editor.putString("name",nm);
                    editor.putString("number",num);
                    editor.putString("address",add);
                    if(male.isChecked()){
                        editor.putString("gender","Male");
                    }
                    else{
                        editor.putString("gender","Female");
                    }
                    editor.apply();
                    Intent intent = new Intent(PersonalDetails.this,PersonalDocuments.class);
                    startActivity(intent);
                }
            }
        });
    }
}