package com.example.nexus_max.project;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AlertPage extends Fragment{
    ListView listViewAlert;
    DatabaseReference dbAlert;
    List<Alert> wList;
    private Activity context;
    public AlertPage(){
       context= this.getActivity();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_alert_page,container,false);
        wList=new ArrayList<>();
        listViewAlert=(ListView)view.findViewById(R.id.ls_alert);
        dbAlert= FirebaseDatabase.getInstance().getReference("Alerts");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dbAlert.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                wList.clear();
                for(DataSnapshot msgSnap:dataSnapshot.getChildren()){
                    Alert msg=msgSnap.getValue(Alert.class);
                    wList.add(msg);
                    AlertList adapter =new AlertList(getActivity(),wList);
                    listViewAlert.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
