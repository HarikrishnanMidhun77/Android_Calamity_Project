package com.example.nexus_max.project;

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

public class EmCallPage extends AppCompatActivity {
    ListView listViewEm;
    DatabaseReference dbEm;
    List<EmergencyCall> wList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_em_call_page);

        wList=new ArrayList<>();
        listViewEm=(ListView)findViewById(R.id.ls_em_call);
        dbEm= FirebaseDatabase.getInstance().getReference("EmergencyPhones");
    }


    @Override
    public void onStart() {
        super.onStart();
        dbEm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                wList.clear();
                for(DataSnapshot msgSnap:dataSnapshot.getChildren()){
                    EmergencyCall msg=msgSnap.getValue(EmergencyCall.class);
                    wList.add(msg);
                        EmList adapter =new EmList(EmCallPage.this,wList);
                        listViewEm.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
