package com.example.nexus_max.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReplyPage extends AppCompatActivity {
    ListView listViewReply;
    DatabaseReference dbReply;
    List<ReplyClass> wList;
    String uem="";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_page);
        mAuth = FirebaseAuth.getInstance();

        wList=new ArrayList<>();
        listViewReply=(ListView)findViewById(R.id.ls_reply);
        dbReply= FirebaseDatabase.getInstance().getReference("Replies");
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            uem=email;
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
        dbReply.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                wList.clear();
                for(DataSnapshot msgSnap:dataSnapshot.getChildren()){
                    ReplyClass msg=msgSnap.getValue(ReplyClass.class);
                    /*if(msg!=null) {
                        if (msg.getTo_mail()==uem) {
                            wList.add(msg);
                        }
                    }*/
                    wList.add(msg);

                        ReplyList adapter =new ReplyList(ReplyPage.this,wList);
                        listViewReply.setAdapter(adapter);


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
        Intent intent = new Intent(ReplyPage.this, Main_menu.class);
        startActivity(intent);
    }
}
