package com.barbera.barberaserviceapp.ui.service;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.barbera.barberaserviceapp.R;
import com.barbera.barberaserviceapp.network.JsonPlaceHolderApi;
import com.barbera.barberaserviceapp.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ServiceActivity extends AppCompatActivity {

    private EditText startotp;
    private EditText endotp;
    private CardView startOtpBtn;
    private  CardView endOtpBtn;
    private String name;
    private String service;
    private String time;
    private String address;
    private String amount;
    private int id;
    private String date;
    private String contact;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        sharedPreferences=getSharedPreferences("ServiceInfo",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        startotp = findViewById(R.id.editText);
        endotp = findViewById(R.id.editText1);
        startOtpBtn = findViewById(R.id.otp);
        endOtpBtn = findViewById(R.id.otp1);

        name= getIntent().getExtras().getString("name");
        service = getIntent().getExtras().getString("service");
        time = getIntent().getExtras().getString("time");
        address = getIntent().getExtras().getString("address");
        amount = getIntent().getExtras().getString("amount");
        id= getIntent().getExtras().getInt("id");
        date =getIntent().getExtras().getString("date");
        contact = getIntent().getExtras().getString("contact");

        startOtpBtn.setOnClickListener(v -> {
            startOtpBtn.setEnabled(false);
            String opt1= startotp.getText().toString();
            if(opt1.equals("1234")){
                startotp.setVisibility(View.INVISIBLE);
                startOtpBtn.setVisibility(View.INVISIBLE);
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

        int amt = Integer.parseInt(amount);

        builder.setPositiveButton("Paid", (dialog, which) -> {
            int pay= sharedPreferences.getInt("payment",0);
            int trip= sharedPreferences.getInt("trips",0);
            editor.putInt("payment",pay+amt);
            editor.putInt("trips",trip+1);
            editor.commit();
            updateInDb(name,service,time,address,amount,id,date,contact);
            dialog.dismiss();
        });
        builder.setNegativeButton("Not Paid", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();

    }
    private void updateInDb(String name, String service, String time, String address, String amount, int id, String date, String contact) {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        final ProgressDialog progressDialog=new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Cancelling Booking!!");
        progressDialog.show();
        progressDialog.setCancelable(true);
        Call<String> call = jsonPlaceHolderApi.updateAssignee(name,service,time,address,amount,"debarghya","update",2,id,date,contact);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(),"NOt Success",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"N Success",Toast.LENGTH_SHORT).show();
            }
        });
    }
}