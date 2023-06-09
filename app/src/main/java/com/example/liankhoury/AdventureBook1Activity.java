package com.example.liankhoury;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AdventureBook1Activity extends AppCompatActivity {

    //to get detail of user and book
    String myUid,myEmail,myName,bookId,hisName;

    // views
    TextView bComments;

    //add comment views
    EditText commentET;
    ImageButton sendButton;
    ImageView cAvatarIv;

    //progress bar
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_book1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Book Details");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        commentET = findViewById(R.id.comment_ET);
        sendButton = findViewById(R.id.sendButton);
        cAvatarIv = findViewById(R.id.cAvatarIv);

        loadBookInfo();

        // send comment button click
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postComment();
            }
        });
    }

    private void loadBookInfo() {
        // get book using the id of the book
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        Query query = ref.orderByChild("bId").equalTo(bookId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // keep checking the books until the required book
                for (DataSnapshot ds: snapshot.getChildren()) {
                    // fet Data
                    String bT = ""+ds.child("bTitle").getValue();
                    String uid = ""+ds.child("uid").getValue();
                    String uEmail = ""+ds.child("uEmail").getValue();
                    hisName = ""+ds.child("uName").getValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void postComment() {
        pd = new ProgressDialog(this);
        pd.setMessage("Adding Comment...");

        // get Data from comment edit text
        String comment = commentET.getText().toString().trim();
        // validate
        if (TextUtils.isEmpty(comment)) {
            // no value is entered
            Toast.makeText(this,"Comment is empty...", Toast.LENGTH_LONG).show();
            return;
        }
        // each book will have a child "comments" that will contain comments of that book
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books").child(bookId).child("comments");

        HashMap<String, Object> hashMap = new HashMap<>();
        //put info in hashmap
        hashMap.put("comment",comment);
        hashMap.put("uid",myUid);
        hashMap.put("uEmail",myEmail);
        hashMap.put("uName", myName);

        //put this data in dp
        ref.child(comment).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //added
                pd.dismiss();
                Toast.makeText(AdventureBook1Activity.this,"Comment Added...",Toast.LENGTH_LONG).show();
                commentET.setText("");
                updateCommentCount();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //failed, not added
                pd.dismiss();
                Toast.makeText(AdventureBook1Activity.this,""+ e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    boolean mProcessComment = false;
    private void updateCommentCount(){
        // whenever user adds comment, increase the comment count
        mProcessComment = true;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books").child(bookId);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (mProcessComment){
                    String comments = ""+snapshot.child("bComments").getValue();
                    int newCommentVal = Integer.parseInt(comments) + 1;
                    ref.child("bComments").setValue("" + newCommentVal);
                    mProcessComment = false;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}