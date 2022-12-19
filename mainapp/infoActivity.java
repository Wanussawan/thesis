package com.rana.adarsh.imagetotext.mainapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rana.adarsh.imagetotext.Adapter.ProvinsuAdapter;
import com.rana.adarsh.imagetotext.Model.Provinsi;
import com.rana.adarsh.imagetotext.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class infoActivity extends AppCompatActivity {

    ListView listView;
    List<Provinsi> provinsiList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoactivity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Information");

        listView = (ListView) findViewById(R.id.list_prov);
        provinsiList = new ArrayList<>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dropdawn, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId())
        {
            case R.id.item1:
                startActivity(new Intent(infoActivity.this, Show1.class));
                break;


            case R.id.item2:
                startActivity(new Intent(infoActivity.this, Show2.class));
                break;

            default:
        }


        return super.onOptionsItemSelected(item);

    }
}



