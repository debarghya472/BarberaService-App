package com.barbera.barberaserviceapp.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.barbera.barberaserviceapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ItemList extends AppCompatActivity {
    private TextView apron,car,disp,mir,neck,tsh,tiss,tri,aflot,bag,clecre,shavapr,shavfoa;
    private TextView it1,it2,it3,it4,it5,it6,it7,it8,it9,it10,it11,it12,it13;
    private String itm1,itm2,itm3,itm4,itm5,itm6,itm7,itm8,itm9,itm10,itm11,itm12,itm13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        apron=findViewById(R.id.apron);
        it1=findViewById(R.id.apronT);
        car=findViewById(R.id.carpet);
        it13=findViewById(R.id.carpetT);
        mir=findViewById(R.id.mirror);
        it2=findViewById(R.id.mirrorT);
        disp=findViewById(R.id.disposable);
        it3=findViewById(R.id.disposableT);
        neck=findViewById(R.id.necktape);
        it4=findViewById(R.id.necktapeT);
        tsh=findViewById(R.id.tshirt);
        it5=findViewById(R.id.tshirtT);
        tiss=findViewById(R.id.tissue);
        it6=findViewById(R.id.tissueT);
        tri=findViewById(R.id.tripod);
        it7=findViewById(R.id.tripodT);
        aflot=findViewById(R.id.afterlotion);
        it8=findViewById(R.id.afterlotionT);
        bag=findViewById(R.id.bag);
        it9=findViewById(R.id.bagT);
        clecre=findViewById(R.id.cleansingcream);
        it10=findViewById(R.id.cleansingcreamT);
        shavapr=findViewById(R.id.shavingapron);
        it11=findViewById(R.id.shavingapronT);
        shavfoa=findViewById(R.id.shavingfoam);
        it12=findViewById(R.id.shavingfoamT);

        FirebaseFirestore.getInstance().collection("Service").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Item")
                .document("list").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                itm1=documentSnapshot.getString("Apron");
                if(!itm1.equals("0")){
                    it1.setVisibility(View.VISIBLE);
                    apron.setText(itm1);
                    apron.setVisibility(View.VISIBLE);
                }
                else{
                    it1.setVisibility(View.GONE);
                    apron.setVisibility(View.GONE);
                }
                itm13= documentSnapshot.getString("Carpet");
                if(!itm13.equals("0")){
                    it13.setVisibility(View.VISIBLE);
                    car.setText(itm13);
                    car.setVisibility(View.VISIBLE);
                }
                else{
                    it13.setVisibility(View.GONE);
                    car.setVisibility(View.GONE);
                }
                itm2= documentSnapshot.getString("Disposable");
                if(!itm2.equals("0")){
                    it2.setVisibility(View.VISIBLE);
                    disp.setText(itm2);
                    disp.setVisibility(View.VISIBLE);
                }
                else{
                    it2.setVisibility(View.GONE);
                    disp.setVisibility(View.GONE);
                }
                itm3= documentSnapshot.getString("Mirror");
                if(!itm3.equals("0")){
                    it3.setVisibility(View.VISIBLE);
                    mir.setText(itm3);
                    mir.setVisibility(View.VISIBLE);
                }
                else{
                    it3.setVisibility(View.GONE);
                    mir.setVisibility(View.GONE);
                }
                itm4= documentSnapshot.getString("Neck Tape");
                if(!itm4.equals("0")){
                    it4.setVisibility(View.VISIBLE);
                    neck.setText(itm4);
                    neck.setVisibility(View.VISIBLE);
                }
                else{
                    it4.setVisibility(View.GONE);
                    neck.setVisibility(View.GONE);
                }
                itm5= documentSnapshot.getString("T-shirt");
                if(!itm5.equals("0")){
                    it5.setVisibility(View.VISIBLE);
                    tsh.setText(itm5);
                    tsh.setVisibility(View.VISIBLE);
                }
                else{
                    it5.setVisibility(View.GONE);
                    tsh.setVisibility(View.GONE);
                }
                itm6= documentSnapshot.getString("Tissue");
                if(!itm6.equals("0")){
                    it6.setVisibility(View.VISIBLE);
                    tiss.setText(itm6);
                    tiss.setVisibility(View.VISIBLE);
                }
                else{
                    it6.setVisibility(View.GONE);
                    tiss.setVisibility(View.GONE);
                }
                itm7= documentSnapshot.getString("Tripod");
                if(!itm7.equals("0")){
                    it7.setVisibility(View.VISIBLE);
                    tri.setText(itm7);
                    tri.setVisibility(View.VISIBLE);
                }
                else{
                    it7.setVisibility(View.GONE);
                    tri.setVisibility(View.GONE);
                }
                itm8= documentSnapshot.getString("after lotion");
                if(!itm8.equals("0")){
                    it8.setVisibility(View.VISIBLE);
                    aflot.setText(itm8);
                    aflot.setVisibility(View.VISIBLE);
                }
                else{
                    it8.setVisibility(View.GONE);
                    aflot.setVisibility(View.GONE);
                }
                itm9= documentSnapshot.getString("bag");
                if(!itm9.equals("0")){
                    it9.setVisibility(View.VISIBLE);
                    bag.setText(itm9);
                    bag.setVisibility(View.VISIBLE);
                }
                else{
                    it9.setVisibility(View.GONE);
                    bag.setVisibility(View.GONE);
                }
                itm10= documentSnapshot.getString("cleansing cream");
                if(!itm10.equals("0")){
                    it10.setVisibility(View.VISIBLE);
                    clecre.setText(itm10);
                    clecre.setVisibility(View.VISIBLE);
                }
                else{
                    it10.setVisibility(View.GONE);
                    clecre.setVisibility(View.GONE);
                }
                itm11= documentSnapshot.getString("shaving apron");
                if(!itm11.equals("0")){
                    it11.setVisibility(View.VISIBLE);
                    shavapr.setText(itm11);
                    shavapr.setVisibility(View.VISIBLE);
                }
                else{
                    it11.setVisibility(View.GONE);
                    shavapr.setVisibility(View.GONE);
                }
                itm12= documentSnapshot.getString("shaving foam");
                if(!itm12.equals("0")){
                    it12.setVisibility(View.VISIBLE);
                    shavfoa.setText(itm12);
                    shavfoa.setVisibility(View.VISIBLE);
                }
                else{
                    it12.setVisibility(View.GONE);
                    shavfoa.setVisibility(View.GONE);
                }
            }
        });
    }
}