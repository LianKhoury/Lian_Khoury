package com.example.liankhoury;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ScienceFictionBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_fiction_books);





        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}