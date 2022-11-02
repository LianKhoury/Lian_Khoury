package com.example.liankhoury;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class WelcomeActivity extends AppCompatActivity {

    ListView listView_Books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        listView_Books = (ListView) findViewById(R.id.ListView_Books);

        //make an array list
        ArrayList<String> arrayBookList = new ArrayList<>();


        //add items to arrayList
        arrayBookList.add("Fantasy");
        arrayBookList.add("Science Fiction");
        arrayBookList.add("Historical Fiction");
        arrayBookList.add("Realistic Fiction");
        arrayBookList.add("Fan Fiction");
        arrayBookList.add("Crime");
        arrayBookList.add("Mystery");
        arrayBookList.add("Suspense / Thriller");
        arrayBookList.add("Horror");
        arrayBookList.add("Humor");
        arrayBookList.add("Classic");
        arrayBookList.add("Drama");
        arrayBookList.add("Romance");
        arrayBookList.add("Comic");
        arrayBookList.add("Fable");
        arrayBookList.add("Fairy Tale");
        arrayBookList.add("Short Story");
        arrayBookList.add("Legend");
        arrayBookList.add("Mythology");
        arrayBookList.add("Poetry");
        arrayBookList.add("Graphic Novel");

        //initialize the adapter
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayBookList);

        //connect adapter to array
        listView_Books.setAdapter(arrayAdapter);

        //handle items clicks
        listView_Books.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(WelcomeActivity.this,"clicked item" + i + "" + arrayBookList.get(i).toString(),Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    Intent i_BookList = new Intent(WelcomeActivity.this, FantasybooksActivity.class);
                    startActivity(i_BookList);
                }
                if (i == 1) {
                    Intent i_BookList = new Intent(WelcomeActivity.this, SciencebooksActivity.class);
                    startActivity(i_BookList);
                }
                if (i == 2) {
                    Intent i_BookList = new Intent(WelcomeActivity.this, SciencebooksActivity.class);
                    startActivity(i_BookList);
                }
                if (i == 3) {
                    Intent i_BookList = new Intent(WelcomeActivity.this, SciencebooksActivity.class);
                    startActivity(i_BookList);
                }
                if (i == 4) {
                    Intent i_BookList = new Intent(WelcomeActivity.this, SciencebooksActivity.class);
                    startActivity(i_BookList);
                }
                if (i == 5) {
                    Intent i_BookList = new Intent(WelcomeActivity.this, SciencebooksActivity.class);
                    startActivity(i_BookList);
                }
                if (i == 6) {
                    Intent i_BookList = new Intent(WelcomeActivity.this, SciencebooksActivity.class);
                    startActivity(i_BookList);
                }
                if (i == 6) {
                    Intent i_BookList = new Intent(WelcomeActivity.this, SciencebooksActivity.class);
                    startActivity(i_BookList);
                }
                if (i == 7) {
                    Intent i_BookList = new Intent(WelcomeActivity.this, SciencebooksActivity.class);
                    startActivity(i_BookList);
                }
                if (i == 8) {
                    Intent i_BookList = new Intent(WelcomeActivity.this, SciencebooksActivity.class);
                    startActivity(i_BookList);
                }
                if (i == 9) {
                    Intent i_BookList = new Intent(WelcomeActivity.this, SciencebooksActivity.class);
                    startActivity(i_BookList);
                }
                if (i == 10) {
                    Intent i_BookList = new Intent(WelcomeActivity.this, SciencebooksActivity.class);
                    startActivity(i_BookList);
                }
                if (i == 11) {
                    Intent i_BookList = new Intent(WelcomeActivity.this, SciencebooksActivity.class);
                    startActivity(i_BookList);
                }

            }
        });
    }

}