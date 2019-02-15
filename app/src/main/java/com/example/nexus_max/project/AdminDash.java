package com.example.nexus_max.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminDash extends AppCompatActivity {
Button camp,prec,weath,news,alert,feedb,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);

        camp=(Button)findViewById(R.id.btn_aDash_camp);
        prec=(Button)findViewById(R.id.btn_aDash_prec);
        weath=(Button)findViewById(R.id.btn_aDash_weath);
        news=(Button)findViewById(R.id.btn_aDash_news);
        alert=(Button)findViewById(R.id.btn_aDash_alert);
        feedb=(Button)findViewById(R.id.btn_aDash_feedb);
        logout=(Button)findViewById(R.id.btn_aDash_logout);

        camp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        prec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDash.this, AdminCalamEntry.class);
                startActivity(intent);

            }
        });
        weath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDash.this, AdminWeatherEntry.class);
                startActivity(intent);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDash.this, AdminNewsEntry.class);
                startActivity(intent);
            }
        });
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDash.this, AdminAlertEntry.class);
                startActivity(intent);

            }
        });
        feedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDash.this, firstt.class);
                startActivity(intent);

            }
        });
    }
}
