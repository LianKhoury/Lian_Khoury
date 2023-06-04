package com.example.liankhoury;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HistoricalBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_books);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}