package com.example.liankhoury;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ThrillerBooksActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardV1,cardV2,cardV3,cardV4,cardV5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thriller_books);



        getSupportActionBar().setTitle("Thriller Books");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cardV1 = (CardView) findViewById(R.id.cv1);
        cardV2 = (CardView) findViewById(R.id.cv2);
        cardV3 = (CardView) findViewById(R.id.cv3);
        cardV4 = (CardView) findViewById(R.id.cv4);
        cardV5 = (CardView) findViewById(R.id.cv5);

        cardV1.setOnClickListener(this);
        cardV2.setOnClickListener(this);
        cardV3.setOnClickListener(this);
        cardV4.setOnClickListener(this);
        cardV5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.cv1:
                i = new Intent(ThrillerBooksActivity.this, AdventureBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv2:
                i = new Intent(ThrillerBooksActivity.this, ClassicBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv3:
                i = new Intent(ThrillerBooksActivity.this, ComicBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv4:
                i = new Intent(ThrillerBooksActivity.this, CrimeBooksActivity.class);
                startActivity(i);
                break;

            case R.id.cv5:
                i = new Intent(ThrillerBooksActivity.this, DramaBooksActivity.class);
                startActivity(i);
                break;

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}