package com.example.liankhoury;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.locks.ReadWriteLock;

public class UserProfileActivity extends AppCompatActivity {

    private TextView TextViewProfile,T_VShowFullName,T_VShowEmail,T_VShowPhoneNum, TextView_Welcome;
    private ProgressBar progressBar;
    private String FullName,email,phone;
    private ImageView imageView;
    private FirebaseAuth authProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        getSupportActionBar().setTitle("Home");

        TextViewProfile = findViewById(R.id.TextViewProfile);
        T_VShowFullName = findViewById(R.id.T_VShowFullName);
        T_VShowEmail = findViewById(R.id.T_VShowEmail);
        T_VShowPhoneNum = findViewById(R.id.T_VShowPhoneNum);
        progressBar = findViewById(R.id.progressBar);

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();

        if (firebaseUser == null){
            Toast.makeText(UserProfileActivity.this, "Something went wrong! User's details are not available at the moment", Toast.LENGTH_LONG).show();
        } else {
            checkIfEmailVertified(firebaseUser)
            progressBar.setVisibility(View.VISIBLE);
            showUserProfile(firebaseUser);
        }
    }

    private void checkIfEmailVerified(FirebaseUser firebaseUser){
        if(!firebaseUser.isEmailVerified()){
            showAlertDialog();
        }
    }

    private void showAlertDialog() {
        //Setup The Alert Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(UserProfileActivity.this);

    }

    private void showUserProfile(FirebaseUser firebaseUser){
        String userID = firebaseUser.getUid();

        //Extracting User Reference from Database for "Registered Users"
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
        referenceProfile.child(userID);
        referenceProfile.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if(readUserDetails != null){
                    FullName = firebaseUser.getDisplayName();
                    email = firebaseUser.getEmail();
                    phone = readUserDetails.phone;
                    TextView_Welcome.setText("Welcome," + FullName + "!");
                    T_VShowFullName.setText(FullName);
                    T_VShowEmail.setText(email);
                    T_VShowPhoneNum.setText(phone);
                }

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfileActivity.this,"something went wrong!",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}