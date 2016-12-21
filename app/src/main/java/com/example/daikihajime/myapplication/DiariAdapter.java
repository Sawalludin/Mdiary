package com.example.daikihajime.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.daikihajime.myapplication.Diari;

/**
 * Created by Sawal on 12/2/2015.
 */
public class DiariAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Diari> data_mhs = new ArrayList<Diari>();
    private static LayoutInflater inflater = null;

    public DiariAdapter(Activity a, ArrayList<Diari> d){
        activity = a;
        data_mhs = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount()
    {
        return data_mhs.size();
    }

    public Object getItem(int position)
    {
        return data_mhs.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        if (convertView==null)
            vi = inflater.inflate(R.layout.semua_diari, null);
        TextView pid = (TextView) vi.findViewById(R.id.pid);
        TextView tanggal = (TextView) vi.findViewById(R.id.tanggal);
        TextView email = (TextView) vi.findViewById(R.id.email);
        TextView desc = (TextView) vi.findViewById(R.id.desc);

        Diari daftar_mhs = data_mhs.get(position);
        pid.setText(daftar_mhs.getPid());
        tanggal.setText(daftar_mhs.getName());
        email.setText(daftar_mhs.getEmail());
        desc.setText(daftar_mhs.getDescription());

        return vi;
    }
}
