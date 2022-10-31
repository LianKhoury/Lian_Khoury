package com.example.liankhoury;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    // first step in the object programing
    EditText editTextNumber1,editTextNumber2;
    Button buttonPlus,buttonMinus;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // linking objects with design by id
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);

        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);

        textViewResult = findViewById(R.id.textViewResult);

    }

    @SuppressLint("SetTextI18n")
    public void Plus(View view) {
        if(editTextNumber1.getText().toString().equals(""))
            Toast.makeText(this,"Empty number1",Toast.LENGTH_LONG).show();
        else if(editTextNumber2.getText().toString().equals(""))
            Toast.makeText(this,"Empty number2",Toast.LENGTH_LONG).show();
        else {
            double number1 = Double.parseDouble(editTextNumber1.getText().toString());
            double number2 = Double.parseDouble(editTextNumber2.getText().toString());
            textViewResult.setText((number1 + number2) + "");
        }

    }

    public void Minus(View view) {
        if(editTextNumber1.getText().toString().equals(""))
            Toast.makeText(this,"Empty number1",Toast.LENGTH_LONG).show();
        else if(editTextNumber2.getText().toString().equals(""))
            Toast.makeText(this,"Empty number2",Toast.LENGTH_LONG).show();
        else {
            double number1 = Double.parseDouble(editTextNumber1.getText().toString());
            double number2 = Double.parseDouble(editTextNumber2.getText().toString());
            if (number1 > number2)
                textViewResult.setText((number1 - number2) + "");
            else
                textViewResult.setText((number2 - number1) + "");
        }

    }

    public void Return(View view) {
        Intent i  = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}