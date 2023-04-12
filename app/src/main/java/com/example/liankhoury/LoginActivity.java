package com.example.liankhoury;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmailAddress, editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        //editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextPassword = findViewById(R.id.editTextPassword);



    }
}
