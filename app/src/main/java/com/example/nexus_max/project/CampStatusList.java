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

public class CampStatusList  extends ArrayAdapter {
    private Activity context;
    private List<CampStatus> wList;
    CampStatus weather;

    public CampStatusList(Activity context, List<CampStatus> wList) {
        super(context, R.layout.camp_status_layout, wList);
        this.context = context;
        this.wList = wList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem= inflater.from(context).inflate(R.layout.camp_status_layoutt,null,true);

        TextView cid=(TextView)listViewItem.findViewById(R.id.txtCid);
        TextView it=(TextView)listViewItem.findViewById(R.id.txt_cs_item);
        TextView st=(TextView)listViewItem.findViewById(R.id.txt_cs_st);

        weather=wList.get(position);
       cid.setText(weather.getCampId());
        it.setText(weather.getItem());
        st.setText(weather.getStatus());
        return listViewItem;
    }

}
