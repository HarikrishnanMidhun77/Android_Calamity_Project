package com.example.nexus_max.project;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WeatherList extends ArrayAdapter {
    private Activity context;
    private List<Weather> wList;
    Weather weather;

    public WeatherList(Activity context, List<Weather> wList) {
        super(context, R.layout.weather_layout, wList);
        this.context = context;
        this.wList = wList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem= inflater.inflate(R.layout.weather_layout,null,true);

        TextView txt_dist=(TextView)listViewItem.findViewById(R.id.txt_w_dist);
        TextView txt_dc=(TextView)listViewItem.findViewById(R.id.txt_w_dc);

        weather=wList.get(position);
        txt_dist.setText(weather.getDist());
        txt_dc.setText(weather.getDc());
        return listViewItem;
    }
    }
