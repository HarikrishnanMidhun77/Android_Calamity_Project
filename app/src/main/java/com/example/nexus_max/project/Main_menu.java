package com.example.nexus_max.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Main_menu extends AppCompatActivity {
    Button btn_msg,btn_prec,btn_notif,btn_camp,btn_feedb,btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btn_msg=(Button)findViewById(R.id.btn_main_msg) ;
        btn_prec=(Button)findViewById(R.id.btn_main_prec) ;
        btn_notif=(Button)findViewById(R.id.btn_main_notif) ;
        btn_camp=(Button)findViewById(R.id.btn_main_camp) ;
        btn_feedb=(Button)findViewById(R.id.btn_main_feedb) ;
        btn_logout=(Button)findViewById(R.id.btn_main_logout) ;

        btn_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_menu.this, option.class);
                startActivity(intent);
            }
        });

        btn_prec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_menu.this, Main_menu.class);
                startActivity(intent);
            }
        });

        btn_notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_menu.this, Main_menu.class);
                startActivity(intent);
            }
        });

        btn_camp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_menu.this, camplogin.class);
                startActivity(intent);
            }
        });

        btn_feedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_menu.this, feedback.class);
                startActivity(intent);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
            }
        });
    }
}
