package com.example.liankhoury;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ClassicBooksActivity extends AppCompatActivity {

    private CardView cardV1, cardV2, cardV3, cardV4, cardV5;
    private ImageButton add_to_fav1, add_to_fav2, add_to_fav3, add_to_fav4, add_to_fav5;
    private Drawable fav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_books);

        getSupportActionBar().setTitle("Classic Books");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cardV1 = (CardView) findViewById(R.id.cv1);
        cardV2 = (CardView) findViewById(R.id.cv2);
        cardV3 = (CardView) findViewById(R.id.cv3);
        cardV4 = (CardView) findViewById(R.id.cv4);
        cardV5 = (CardView) findViewById(R.id.cv5);

        add_to_fav1 = findViewById(R.id.add_to_fav1);
        add_to_fav2 = findViewById(R.id.add_to_fav2);
        add_to_fav3 = findViewById(R.id.add_to_fav3);
        add_to_fav4 = findViewById(R.id.add_to_fav4);
        add_to_fav5 = findViewById(R.id.add_to_fav5);

        fav = getDrawable(R.drawable.ic_baseline_favorite_24);

        cardV1.setOnClickListener((View.OnClickListener) this);
        cardV2.setOnClickListener((View.OnClickListener) this);
        cardV3.setOnClickListener((View.OnClickListener) this);
        cardV4.setOnClickListener((View.OnClickListener) this);
        cardV5.setOnClickListener((View.OnClickListener) this);

        add_to_fav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_to_fav1.setImageDrawable(fav);
            }
        });

        add_to_fav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_to_fav2.setImageDrawable(fav);
            }
        });

        add_to_fav3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_to_fav3.setImageDrawable(fav);
            }
        });

        add_to_fav4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_to_fav4.setImageDrawable(fav);
            }
        });

        add_to_fav5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_to_fav5.setImageDrawable(fav);
            }
        });

    }


    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.cv1:
                i = new Intent(ClassicBooksActivity.this, ClassicBook1Activity.class);
                startActivity(i);
                break;

            case R.id.cv2:
                i = new Intent(ClassicBooksActivity.this, ClassicBook2Activity.class);
                startActivity(i);
                break;

            case R.id.cv3:
                i = new Intent(ClassicBooksActivity.this, ClassicBook3Activity.class);
                startActivity(i);
                break;

            case R.id.cv4:
                i = new Intent(ClassicBooksActivity.this, ClassicBook4Activity.class);
                startActivity(i);
                break;

            case R.id.cv5:
                i = new Intent(ClassicBooksActivity.this, ClassicBook5Activity.class);
                startActivity(i);
                break;

        }
    }
}