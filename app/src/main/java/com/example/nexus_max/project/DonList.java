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

public class DonList extends ArrayAdapter<Donation> {
    private Activity context;
    private List<Donation> dList;
    Donation donation;

    public DonList(Activity context, List<Donation> dList){
        super(context, R.layout.messages_layout,dList);
        this.context=context;
        this.dList=dList;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem= inflater.inflate(R.layout.don_layout,null,true);

        TextView txt_name=(TextView)listViewItem.findViewById(R.id.txt_don_name);
        TextView txt_amt=(TextView)listViewItem.findViewById(R.id.txt_don_amt);

        donation=dList.get(position);
        txt_name.setText(donation.getDname());
        txt_amt.setText(donation.getDamt());
        return listViewItem;
    }

}
