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
    ListView listViewWeather;
    DatabaseReference dbWeath;
    List<Weather> wList;
    private Activity context;
    public AlertPage(){
       context= this.getActivity();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_alert_page,container,false);
        wList=new ArrayList<>();
        listViewWeather=(ListView)view.findViewById(R.id.ls_alert);
        dbWeath= FirebaseDatabase.getInstance().getReference("Alerts");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dbWeath.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                wList.clear();
                for(DataSnapshot msgSnap:dataSnapshot.getChildren()){
                    Weather msg=msgSnap.getValue(Weather.class);
                    wList.add(msg);
                    WeatherList adapter =new WeatherList(context,wList);
                    listViewWeather.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
