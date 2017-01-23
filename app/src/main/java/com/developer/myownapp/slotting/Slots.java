package com.developer.myownapp.slotting;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.authentication.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class Slots extends AppCompatActivity implements View.OnClickListener {
    Button mrnslot,noonslot,nytslot,showb;
    EditText slotemail;
    Intent intent=getIntent();
    Date checkTime;
    String userId;
    String loc,userEmail;
    ArrayList<MyObjects> list = new ArrayList<MyObjects>();
    DatabaseReference db;
    ArrayList<MyObjects> list1 = new ArrayList<>();
    Firebase fire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slots);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);
        fire = new Firebase(Config.FIREBASE_URL);
//        loc = intent.getStringExtra("Vindya");
        Intent intent=this.getIntent();
        if(intent !=null) {
            loc = intent.getStringExtra("Vindya");
            userEmail = intent.getStringExtra("email");
        }
//        Toast.makeText(Slots.this, userEmail, Toast.LENGTH_SHORT).show();
        slotemail = (EditText) findViewById(R.id.slotemail);
        mrnslot = (Button) findViewById(R.id.mrnslot);
        noonslot = (Button) findViewById(R.id.noonslot);
        nytslot = (Button) findViewById(R.id.nytslot);
        showb = (Button) findViewById(R.id.showb);
        mrnslot.setOnClickListener(this);
        noonslot.setOnClickListener(this);
        nytslot.setOnClickListener(this);
        showb.setOnClickListener(this);

        fire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                MyObjects obj =  dataSnapshot.getValue(MyObjects.class);
                list.add(obj);
//                Toast.makeText(Slots.this,obj.getUser()+"*****", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                list.clear();
                MyObjects obj =  dataSnapshot.getValue(MyObjects.class);
                list.add(obj);
//                Toast.makeText(Slots.this,obj.getUser()+"*****", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {
                list.clear();
                MyObjects obj =  dataSnapshot.getValue(MyObjects.class);
                list.add(obj);
            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


//        list1=retrieve();
//        printall();

    }

    public void showMessage(String title,String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.mrnslot) {
            int count = list.size();
//            for(MyObjects k: list) {
////            if(k!=null) {
//                Toast.makeText(Slots.this,k.getUser()+"*****", Toast.LENGTH_SHORT).show();
////            }
//            }

//           Toast.makeText(Slots.this, count+"******", Toast.LENGTH_SHORT).show();
            String name = slotemail.getText().toString();
//            Toast.makeText(Slots.this,name, Toast.LENGTH_SHORT).show();
            if(name.equals(userEmail)) {
            String slot = "morning slot";
            Intent myIntent = new Intent(this, DateandTime.class);
            myIntent.putExtra("username",name);
            myIntent.putExtra("slottime",slot);
            myIntent.putExtra("locName",loc);
            startActivity(myIntent);
            }
            else{
                Toast.makeText(Slots.this,"Please provide valid email id", Toast.LENGTH_SHORT).show();
            }

        }
        if(v.getId()==R.id.showb) {
            ArrayList<MyObjects> sho = new ArrayList<>();
            String name = slotemail.getText().toString();
            for(int i=0;i<list.size();i++) {
                if(list.get(i).getUser().equals(name)) {
                    sho.add(list.get(i));
                }
            }
            if(sho.size()==0){
                showMessage("Sorry","No slots found");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            int lsize = sho.size();
            int count =0;
            while(count<lsize) {
                buffer.append("User:"+sho.get(count).getUser()+"\n");
                buffer.append("location:"+sho.get(count).getLocname()+"\n");
                buffer.append("Date :"+sho.get(count).getDate()+"\n");
                buffer.append("slot time :"+sho.get(count).getSlottime()+"\n\n");
                count++;
            }
            showMessage("User Bookings",buffer.toString());
        }
        if(v.getId()==R.id.noonslot) {

            String name = slotemail.getText().toString();
            if(name.equals(userEmail)){
                String slot = "noon slot";
                Intent myIntent = new Intent(this, DateandTime.class);
                myIntent.putExtra("username",name);
                myIntent.putExtra("slottime",slot);
                myIntent.putExtra("locName",loc);
                startActivity(myIntent);}else{
                Toast.makeText(Slots.this,"Please provide valid email id", Toast.LENGTH_SHORT).show();
            }
        }
        if(v.getId()==R.id.nytslot) {
            String name = slotemail.getText().toString();
            if(name.equals(userEmail)){
                String slot = "night slot";
                Intent myIntent = new Intent(this, DateandTime.class);
                myIntent.putExtra("username",name);
                myIntent.putExtra("slottime",slot);
                myIntent.putExtra("locName",loc);
                startActivity(myIntent);}else{
                Toast.makeText(Slots.this,"Please provide valid email id", Toast.LENGTH_SHORT).show();
            }
        }


    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
}
