package com.rana.adarsh.imagetotext.mainapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rana.adarsh.imagetotext.R;

public class DetailActivity extends AppCompatActivity {

    TextView tvswine,tvnum,tvname,tvstatus,tvdate;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        //Initializing Views
        tvswine = findViewById(R.id.txtswine);
        tvnum = findViewById(R.id.txtnum);
        tvname = findViewById(R.id.txtname);
        tvstatus = findViewById(R.id.txtstatus);
        tvdate = findViewById(R.id.txtdate);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvswine.setText("หมู่ที่ : "+detail.employeeArrayList.get(position).getSwine());
        tvnum.setText("บ้านเลขที่ : "+detail.employeeArrayList.get(position).getNumhouse());
        tvname.setText("ชื่อเจ้าบ้าน : "+detail.employeeArrayList.get(position).getName());
        tvstatus.setText("สถานะ : "+detail.employeeArrayList.get(position).getStatus());
        tvdate.setText("วันที่ล่าสุด : "+detail.employeeArrayList.get(position).getDate());



    }
}