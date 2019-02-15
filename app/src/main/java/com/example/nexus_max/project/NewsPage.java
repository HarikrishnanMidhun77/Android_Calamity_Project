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

public class NewsPage extends Fragment{
    ListView listViewNews;
    DatabaseReference dbNews;
    List<News> nList;
    private Activity context;
    public NewsPage(){
       context= this.getActivity();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_news_page,container,false);
        nList=new ArrayList<>();
        listViewNews=(ListView)view.findViewById(R.id.ls_news);
        dbNews= FirebaseDatabase.getInstance().getReference("News");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dbNews.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nList.clear();
                for(DataSnapshot msgSnap:dataSnapshot.getChildren()){
                    News msg=msgSnap.getValue(News.class);
                    nList.add(msg);
                    NewsList adapter =new NewsList(getActivity(),nList);
                    listViewNews.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
