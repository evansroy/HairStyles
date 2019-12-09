package com.example.mechanicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Objects;

public class MechanicWeb_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_web_);
        //Objects.requireNonNull(getSupportActionBar()).setTitle("Mechanic Web");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
