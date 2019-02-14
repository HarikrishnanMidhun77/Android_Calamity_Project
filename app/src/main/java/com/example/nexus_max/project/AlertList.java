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

public class AlertList extends ArrayAdapter {
    private Activity context;
    private List<Alert> aList;
    Alert alert;

    public AlertList(Activity context, List<Alert> aList) {
        super(context, R.layout.weather_layout, aList);
        this.context = context;
        this.aList = aList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem= inflater.inflate(R.layout.alert_layout,null,true);

        TextView txt_loc=(TextView)listViewItem.findViewById(R.id.txt_a_loc);
        TextView txt_alert=(TextView)listViewItem.findViewById(R.id.txt_a_alert);

        alert=aList.get(position);
        txt_loc.setText(alert.getLoc());
        txt_alert.setText(alert.getAlert());
        return listViewItem;
    }
    }
