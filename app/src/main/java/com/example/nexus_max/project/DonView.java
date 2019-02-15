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

public class DonView extends AppCompatActivity {
    ListView donListView;
    DatabaseReference dbDon;
    List<Donation> dList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_view);

        dList=new ArrayList<>();
        donListView=(ListView)findViewById(R.id.ls_don);
        dbDon= FirebaseDatabase.getInstance().getReference("Donations");
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbDon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dList.clear();
                for(DataSnapshot msgSnap:dataSnapshot.getChildren()){
                    Donation msg=msgSnap.getValue(Donation.class);
                    dList.add(msg);
                    DonList adapter =new DonList(DonView.this,dList);
                    donListView.setAdapter(adapter);

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
        Intent intent = new Intent(DonView.this, Main_menu.class);
        startActivity(intent);
    }
}
