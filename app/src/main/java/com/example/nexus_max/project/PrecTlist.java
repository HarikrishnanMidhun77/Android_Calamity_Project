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

public class PrecTlist extends ArrayAdapter<Precaution> {
    private Activity context;
    private List<Precaution> ptList;
    Precaution precaution;

    public PrecTlist(Activity context, List<Precaution> ptList){
        super(context, R.layout.messages_layout,ptList);
        this.context=context;
        this.ptList=ptList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem= inflater.inflate(R.layout.prec_t_layout,null,true);

        TextView txt_title=(TextView)listViewItem.findViewById(R.id.pt_lo_title);
        precaution=ptList.get(position);
        txt_title.setText(precaution.getCalam());
        return listViewItem;
    }
}
