package com.example.nexus_max.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminEmCallEntry extends AppCompatActivity {
    EditText txtLoc,txtPh;
    Button btnSub;
    DatabaseReference dbEm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_em_call);

        dbEm= FirebaseDatabase.getInstance().getReference("EmergencyPhones");

        txtLoc=(EditText)findViewById(R.id.et_em_office);
        txtPh=(EditText)findViewById(R.id.et_em_phno);
        btnSub=(Button)findViewById(R.id.btn_em_sub);

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
        String temp=txtPh.getText().toString();


        if(notEmpty(loc) && notEmpty(temp) ){
            String uid=dbEm.push().getKey();
            EmergencyCall em=new EmergencyCall(uid,loc,temp);
            dbEm.child(uid).setValue(em);
            Toast.makeText(AdminEmCallEntry.this,"Submitted !",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(AdminEmCallEntry.this,"All fields must be filled!", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(AdminEmCallEntry.this, AdminDash.class);
        startActivity(intent);
    }
}
