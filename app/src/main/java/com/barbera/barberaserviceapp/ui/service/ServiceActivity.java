package com.barbera.barberaserviceapp.ui.service;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.barbera.barberaserviceapp.R;

public class ServiceActivity extends AppCompatActivity {

    private EditText startotp;
    private EditText endotp;
    private CardView startOtpBtn;
    private  CardView endOtpBtn;
    private String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        startotp = findViewById(R.id.editText);
        endotp = findViewById(R.id.editText1);
        startOtpBtn = findViewById(R.id.otp);
        endOtpBtn = findViewById(R.id.otp1);

        amount = getIntent().getExtras().getString("amount");

        startOtpBtn.setOnClickListener(v -> {
            startOtpBtn.setEnabled(false);
            String opt1= startotp.getText().toString();
            if(opt1.equals("1234")){
                endotp.setVisibility(View.VISIBLE);
                endOtpBtn.setVisibility(View.VISIBLE);
                startotp.getText().clear();
            }
        });
        
        endOtpBtn.setOnClickListener(v -> {
            startOtpBtn.setEnabled(false);
            String otp2 = endotp.getText().toString();
            if(otp2.equals("0909")){
                endotp.getText().clear();
                showpayment();
            }
        });



    }

    private void showpayment() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ServiceActivity.this);
        builder.setMessage("Receive Payment of Rs."+amount);
        builder.setIcon(R.drawable.logo);

        builder.setPositiveButton("Paid", (dialog, which) -> {
            dialog.dismiss();
            finish();
        });
        builder.setNegativeButton("Not Paid", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}