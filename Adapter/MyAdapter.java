package com.rana.adarsh.imagetotext.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rana.adarsh.imagetotext.R;
import com.rana.adarsh.imagetotext.Model.Employee;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Employee> {

    Context context;
    List<Employee> arrayListEmployee;


    public MyAdapter(@NonNull Context context, List<Employee> arrayListEmployee) {
        super(context, R.layout.custom_list_item,arrayListEmployee);

        this.context = context;
        this.arrayListEmployee = arrayListEmployee;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);

        TextView tvID = view.findViewById(R.id.txt_swine);
        TextView tvName = view.findViewById(R.id.txt_name);

        tvID.setText(arrayListEmployee.get(position).getSwine());
        tvName.setText(arrayListEmployee.get(position).getName());

        return view;
    }
}