package com.rana.adarsh.imagetotext.mainapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rana.adarsh.imagetotext.R;


import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Button loginbtn;
    EditText etusername,etpassword;

    SharedPreferences sharedPreferences;

    public static final String fileName = "login";
    public static final String Username = "username";
    public static final String Password = "password";

    @Override
    protected void onCreate(Bundle saveInstanceStatus) {
        super.onCreate(saveInstanceStatus);
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Login");
        actionBar.hide();

        loginbtn = findViewById(R.id.loginbtn);
        etusername = findViewById(R.id.etusername);
        etpassword = findViewById(R.id.etpassword);

        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(Username)){
            Intent i = new Intent(MainActivity.this,indexActivity.class);
            startActivity(i);
            finish();
        }

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        StringRequest request = new StringRequest(Request.Method.POST, "https://groundwaterbkj.000webhostapp.com/appgw/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("1")){

                            String username = etusername.getText().toString();
                            String password = etpassword.getText().toString();

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(Username,username);
                            editor.putString(Password,password);
                            editor.commit();

                            startActivity(new Intent(getApplicationContext(), indexActivity.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Wrong username or password" , Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",etusername.getText().toString());
                params.put("password",etpassword.getText().toString());
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }
}
