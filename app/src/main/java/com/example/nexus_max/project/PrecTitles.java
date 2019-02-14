package com.example.nexus_max.project;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PrecTitles extends AppCompatActivity {
    ListView precListView;
    DatabaseReference dbPrec;
    List<Precaution> pList;
    PrecTlist padapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prec_titles);
        pList=new ArrayList<>();
        precListView=(ListView)findViewById(R.id.ls_prec_t);
        dbPrec= FirebaseDatabase.getInstance().getReference("Precautions");

        precListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Precaution cal= pList.get(position);

                Intent intent = new Intent(PrecTitles.this,PrecView.class);
                //based on item add info to intent
                intent.putExtra("desc",cal.precs);
                intent.putExtra("head",cal.calam);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbPrec.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pList.clear();
                for(DataSnapshot pSnap:dataSnapshot.getChildren()){
                    Precaution p=pSnap.getValue(Precaution.class);
                    pList.add(p);
                    PrecTlist adapter =new PrecTlist(PrecTitles.this,pList);
                    precListView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
