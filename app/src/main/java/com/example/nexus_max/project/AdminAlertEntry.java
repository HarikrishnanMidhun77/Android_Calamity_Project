package com.example.nexus_max.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAlertEntry extends AppCompatActivity {
    EditText txtLoc,txtAl;
    Button btnSub;
    DatabaseReference dbAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_alert_entry);

        dbAlert= FirebaseDatabase.getInstance().getReference("Alerts");

        txtLoc=(EditText)findViewById(R.id.et_aAlert_loc);
        txtAl=(EditText)findViewById(R.id.et_aAlert_type);
        btnSub=(Button)findViewById(R.id.btn_aAlert_sub);

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
        String ale=txtAl.getText().toString();


        if(notEmpty(loc) && notEmpty(ale) ){
            String uid=dbAlert.push().getKey();
            Alert alert=new Alert(uid,loc,ale);
            dbAlert.child(uid).setValue(alert);
            Toast.makeText(AdminAlertEntry.this,"Submitted !",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(AdminAlertEntry.this,"All fields must be filled!", Toast.LENGTH_LONG).show();
        }
    }
}
