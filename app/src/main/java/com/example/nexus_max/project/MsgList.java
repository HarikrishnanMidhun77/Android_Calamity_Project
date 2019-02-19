package com.example.nexus_max.project;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
//import com.example.nexus_max.project.R;

import java.util.List;

public class MsgList extends ArrayAdapter<Message> {
    private Activity context;
    private List<Message> msgList;
    Message message;

    public MsgList(Activity context, List<Message> msgList){
        super(context, R.layout.messages_layout,msgList);
        this.context=context;
        this.msgList=msgList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem= inflater.inflate(R.layout.messages_layout,null,true);

        TextView txt_phno=(TextView)listViewItem.findViewById(R.id.txt_mlo_phno);
        TextView txt_name=(TextView)listViewItem.findViewById(R.id.txt_mlo_name);
        TextView txt_place=(TextView)listViewItem.findViewById(R.id.txt_mlo_place);
        TextView txt_calam=(TextView)listViewItem.findViewById(R.id.txt_mlo_calam);

        Button btn_reply=(Button)listViewItem.findViewById(R.id.btn_mlo_reply);
        Button btn_share=(Button)listViewItem.findViewById(R.id.btn_mlo_share);
        Button btn_prec=(Button)listViewItem.findViewById(R.id.btn_mlo_prec);
        Button btn_img=(Button)listViewItem.findViewById(R.id.btn_mlo_img);

         message=msgList.get(position);
        txt_phno.setText(message.getTo_phno());
        txt_name.setText(message.getUname());
        txt_place.setText(message.getLoc());
        txt_calam.setText(message.getCalam());

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareBody = "Emergency!! There is "+message.getCalam()+" in "+message.getLoc()+". "+message.getUname()+" is in trouble! Please Help!";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent,"Share Using" ));
            }
        });
        btn_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Reply.class);
                intent.putExtra("mid",message.getMid());
                /*intent.putExtra("phno",message.getTo_phno());
                intent.putExtra("name",message.getUname());
                intent.putExtra("loc",message.getLoc());
                intent.putExtra("calam",message.getCalam());*/
                context.startActivity(intent);
            }
        });

        btn_prec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PrecTitles.class);
                context.startActivity(intent);
            }
        });

        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowMsgImg.class);
                intent.putExtra("img_path",message.getImgPath());
                context.startActivity(intent);
            }
        });
            return listViewItem;
    }
}
