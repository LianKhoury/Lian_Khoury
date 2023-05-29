package com.example.liankhoury;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmailAddress, editTextPassword;
    private ProgressBar progressBar;
    private FirebaseAuth authProfile;
    private ImageView ImageView_show_hide_password;
    private Button buttonLogin;
    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("login");

        editTextEmailAddress = findViewById(R.id.editText_login_email);
        editTextPassword = findViewById(R.id.editText_login_password);
        progressBar = findViewById(R.id.progressBar);

        authProfile = FirebaseAuth.getInstance();

        // Show Hide password using Eye Icon
        ImageView imageViewShowHidePassword = findViewById(R.id.ImageView_show_hide_password);
        imageViewShowHidePassword.setImageResource(R.drawable.icon_pwdhide);
        imageViewShowHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    // if password is visible then hide it
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    // change icon
                    imageViewShowHidePassword.setImageResource(R.drawable.icon_pwdhide);
                } else {
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewShowHidePassword.setImageResource(R.drawable.icon_pwdshow);
                }
            }
        });


        //login User
        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textEmail = editTextEmailAddress.getText().toString();
                String textPassword = editTextPassword.toString().toString();

                if (TextUtils.isEmpty(textEmail)) {
                    Toast.makeText(LoginActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    editTextEmailAddress.setError("Email is required");
                    editTextEmailAddress.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                    Toast.makeText(LoginActivity.this, "Please re-enter your email", Toast.LENGTH_SHORT).show();
                    editTextEmailAddress.setError("Valid email is required");
                    editTextEmailAddress.requestFocus();
                } else if (TextUtils.isEmpty(textPassword)) {
                    Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    editTextPassword.setError("Password is required");
                    editTextPassword.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    loginUser(textEmail,textPassword);
                }
            }
        });


    }

    private void loginUser(String email, String password) {
        authProfile.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    //Get instance for the current user
                    FirebaseUser firebaseUser = authProfile.getCurrentUser();

                    //Check if email is verified before user can access their profile
                    if (firebaseUser.isEmailVerified()){
                        Toast.makeText(LoginActivity.this, "You are logged in now", Toast.LENGTH_SHORT).show();

                    } else {
                        firebaseUser.sendEmailVerification();
                        authProfile.signOut(); // sign out user
                        showAlertDialog();
                    }
                } else {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        editTextEmailAddress.setError("User does not exists or is no longer valid . Please register again.");
                        editTextEmailAddress.requestFocus();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        editTextEmailAddress.setError("Invalid credentials. kindly, check and re-enter");
                        editTextEmailAddress.requestFocus();
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                        Toast.makeText(LoginActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void showAlertDialog() {
        // Setup the Alert Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Email is not verified");
        builder.setMessage("Please verify your email now. You can not login without email verification.");

        // Open Email Apps if user clicks/taps Continue button
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // To email app in new window and not within my app
                startActivity(intent);
            }
        });
        // Create the AlertDialog
        AlertDialog alertDialog = builder.create();

        // Show the AlertDialog
        alertDialog.show();
    }

    //Check if User is already logged in. In such case, straightaway take the User to the User's profile
    @Override
    protected void onStart() {
        super.onStart();
        if (authProfile.getCurrentUser() != null) {
            Toast.makeText(LoginActivity.this,"Already Logged In!", Toast.LENGTH_SHORT).show();

            // Start the UserProfileActivity
            startActivity(new Intent(LoginActivity.this,UserProfileActivity.class));
            finish();    // Close LoginActivity
        } else  {
            Toast.makeText(LoginActivity.this,"you can login now!", Toast.LENGTH_SHORT).show();
        }
    }
}
