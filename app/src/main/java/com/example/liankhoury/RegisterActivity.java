package com.example.liankhoury;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText editText_registerEmail, editText_registerPassword, editText_registerPhoneNum, editText_registerFullName, editText_registerConfirm_Password;
    private Button buttonCancel;
    private ProgressBar progressBar;
    private static final String TAG = "RegisterActivity";

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //getSupportActionBar().setTitle("Register");

        Toast.makeText(RegisterActivity.this, "you can register now", Toast.LENGTH_LONG).show();

        editText_registerEmail = findViewById(R.id.editText_registerEmail);
        editText_registerPassword = findViewById(R.id.editText_registerPassword);
        editText_registerConfirm_Password = findViewById(R.id.editText_registerConfirm_Password);
        editText_registerFullName = findViewById(R.id.editText_registerFullName);
        editText_registerPhoneNum = findViewById(R.id.editText_registerPhoneNum);

        progressBar = findViewById(R.id.ProgressBar);

        buttonCancel = findViewById(R.id.buttonCancel);


        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtain the entered data
                String textFullName = editText_registerFullName.getText().toString();
                String textEmail = editText_registerEmail.getText().toString();
                String textPwd = editText_registerPassword.getText().toString();
                String textConfirmPwd = editText_registerConfirm_Password.getText().toString();
                String textPhoneNum = editText_registerPhoneNum.getText().toString();


                // Validate Mobile Number using matcher and Pattern (Regular Expression)
                String mobileRegex = "[0][5][0-9]{7}";
                Matcher mobileMatcher;
                Pattern mobilePattern = Pattern.compile(mobileRegex);
                mobileMatcher = mobilePattern.matcher(textPhoneNum);


                if (TextUtils.isEmpty(textFullName)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your full name", Toast.LENGTH_LONG).show();
                    editText_registerFullName.setError("Full Name is required");
                    editText_registerFullName.requestFocus();
                } else if (TextUtils.isEmpty(textEmail)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your email", Toast.LENGTH_LONG).show();
                    editText_registerEmail.setError("Email Address is required");
                    editText_registerEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                    Toast.makeText(RegisterActivity.this, "Please re-enter your email", Toast.LENGTH_LONG).show();
                    editText_registerEmail.setError("Valid email Address is required");
                    editText_registerEmail.requestFocus();
                } else if (TextUtils.isEmpty(textPhoneNum)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your phone number", Toast.LENGTH_LONG).show();
                    editText_registerPhoneNum.setError("Phone Number is required");
                    editText_registerPhoneNum.requestFocus();
                } else if (textPhoneNum.length() != 10) {
                    Toast.makeText(RegisterActivity.this, "Please re-enter your phone number", Toast.LENGTH_LONG).show();
                    editText_registerPhoneNum.setError("Phone Number should be 10 digits");
                    editText_registerPhoneNum.requestFocus();
                } else if (!mobileMatcher.find()) {
                    Toast.makeText(RegisterActivity.this, "Please re-enter your phone number", Toast.LENGTH_LONG).show();
                    editText_registerPhoneNum.setError("Phone Number is not valid");
                    editText_registerPhoneNum.requestFocus();
                } else if (TextUtils.isEmpty(textPwd)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your password", Toast.LENGTH_LONG).show();
                    editText_registerPassword.setError("Password is required");
                    editText_registerPassword.requestFocus();
                } else if (textPwd.length() < 8) {
                    Toast.makeText(RegisterActivity.this, "password should be at least 8 digits", Toast.LENGTH_LONG).show();
                    editText_registerPassword.setError("password too weak");
                    editText_registerPassword.requestFocus();
                } else if (TextUtils.isEmpty(textConfirmPwd)) {
                    Toast.makeText(RegisterActivity.this, "Please confirm your password", Toast.LENGTH_LONG).show();
                    editText_registerConfirm_Password.setError("password confirmation is required");
                    editText_registerConfirm_Password.requestFocus();
                } else if (!textPwd.equals(textConfirmPwd)) {
                    Toast.makeText(RegisterActivity.this, "Please make sure you entered the same password", Toast.LENGTH_LONG).show();
                    editText_registerConfirm_Password.setError("password confirmation is required");
                    editText_registerConfirm_Password.requestFocus();
                    // clear the entered password
                    editText_registerPassword.clearComposingText();
                    editText_registerConfirm_Password.clearComposingText();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    registerUser(textFullName, textEmail, textPhoneNum, textPwd);
                }

            }
        });

        preferences = getSharedPreferences("Userinfo", 0);
    }

    public void registerUser(String textFullName, String textEmail, String textPhoneNum, String textPwd) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        // Create user Profile
        auth.createUserWithEmailAndPassword(textEmail, textPwd).addOnCompleteListener(RegisterActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_LONG).show();
                            FirebaseUser firebaseUser = auth.getCurrentUser();

                            // Update Display Name of User
                            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(textFullName).build();
                            firebaseUser.updateProfile(profileChangeRequest);

                            // Enter User data into the Firebase Realtime Database
                            ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(textPhoneNum);

                            //Extracting User Data into the firebase Realtime Database.
                            DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered User");

                            referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        // send Verification Email
                                        firebaseUser.sendEmailVerification();

                                        Toast.makeText(RegisterActivity.this,"User registered successfully. Please verify your email",Toast.LENGTH_LONG).show();

                                        /*// open User profile after successful registration
                                        Intent intent = new Intent(RegisterActivity.this,UserProfileActivity.class);
                                        // to prevent user from returning back to Register Activity on pressing back button after registration
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish(); //to close Register Activity*/
                                    } else {
                                        Toast.makeText(RegisterActivity.this,"User registered failed. please try again",Toast.LENGTH_LONG).show();
                                    }
                                    // Hide ProgressBar whether User creation is successful or failed
                                    progressBar.setVisibility(View.GONE);
                                }
                            });

                        } else {
                            try{
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e){
                                editText_registerPassword.setError(" your password is too weak. kindly use a mix of alphabets, numbers and special characters");
                                editText_registerPassword.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e){
                                editText_registerPassword.setError(" your password is invalid or already in use. kindly re-enter.");
                                editText_registerPassword.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e){
                                editText_registerPassword.setError(" user is already registered with this password. Use another password ");
                                editText_registerPassword.requestFocus();
                            } catch (Exception e){
                                Log.e(TAG, e.getMessage());
                                Toast.makeText(RegisterActivity.this, e.getMessage() , Toast.LENGTH_LONG).show();
                            }
                            // Hide ProgressBar whether User creation is successful or failed
                            progressBar.setVisibility(View.GONE);
                        }


                    }
                });
    }
}



    /*public void registerR(View view){
        String input_mail = editText_registerEmail.getText().toString();
        String input_password = editText_registerPassword.getText().toString();
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
    }*/

   /* public void cancel(View view) {
        Intent intent_main = new Intent(this,MainActivity.class);
        startActivity(intent_main);
    }*/
//}
/*//public void register(View view) {
//        String registeredMail = editTextEmailAddress.getText().toString();
//        String registeredPassword= editTextPassword.getText().toString();
//        //save the email value in the preferences and commits the file
//        preferences.edit().putString("email",registeredMail).commit();
//        preferences.edit().putString("password",registeredPassword).commit();
//        Intent i_register = new Intent(this,RegisterActivity.class);
//        startActivity(i_register);
//    }*/