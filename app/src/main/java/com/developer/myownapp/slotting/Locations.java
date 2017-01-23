package com.developer.myownapp.slotting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Locations extends AppCompatActivity implements View.OnClickListener{
    Button vSlot,tslot,mslot;
    String emailVerify;
    Intent intent=getIntent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        vSlot = (Button) findViewById(R.id.vSlot);
        tslot = (Button) findViewById(R.id.tslot);
        mslot = (Button) findViewById(R.id.mslot);
        Intent intent=this.getIntent();
        if(intent !=null){
            emailVerify = intent.getStringExtra("username");
        }
        vSlot.setOnClickListener(this);
        tslot.setOnClickListener(this);
        mslot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        System.out.println(v.getId()+"*****************");
        if(v.getId()== R.id.vSlot) {
            String locname = "vindya";
            Intent myIntent = new Intent(this, Slots.class);
            myIntent.putExtra("Vindya",locname);
            myIntent.putExtra("email",emailVerify);
            startActivity(myIntent);
        }else if(v.getId() == R.id.tslot) {
            String locname = "thub";
            Intent myIntent = new Intent(this, Slots.class);
            myIntent.putExtra("Vindya",locname);
            myIntent.putExtra("email",emailVerify);
            startActivity(myIntent);

        }else if(v.getId() == R.id.mslot) {
            String locname = "maingate";
            Intent myIntent = new Intent(this, Slots.class);
            myIntent.putExtra("Vindya",locname);
            myIntent.putExtra("email",emailVerify);
            startActivity(myIntent);
        }
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
}
