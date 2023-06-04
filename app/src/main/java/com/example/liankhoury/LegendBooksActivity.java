package com.example.liankhoury;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LegendBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legend_books);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}