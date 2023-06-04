package com.example.liankhoury;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RomanceBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romance_books);





        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}