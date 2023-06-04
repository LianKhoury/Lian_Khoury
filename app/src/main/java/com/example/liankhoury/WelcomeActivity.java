package com.example.liankhoury;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {


    GridLayout GridW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        GridW = (GridLayout)findViewById(R.id.GridW);

        //set Event
        setSingleEvent(GridW);

        Intent notification = new Intent(this,Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,notification,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC,System.currentTimeMillis(), 3000, pendingIntent);

        }

    private void setSingleEvent(GridLayout gridW) {
        // Loop all child item from welcome grid
        for (int i = 0 ; i<gridW.getChildCount(); i++)
        {
            CardView cardView = (CardView)gridW.getChildAt(i);
            int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(WelcomeActivity.this,"Clicked at index" + finalI,Toast.LENGTH_SHORT).show();
                    if (finalI == 0) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, FantasyBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 1) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, FableBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 2) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, FairytaleBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 3) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, ClassicBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 4) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, ComicBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 5) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, CrimeBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 6) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, DetectiveBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 7) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, GraphicalBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 8) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, HorrorBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 9) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, MysteryBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 10) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, MythologyBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 11) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, PoetryBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 12) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, ScienceBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 13) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, FantasyBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 14) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, ScienceFictionBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 15) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, ThrillerBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 16) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, RomanceBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 17) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, ReligionBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 18) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, LegendBooksActivity.class);
                        startActivity(i_BookList);
                    }
                    if (finalI == 19) {
                        Intent i_BookList = new Intent(WelcomeActivity.this, DramaBooksActivity.class);
                        startActivity(i_BookList);
                    }


                }
            });
        }
    }

    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_example, menu);
        return true;
    }
}

