package com.developer.myownapp.slotting;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class DateandTime extends AppCompatActivity {
    Button pay;
    Intent intent;
    String loc, user, slottime;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    String yearmonthday = "";
    String timeget = "";
    ArrayList<MyObjects> list = new ArrayList<MyObjects>();
    Firebase fire;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dateand_time);
        Firebase.setAndroidContext(this);
        dateView = (TextView) findViewById(R.id.textView3);
        pay = (Button) findViewById(R.id.pay);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        Intent intent = this.getIntent();
        if (intent != null)
            user = intent.getStringExtra("username");

        Intent intent1 = this.getIntent();
        if (intent1 != null)
            slottime = intent.getStringExtra("slottime");

        Intent intent2 = this.getIntent();
        if (intent2 != null)
            loc = intent.getStringExtra("locName");

        Firebase.setAndroidContext(this);
        fire = new Firebase(Config.FIREBASE_URL);

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
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating firebase object
//                Toast.makeText(DateandTime.this, list.size()+"******", Toast.LENGTH_SHORT).show();
                if(checkifthere()==true) {
                    fire = new Firebase(Config.FIREBASE_URL);
                    //Creating MyObject object
                    MyObjects obj = new MyObjects();
                    System.out.println(timeget + "*********************************");
                    //Adding values
                    obj.setUser(user);
                    obj.setLocname(loc);
                    obj.setSlottime(slottime);
                    obj.setDate(timeget);
                    String newslots = user + "*" + loc + "*" + slottime + "*" + timeget;
//                yearmonthday="";

                    //Storing values to firebase
                    fire.push().setValue(obj);
                    startActivity(new Intent(DateandTime.this, Payment.class));
                    finish();
                }else{
                    Toast.makeText(DateandTime.this, "The slot is already booked.Please choose another slot", Toast.LENGTH_SHORT).show();
                }

            }


        });
    }
    public boolean checkifthere(){
        for(int i=0;i<list.size();i++) {
            if(list.get(i).getLocname().equals(loc)){
                if(list.get(i).getDate().equals(timeget)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void gettime(String time) {
        timeget = time;
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        String date = year + "/" + month + "/" + day;
        Date strtdate = null;
        try {
            strtdate = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
                    .parse(date);
//            Toast.makeText(DateandTime.this,strtdate.toString()+"***", Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date cdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat sec = new SimpleDateFormat("yyyy/MM/dd");
        String mdate = sec.format(cdate);
        Date checkdate = null;
        try {
            checkdate = sdf.parse(mdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Toast.makeText(DateandTime.this, checkdate.toString()+"$$$$$", Toast.LENGTH_SHORT).show();
        if (checkdate.compareTo(strtdate) ==1) {
            Toast.makeText(DateandTime.this, "Please choose valid date", Toast.LENGTH_SHORT).show();
        } else {
            dateView.setText(new StringBuilder().append(day).append("/")
                    .append(month).append("/").append(year));
            yearmonthday += dateView.getText().toString();
            String time = year + "/" + month + "/" + day;
            gettime(time);

        }
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
}