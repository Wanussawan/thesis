package com.rana.adarsh.imagetotext.mainapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.List;

public class Show1 extends AppCompatActivity {

    ListView listView;
    List<Provinsi> provinsiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show1activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Information");

        listView = (ListView) findViewById(R.id.list_prov);
        provinsiList = new ArrayList<>();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://groundwaterbkj.000webhostapp.com/appgw/listSemu.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("Information");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject provObj = array.getJSONObject(i);
                                Provinsi p = new Provinsi(provObj.getString("Swine"), provObj.getString("Status"), provObj.getString("Name"));
                                provinsiList.add(p);

                            }
                            ProvinsuAdapter adapter = new ProvinsuAdapter(provinsiList, getApplicationContext());
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

        };

        Handler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);

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
                startActivity(new Intent(Show1.this, Show1.class));
                break;


            case R.id.item2:
                startActivity(new Intent(Show1.this, Show2.class));
                break;

            default:
        }


        return super.onOptionsItemSelected(item);

    }
}
