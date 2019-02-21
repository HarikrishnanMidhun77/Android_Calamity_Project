package com.example.nexus_max.project;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ReplyList extends ArrayAdapter {
    private Activity context;
    private List<ReplyClass> wList;
    ReplyClass reply;

    public ReplyList(Activity context, List<ReplyClass> wList) {
        super(context, R.layout.reply_layout, wList);
        this.context = context;
        this.wList = wList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem= inflater.from(context).inflate(R.layout.reply_layout,null,true);

        TextView txt_name=(TextView) listViewItem.findViewById(R.id.txt_rep_name);
        EditText txt_message=(EditText)listViewItem.findViewById(R.id.txt_rep_msg);

        reply=wList.get(position);
        txt_name.setText(reply.getFrom_mail());
        txt_message.setText(reply.getMessage());
        return listViewItem;
    }
    }
