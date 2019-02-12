package com.example.nexus_max.project;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class otp extends AppCompatActivity {
Button btn_done,btn_cancel,btn_resend;
EditText et_otp;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        btn_done=(Button)findViewById(R.id.btn_otp_done) ;
        btn_cancel=(Button)findViewById(R.id.btn_otp_cancel) ;
        btn_resend=(Button)findViewById(R.id.btn_otp_resend) ;
        et_otp=(EditText)findViewById(R.id.et_otp_otp);


        readOtp();
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(otp.this, Main_menu.class);
                startActivity(intent);
            }
        });
      btn_cancel.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(otp.this, firstt.class);
              startActivity(intent);
          }
      });
      btn_resend.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              readOtp();
          }
      });
    }
    private void readOtp(){
        Random r = new Random();
        final int rno = r.nextInt(100000 - 999999) + 999999;
        mHandler.postDelayed(new Thread() {
            @Override
            public void run() {
                et_otp.setText(String.valueOf(rno));
            }
        }, 4000);
    }
}
