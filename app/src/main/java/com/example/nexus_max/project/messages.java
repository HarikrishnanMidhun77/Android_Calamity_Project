package com.example.nexus_max.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class messages extends AppCompatActivity {
ListView msgListView;
    DatabaseReference dbMsg;
    List<Message> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        mList=new ArrayList<>();
        msgListView=(ListView)findViewById(R.id.ls_msg);
        dbMsg= FirebaseDatabase.getInstance().getReference("Messages");
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbMsg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mList.clear();
                for(DataSnapshot msgSnap:dataSnapshot.getChildren()){
                    Message msg=msgSnap.getValue(Message.class);
                    mList.add(msg);
                    MsgList adapter =new MsgList(messages.this,mList);
                    msgListView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(messages.this, Main_menu.class);
        startActivity(intent);
    }
}
