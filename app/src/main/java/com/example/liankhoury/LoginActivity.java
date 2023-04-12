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
if (editTextEmailAddress.getText().toString().equals(""))
        Toast.makeText(this, "Empty Email", Toast.LENGTH_LONG).show();
        else if (editTextPassword.getText().toString().equals(""))
        Toast.makeText(this, "Empty Password", Toast.LENGTH_LONG).show();
        //else if (editTextPersonName.getText().toString().equals(""))
        //    Toast.makeText(this, "Empty UserName", Toast.LENGTH_LONG).show();
        else {
        //this line gets the registered email and password , in case no user was registered empty string is returned
        String input_mail = preferences.getString("email","");
        String input_password = preferences.getString("password","");

        String registeredMail = editTextEmailAddress.getText().toString();
        String registeredPassword = editTextPassword.getText().toString();

        if (input_mail.equals(registeredMail) && input_password.equals(registeredPassword)) {
        Intent i_mail = new Intent(this,WelcomeActivity.class);
        startActivity(i_mail);
        }
        else {
        Toast.makeText(this, "Incorrect credentials!", Toast.LENGTH_SHORT).show();
        }
        }
        }


public void showAlertDialog(FirebaseUser firebaseUser){
        // Setup The Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Email Not Verified");
        builder.setMessage("Please verify your email verification");

        // Open Email Apps if User clicks/taps Continue button

        }
