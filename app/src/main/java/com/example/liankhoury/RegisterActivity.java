package com.example.liankhoury;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmailAddress,editTextPassword;
    private TextView registrationForm;
    private Button buttonCancel,buttonRegister;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);

        registrationForm = findViewById(R.id.registrationForm);

        buttonCancel = findViewById(R.id.buttonCancel);
        buttonRegister = findViewById(R.id.buttonRegister);

        preferences = getSharedPreferences("Userinfo",0);
    }

    public void registerR(View view){
        String input_mail = editTextEmailAddress.getText().toString();
        String input_password = editTextPassword.getText().toString();
        if(input_mail.length()>0){
            //open preferences file
            SharedPreferences.Editor editor = preferences.edit();
            //save key , value , data
            editor.putString("email",input_mail);
            editor.putString("password",input_password);

            editor.apply();
            Toast.makeText(this, "user registered!", Toast.LENGTH_LONG).show();
            Intent intent_main = new Intent(this,MainActivity.class);
            startActivity(intent_main);
        }
        else{
            Toast.makeText(this, "Empty values, please insert!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel(View view) {
        Intent intent_main = new Intent(this,MainActivity.class);
        startActivity(intent_main);
    }
}
//public void register(View view) {
//        String registeredMail = editTextEmailAddress.getText().toString();
//        String registeredPassword= editTextPassword.getText().toString();
//        //save the email value in the preferences and commits the file
//        preferences.edit().putString("email",registeredMail).commit();
//        preferences.edit().putString("password",registeredPassword).commit();
//        Intent i_register = new Intent(this,RegisterActivity.class);
//        startActivity(i_register);
//    }