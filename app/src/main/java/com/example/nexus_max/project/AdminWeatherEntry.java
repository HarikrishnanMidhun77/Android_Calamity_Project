package com.example.nexus_max.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminWeatherEntry extends AppCompatActivity {
EditText txtLoc,txtTemp;
Button btnSub;
    DatabaseReference dbweath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_weather_entry);

        dbweath= FirebaseDatabase.getInstance().getReference("Weathers");

        txtLoc=(EditText)findViewById(R.id.et_aWeath_loc);
        txtTemp=(EditText)findViewById(R.id.et_aWeath_temp);
        btnSub=(Button)findViewById(R.id.btn_aWeath_sub);

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg();
            }
        });

    }
    public boolean notEmpty(String txt){
        if(!TextUtils.isEmpty(txt)){
            return  true;
        }
        else{
            return false;
        }
    }
    private void sendMsg(){
        String loc=txtLoc.getText().toString();
        String temp=txtTemp.getText().toString();


        if(notEmpty(loc) && notEmpty(temp) ){
            String uid=dbweath.push().getKey();
            Weather weath=new Weather(uid,loc,temp);
            dbweath.child(uid).setValue(weath);
            Toast.makeText(AdminWeatherEntry.this,"Submitted !",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(AdminWeatherEntry.this,"All fields must be filled!", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(AdminWeatherEntry.this, AdminDash.class);
        startActivity(intent);
    }
}
