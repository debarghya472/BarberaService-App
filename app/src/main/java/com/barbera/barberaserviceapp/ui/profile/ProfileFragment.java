package com.barbera.barberaserviceapp.ui.profile;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.barbera.barberaserviceapp.MainActivity;
import com.barbera.barberaserviceapp.R;
import com.barbera.barberaserviceapp.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragment extends Fragment {

    private TextView name;
    private TextView email;
    private TextView phone;
    private TextView earnings;
    private TextView points;
    private  TextView trips;
    private CardView logout;
    private  CardView login;
    private String profilename=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_profile,container,false);
       name = (TextView)view.findViewById(R.id.NameInProfile);
       email = (TextView)view.findViewById(R.id.EmailInProfile);
       phone =(TextView)view.findViewById(R.id.PhoneInProfile);
       earnings =(TextView)view.findViewById(R.id.earning);
       points =(TextView)view.findViewById(R.id.points);
       trips =(TextView)view.findViewById(R.id.trip);
       logout=(CardView)view.findViewById(R.id.logout);
       login =(CardView)view.findViewById(R.id.login);

       displayProfile();
       return view;
    }

    private void displayProfile() {
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            login.setVisibility(View.VISIBLE

            );
            Toast.makeText(getContext(),"User Not Logged In",Toast.LENGTH_SHORT).show();
        }else if(profilename==null){
            FirebaseFirestore.getInstance().collection("Service").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        name.setText(task.getResult().get("name").toString());
                        email.setText(task.getResult().get("email").toString());
                        phone.setText(task.getResult().get("phone").toString());
                        earnings.setText(task.getResult().get("earnings").toString());
                        points.setText(task.getResult().get("points").toString());
                        trips.setText(task.getResult().get("trips").toString());
                        name.setVisibility(View.VISIBLE);
                        email.setVisibility(View.VISIBLE);
                        phone.setVisibility(View.VISIBLE);
                        earnings.setVisibility(View.VISIBLE);
                        points.setVisibility(View.VISIBLE);
                        trips.setVisibility(View.VISIBLE);
                        logout.setVisibility(View.VISIBLE);
                    }
                }
            });
        }else {
            name.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            phone.setVisibility(View.VISIBLE);
            earnings.setVisibility(View.VISIBLE);
            points.setVisibility(View.VISIBLE);
            trips.setVisibility(View.VISIBLE);
            logout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setMessage("Logout From This Device??");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });
    }
}
