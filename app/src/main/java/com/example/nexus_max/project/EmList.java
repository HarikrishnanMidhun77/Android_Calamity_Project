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

public class EmList extends ArrayAdapter {
    private Activity context;
    private List<EmergencyCall> wList;
    EmergencyCall emCall;

    public EmList(Activity context, List<EmergencyCall> wList) {
        super(context, R.layout.em_layout, wList);
        this.context = context;
        this.wList = wList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem= inflater.from(context).inflate(R.layout.em_layout,null,true);

        TextView txt_emName=(TextView)listViewItem.findViewById(R.id.txt_em_name);
        TextView txt_ph=(TextView)listViewItem.findViewById(R.id.txt_em_ph);

        emCall=wList.get(position);
        txt_emName.setText(emCall.getEc_name());
        txt_ph.setText(emCall.getEc_num());
        return listViewItem;
    }
    }
