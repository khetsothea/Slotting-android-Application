package com.developer.myownapp.slotting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Payment extends AppCompatActivity implements View.OnClickListener {
    Button sbank, hbank, pay, cont, sout;
    EditText verifyemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sbank = (Button) findViewById(R.id.sbank);
        pay = (Button) findViewById(R.id.pay);
        hbank = (Button) findViewById(R.id.hbank);
        sout = (Button) findViewById(R.id.sout);

        pay.setEnabled(false);
        sbank.setOnClickListener(this);
        hbank.setOnClickListener(this);
        pay.setOnClickListener(this);
        sout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sbank) {
            pay.setEnabled(true);
        }
        if (v.getId() == R.id.pay) {
            Toast.makeText(Payment.this, "Slot is successfully Booked", Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.hbank) {
            pay.setEnabled(true);
        }
        if (v.getId() == R.id.sout) {
            Toast.makeText(this, "Thank You", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
        }

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
}

