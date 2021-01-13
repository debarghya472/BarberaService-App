package com.barbera.barberaserviceapp.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.barbera.barberaserviceapp.MainActivity;
import com.barbera.barberaserviceapp.R;

public class LoginActivity extends AppCompatActivity {
    private CardView skipLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        skipLogin =(CardView)findViewById(R.id.skip_login);

        skipLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipLogin.setEnabled(false);
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }
}