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

public class AdminCalamEntry extends AppCompatActivity {
Button btn_sub;
EditText et_Acalam,et_Aprec;
    DatabaseReference dbPrec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_calam_entry);

        btn_sub=(Button)findViewById(R.id.btn_aCalam_submit);
        et_Acalam=(EditText)findViewById(R.id.et_aCalam_calam);
        et_Aprec=(EditText)findViewById(R.id.et_aCalam_prec);

        dbPrec= FirebaseDatabase.getInstance().getReference("Precautions");

        btn_sub.setOnClickListener(new View.OnClickListener() {
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
        String calam=et_Acalam.getText().toString();
        String prectxt=et_Aprec.getText().toString();


        if(notEmpty(prectxt) && notEmpty(calam) ){
            String uid=dbPrec.push().getKey();
            Precaution prec=new Precaution(uid,calam,prectxt);
            dbPrec.child(uid).setValue(prec);
            Toast.makeText(AdminCalamEntry.this,"Submitted !",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(AdminCalamEntry.this,"All fields must be filled!", Toast.LENGTH_LONG).show();
        }
    }
}
