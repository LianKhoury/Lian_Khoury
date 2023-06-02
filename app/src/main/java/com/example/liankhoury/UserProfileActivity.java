package com.example.liankhoury;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    private TextView TextViewProfile,T_VShowFullName,T_VShowEmail,T_VShowPhoneNum,TextView_Welcome;
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
        TextView_Welcome  = findViewById(R.id.TextView_Welcome);
        T_VShowFullName = findViewById(R.id.T_VShowFullName);
        T_VShowEmail = findViewById(R.id.T_VShowEmail);
        T_VShowPhoneNum = findViewById(R.id.T_VShowPhoneNum);
        progressBar = findViewById(R.id.ProgressBar);

        // Set OnClickListener on ImageView to open UploadProfilePicActivity
        imageView = findViewById(R.id.imageView_profile_dp);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this,UploadProfilePicActivity.class);
                startActivity(intent);

            }
        });

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();

        if (firebaseUser == null){
            Toast.makeText(UserProfileActivity.this, "Something went wrong! User's details are not available at the moment", Toast.LENGTH_LONG).show();
        } else {
            checkIfEmailVerified(firebaseUser);
            progressBar.setVisibility(View.VISIBLE);
            showUserProfile(firebaseUser);
        }
    }
    //Users coming to UserProfileActivity after successful registration
    private void checkIfEmailVerified(FirebaseUser firebaseUser) {
       if (!firebaseUser.isEmailVerified()){
           showAlertDialog();
       }
    }

    private void showAlertDialog() {
        //SetUp the Alert Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(UserProfileActivity.this);
        builder.setTitle("Email Not Verified");
        builder.setMessage("P;ease verify your Email now. You can not login without email verification next time.");

        //Open Email App if User clicks/taps Continue button
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);         //To email app is new window and not within my app
                startActivity(intent);
            }
        });

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

    // Creating ActionBar Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu items
        getMenuInflater().inflate(R.menu.common_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // When any menu item is selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_refresh){
            //Refresh activity
            startActivity(getIntent());
            finish();
            overridePendingTransition(0,0);
        } /*else if (id == R.id.menu_update_profile){
            Intent intent = new Intent(UserProfileActivity.this,UpdateProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_update_Email){
            Intent intent = new Intent(UserProfileActivity.this,UpdateEmailActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_settings){
            Toast.makeText(UserProfileActivity.this,"menu settings",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_change_pwd){
            Intent intent = new Intent(UserProfileActivity.this,ChangePasswordActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_delete_profile){
            Intent intent = new Intent(UserProfileActivity.this,DeleteProfileActivity.class);
            startActivity(intent);
        } */else if (id == R.id.menu_logout) {
            authProfile.signOut();
            Toast.makeText(UserProfileActivity.this,"Logged Out", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(UserProfileActivity.this,MainActivity.class);

            // Clear stack to prevent user coming back to UserProfileActivity on pressing back button after logging out
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity(intent);
            finish();       // close UserProfileActivity
        } else {
            Toast.makeText(UserProfileActivity.this,"Something went wrong!", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

}