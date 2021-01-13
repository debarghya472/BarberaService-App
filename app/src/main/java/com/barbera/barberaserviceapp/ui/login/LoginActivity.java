package com.barbera.barberaserviceapp.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.barbera.barberaserviceapp.MainActivity;
import com.barbera.barberaserviceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private CardView skipLogin;
    private EditText email;
    private EditText password;
    private CardView login;
    private String emailPattern="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        skipLogin =(CardView)findViewById(R.id.skip_login);
        email =(EditText)findViewById(R.id.email) ;
        password =(EditText)findViewById(R.id.Password);
        login =(CardView) findViewById(R.id.Login);
        firebaseAuth=FirebaseAuth.getInstance();

        skipLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipLogin.setEnabled(false);
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEmailAndPassword()){
                    final ProgressDialog progressDialog=new ProgressDialog(LoginActivity.this);
                    progressDialog.setMessage("Logging You In...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    login.setEnabled(false);
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        progressDialog.dismiss();
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                        finish();
                                    }
                                    else{
                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(),"Error Credentials",Toast.LENGTH_SHORT).show();
                                        login.setEnabled(true);
                                        /*new_user.setEnabled(true);
                                        login_using_phone.setEnabled(true);
                                        withoutLogin.setEnabled(true);*/
                                    }
                                }
                            });

                }
            }
        });
    }

    private boolean checkEmailAndPassword() {
        if(email.getText().toString().isEmpty()){
            email.setError("Please Enter a Registered Email Address");
            email.requestFocus();
            return false;
        }
        if(password.getText().toString().isEmpty()||password.getText().toString().length()<8){
            password.setError("Please Enter a Valid Password");
            password.requestFocus();
            return false;
        }
        if(email.getText().toString().matches(emailPattern)){
            return true;
        }
        else{
            email.setError("Enter a Valid Email Address");
            return false;
        }
    }
}