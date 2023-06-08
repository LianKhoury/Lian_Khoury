package com.example.liankhoury;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardv1, cardv2, cardv3, cardv4, cardv5, cardv6, cardv7, cardv8, cardv9, cardv10, cardv11, cardv12, cardv13, cardv14, cardv16, cardv15, cardv17, cardv18, cardv19, cardv20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        cardv1 = (CardView) findViewById(R.id.cv1);
        cardv2 = (CardView) findViewById(R.id.cv2);
        cardv3 = (CardView) findViewById(R.id.cv3);
        cardv4 = (CardView) findViewById(R.id.cv4);
        cardv5 = (CardView) findViewById(R.id.cv5);
        cardv6 = (CardView) findViewById(R.id.cv6);
        cardv7 = (CardView) findViewById(R.id.cv7);
        cardv8 = (CardView) findViewById(R.id.cv8);
        cardv9 = (CardView) findViewById(R.id.cv9);
        cardv10 = (CardView) findViewById(R.id.cv10);
        cardv11 = (CardView) findViewById(R.id.cv11);
        cardv12 = (CardView) findViewById(R.id.cv12);
        cardv13 = (CardView) findViewById(R.id.cv13);
        cardv14 = (CardView) findViewById(R.id.cv14);
        cardv15 = (CardView) findViewById(R.id.cv15);
        cardv16 = (CardView) findViewById(R.id.cv16);
        cardv17 = (CardView) findViewById(R.id.cv17);
        cardv18 = (CardView) findViewById(R.id.cv18);
        cardv19 = (CardView) findViewById(R.id.cv19);
        cardv20 = (CardView) findViewById(R.id.cv20);


        cardv1.setOnClickListener(this);
        cardv2.setOnClickListener(this);
        cardv3.setOnClickListener(this);
        cardv4.setOnClickListener(this);
        cardv5.setOnClickListener(this);
        cardv6.setOnClickListener(this);
        cardv7.setOnClickListener(this);
        cardv8.setOnClickListener(this);
        cardv9.setOnClickListener(this);
        cardv10.setOnClickListener(this);
        cardv11.setOnClickListener(this);
        cardv12.setOnClickListener(this);
        cardv13.setOnClickListener(this);
        cardv14.setOnClickListener(this);
        cardv15.setOnClickListener(this);
        cardv16.setOnClickListener(this);
        cardv17.setOnClickListener(this);
        cardv18.setOnClickListener(this);
        cardv19.setOnClickListener(this);
        cardv20.setOnClickListener(this);


        Intent notification = new Intent(this, Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, notification, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 3000, pendingIntent);

    }

    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_example, menu);
        return true;
    }
    @Override
    public void onPointerCaptureChanged ( boolean hasCapture){
        super.onPointerCaptureChanged(hasCapture);}

    @Override
    public void onClick (View v){
        Intent i;

        switch (v.getId()) {
            case R.id.cv1:
                i = new Intent(WelcomeActivity.this, AdventureBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv2:
                i = new Intent(WelcomeActivity.this, ClassicBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv3:
                i = new Intent(WelcomeActivity.this, ComicBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv4:
                i = new Intent(WelcomeActivity.this, CrimeBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv5:
                i = new Intent(WelcomeActivity.this, DramaBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv6:
                i = new Intent(WelcomeActivity.this, DetectiveBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv7:
                i = new Intent(WelcomeActivity.this, FantasyBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv8:
                i = new Intent(WelcomeActivity.this, FairytaleBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv9:
                i = new Intent(WelcomeActivity.this, FableBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv10:
                i = new Intent(WelcomeActivity.this, GraphicalBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv11:
                i = new Intent(WelcomeActivity.this, HorrorBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv12:
                i = new Intent(WelcomeActivity.this, LegendBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv13:
                i = new Intent(WelcomeActivity.this, MysteryBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv14:
                i = new Intent(WelcomeActivity.this, MythologyBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv15:
                i = new Intent(WelcomeActivity.this, PoetryBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv16:
                i = new Intent(WelcomeActivity.this, ReligionBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv17:
                i = new Intent(WelcomeActivity.this, RomanceBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv18:
                i = new Intent(WelcomeActivity.this, ScienceBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv19:
                i = new Intent(WelcomeActivity.this, ScienceFictionBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv20:
                i = new Intent(WelcomeActivity.this, ThrillerBooksActivity.class);
                startActivity(i);
                break;
        }

    }

}

