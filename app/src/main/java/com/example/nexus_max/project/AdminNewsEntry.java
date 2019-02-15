package com.example.nexus_max.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminNewsEntry extends AppCompatActivity {
EditText etNews,etPlace,etTime;
Button btnSub;
    DatabaseReference dbNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_news_entry);
        dbNews= FirebaseDatabase.getInstance().getReference("News");

        etNews=(EditText)findViewById(R.id.et_aNews_news);
        etPlace=(EditText)findViewById(R.id.et_aNews_place);
        etTime=(EditText)findViewById(R.id.et_aNews_time);

        btnSub=(Button)findViewById(R.id.btn_aNews_sub);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sendMsg();
            }
        });
    }
    public boolean notEmpty(String txt){
        if(!TextUtils.isEmpty(txt)){
            return  true;
        }
        else{
            return false;
        }
    }
    private void sendMsg(){
        String place=etPlace.getText().toString();
        String time=etTime.getText().toString();
        String ns=etNews.getText().toString();


        if(notEmpty(place) && notEmpty(time)&& notEmpty(ns) ){
            String uid=dbNews.push().getKey();
            News news=new News(uid,place,time,"",ns);
            dbNews.child(uid).setValue(news);
            Toast.makeText(AdminNewsEntry.this,"Submitted !",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(AdminNewsEntry.this,"All fields must be filled!", Toast.LENGTH_LONG).show();
        }
    }
}
