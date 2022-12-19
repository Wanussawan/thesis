package com.rana.adarsh.imagetotext.mainapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rana.adarsh.imagetotext.Adapter.ProvinsuAdapter;
import com.rana.adarsh.imagetotext.Model.Config;
import com.rana.adarsh.imagetotext.Model.Provinsi;
import com.rana.adarsh.imagetotext.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Checktoday extends AppCompatActivity {

    EditText editswine;
    Button buttonfetch;
    ListView listview;
    String swine;


    public static final String KEY_SWINE = "Swine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checktoday);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ข้อมูลที่บันทึกแล้วในวันนี้");


        editswine = (EditText)findViewById(R.id.etswine);
        buttonfetch = (Button)findViewById(R.id.btnfetch);
        listview = (ListView)findViewById(R.id.listViewalready);

        buttonfetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                swine = editswine.getText().toString().trim();

                if (swine.equals("")){
                    Toast.makeText(Checktoday.this, "Please Enter Detail", Toast.LENGTH_SHORT).show();
                }else {

                    GetMatchData();
                }

            }
        });

    }

    private void GetMatchData() {

        swine = editswine.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.MATCHDATA_URL ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {

                            showJSON(response);

                        } else {

                            showJSON(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Checktoday.this, "error", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_SWINE, swine);
                return map;
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String Swine = jo.getString(Config.KEY_SWINE);
                String Numhouse = jo.getString(Config.KEY_NUMHOUSE);
                String Name = jo.getString(Config.KEY_NAMEOWNER);

                final HashMap<String, String> employees = new HashMap<>();
                employees.put(Config.KEY_SWINE,Swine);
                employees.put(Config.KEY_NUMHOUSE,Numhouse);
                employees.put(Config.KEY_NAMEOWNER,Name);

                list.add(employees);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(Checktoday.this, "no", Toast.LENGTH_SHORT).show();
        }
        ListAdapter adapter = new SimpleAdapter(
                Checktoday.this, list, R.layout.list_itemalready,
                new String[]{Config.KEY_SWINE,Config.KEY_NUMHOUSE,Config.KEY_NAMEOWNER},
                new int[]{R.id.tvswine,R.id.tvnumhouse,R.id.tvname});
        listview.setAdapter(adapter);

    }
}
