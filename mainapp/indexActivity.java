package com.rana.adarsh.imagetotext.mainapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.rana.adarsh.imagetotext.R;


public class indexActivity extends AppCompatActivity {

    Button b1,b2,b3,b4,b5;

    SharedPreferences sharedPreferences;

    public static final String fileName = "login";
    public static final String Username = "username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indexactivity);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Ground Water");
//        actionBar.hide();

        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);


        b1 = (Button) findViewById(R.id.givedata);
        b2 = (Button) findViewById(R.id.info);
        b3 = (Button) findViewById(R.id.detail);
        b4 = (Button) findViewById(R.id.checktoday);
//        b5 = (Button) findViewById(R.id.nottoday);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b1 = new Intent(indexActivity.this, Giveinfo.class);
                startActivity(b1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b2 = new Intent(indexActivity.this, infoActivity.class);
                startActivity(b2);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b3 = new Intent(indexActivity.this, detail.class);
                startActivity(b3);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b4 = new Intent(indexActivity.this, Checktoday.class);
                startActivity(b4);
            }
        });

//        b5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent b5 = new Intent(indexActivity.this, Nottoday.class);
//                startActivity(b5);
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.click_logout)
        {
            SharedPreferences sp=getSharedPreferences(fileName,Context.MODE_PRIVATE);
            if(sp.contains("username"))
            {
                SharedPreferences.Editor editor=sp.edit();
                editor.remove("username");

                editor.putString("msg","Logged out Successfully");
                editor.commit();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }



}
