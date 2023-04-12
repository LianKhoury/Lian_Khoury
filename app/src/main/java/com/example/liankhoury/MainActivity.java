package com.example.liankhoury;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //ATTRIBUTES
    private TextView textView, paragraph1TextView;
    private Button buttonGOLogin, buttonRegister;
    private final String valid_mail = "admin";
    private final String valid_password = "1";
    //private final String valid_username = "admin";
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView paragraph1TextView = findViewById(R.id.paragraph1TextView);

        int unicode = 0x1F60A;

        String emoji = getEmoji(unicode);

        String text = paragraph1TextView + emoji;

        paragraph1TextView.setText(text);

        buttonGOLogin = findViewById(R.id.buttonGOLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        // creates a preferences
        preferences = getSharedPreferences("Userinfo", 0);

    }//onCreate

    //load option menu from an activity - linking the xml option menu to the activity
    /*
        this method loads the menu design into this activity
     */
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_example, menu);
        return true;
    }

    public String getEmoji(int unicode){
        return new String(Character.toChars(unicode));
    }


    //handle options menu click events
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help_menu: {
                Toast.makeText(MainActivity.this, "help clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, HelpActivity.class);
                startActivity(i);
                break;
            }
            case R.id.settings_menu:
                Toast.makeText(MainActivity.this, "sittings clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout_menu:
                Toast.makeText(MainActivity.this, "logout clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_menu: {
                //open the about activity when about menu selected
                //this is from where , and the second parameter is to where
                Toast.makeText(MainActivity.this, "about clicked", Toast.LENGTH_SHORT).show();
                Intent j = new Intent(this, AboutActivity.class);
                startActivity(j);
                break;
            }
            case R.id.list_menu: {
                Toast.makeText(MainActivity.this, "list clicked", Toast.LENGTH_SHORT).show();
                Intent z = new Intent(this, ListActivity.class);
                startActivity(z);
                break;
            }
        }
        return true;
    }

    /* another method:
    //public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    //  in case user chose about menu
    //  if (item.getItemId() == R.id.about_menu){
    //      open the about activity when about menu selected
    //      this is from where , and the second parameter is to where
    //      Intent i = new Intent(this,AboutActivity.class);
    //      startActivity(i);
    //  }else if(item.getItemId() == R.id.settings_menu){
    //
    //  }
    //return true;
    // }

     */


    /*
    is called when the user clicks the back button
     */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Back Button was pressed!");
        dialog.setMessage("Are you sure you want to Exit?");
        // in case the user chose No, Nothing Happens, the dialog closes
        dialog.setNegativeButton("No", null);
        // when the user clicks on the Yes button the application closes
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.finish();
            }
        });
        dialog.setIcon(R.drawable.ic_baseline_outlet_24);
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    public void buttonGoLogin(View view) {
        Intent i_goLogin = new Intent(this, LoginActivity.class);
        startActivity(i_goLogin);
    }


    public void register(View view) {
        // String registeredMail = editTextEmailAddress.getText().toString();
        // String registeredPassword= editTextPassword.getText().toString();
        //save the email value in the preferences and commits the file
        // preferences.edit().putString("email",registeredMail).commit();
        // preferences.edit().putString("password",registeredPassword).commit();
        Intent i_register = new Intent(this, RegisterActivity.class);
        startActivity(i_register);
        Toast.makeText(this, "WELCOME!", Toast.LENGTH_LONG).show();

    }
}
