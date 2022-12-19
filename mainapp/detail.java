package com.rana.adarsh.imagetotext.mainapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rana.adarsh.imagetotext.Adapter.MyAdapter;
import com.rana.adarsh.imagetotext.Model.Employee;
import com.rana.adarsh.imagetotext.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class detail extends AppCompatActivity {

    ListView listView;
    MyAdapter adapter;
    public static ArrayList<Employee> employeeArrayList = new ArrayList<>();
    String url = "https://groundwaterbkj.000webhostapp.com/appgw/detail.php";
    Employee employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infobackactivity);

        listView = findViewById(R.id.myListback);
        adapter = new MyAdapter(this, employeeArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),DetailActivity.class)
                        .putExtra("position",position));
            }
        });

        retrieveData();

    }

    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        employeeArrayList.clear();
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for(int i = 0; i < jsonArray.length(); i++){

                                JSONObject object = jsonArray.getJSONObject(i);

                                String swine = object.getString("swine");
                                String numhouse = object.getString("numhouse");
                                String name = object.getString("name");
                                String status = object.getString("status");
                                String date = object.getString("date");

                                employee = new Employee(swine,numhouse,name,status,date);
                                employeeArrayList.add(employee);
                                adapter.notifyDataSetChanged();

                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(detail.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}