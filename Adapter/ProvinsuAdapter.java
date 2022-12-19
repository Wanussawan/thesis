package com.rana.adarsh.imagetotext.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.rana.adarsh.imagetotext.Model.Provinsi;
import com.rana.adarsh.imagetotext.R;

import java.util.List;

public class ProvinsuAdapter extends ArrayAdapter<Provinsi> {

    private List<Provinsi> provinsiList;
    private Context mCtx;

    public ProvinsuAdapter(List<Provinsi> P,Context c)
    {
        super(c, R.layout.infoactivity,P);
        this.provinsiList = P;
        this.mCtx = c;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.infoactivity,null,true);

        TextView nama = (TextView) view.findViewById(R.id.tvNamaProv);
        TextView id = (TextView) view.findViewById(R.id.tvIDProv);
        TextView build = (TextView) view.findViewById(R.id.tvBuild);

        Provinsi provinsi = provinsiList.get(position);
        nama.setText(provinsi.getNama());
        id.setText(provinsi.getId());
        build.setText(provinsi.getBuild());

        return view;
    }
}
