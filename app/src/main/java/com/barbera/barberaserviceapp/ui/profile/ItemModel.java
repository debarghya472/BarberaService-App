package com.barbera.barberaserviceapp.ui.profile;

import android.widget.TextView;

import com.barbera.barberaserviceapp.R;

public class ItemModel {
    private String apron,car,disp,mir,neck,tsh,tiss,tri,aflot,bag,clecre,shavapr,shavfoa,date,docId,seen;
    public ItemModel(String apron,String car,String disp,String mir,String neck,String tsh,String tiss,String tri,String aflot,String bag,String clecre,String shavapr,String shavfoa,String date,String docId,String seen){
        this.apron=apron;
        this.car=car;
        this.disp=disp;
        this.mir=mir;
        this.neck=neck;
        this.tiss=tiss;
        this.tri=tri;
        this.tsh=tsh;
        this.aflot=aflot;
        this.bag=bag;
        this.clecre=clecre;
        this.shavapr=shavapr;
        this.shavfoa=shavfoa;
        this.date=date;
        this.docId=docId;
        this.seen=seen;
    }

    public String getDocId() {
        return docId;
    }

    public String getApron() {
        return apron;
    }

    public String getAflot() {
        return aflot;
    }

    public String getBag() {
        return bag;
    }

    public String getCar() {
        return car;
    }

    public String getClecre() {
        return clecre;
    }

    public String getNeck() {
        return neck;
    }

    public String getDisp() {
        return disp;
    }

    public String getShavapr() {
        return shavapr;
    }

    public String getMir() {
        return mir;
    }

    public String getShavfoa() {
        return shavfoa;
    }

    public String getTiss() {
        return tiss;
    }

    public String getTri() {
        return tri;
    }

    public String getTsh() {
        return tsh;
    }

    public String getDate() {
        return date;
    }

    public String getSeen() {
        return seen;
    }
}
