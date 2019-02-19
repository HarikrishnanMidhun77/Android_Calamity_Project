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

public class donationreg extends AppCompatActivity {
EditText et_name,et_branch,et_acc,et_ifsc,et_amt;
Button btn_trans;
    DatabaseReference dbDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donationreg);

        et_name=(EditText)findViewById(R.id.et_don_name);
        et_branch=(EditText)findViewById(R.id.et_don_branch);
        et_acc=(EditText)findViewById(R.id.et_don_acc);
        et_ifsc=(EditText)findViewById(R.id.et_don_ifsc);
        et_amt=(EditText)findViewById(R.id.et_don_amt);
        btn_trans=(Button)findViewById(R.id.btn_don_trans);

        dbDon= FirebaseDatabase.getInstance().getReference("Donations");

        btn_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDon();
                Intent intent=new Intent(donationreg.this,DonView.class);
                startActivity(intent);
            }
        });

    }
    public boolean notEmpty(String txt) {
        if (!TextUtils.isEmpty(txt)) {
            return true;
        } else {
            return false;
        }
    }
    private void sendDon(){
        String dname=et_name.getText().toString();
        String dbranch=et_branch.getText().toString();
        String dacc=et_acc.getText().toString();
        String difsc=et_ifsc.getText().toString();
        String damt=et_amt.getText().toString();


        if(notEmpty(dname) && notEmpty(dbranch) && notEmpty(dacc) && notEmpty(difsc) && notEmpty(damt) ){
            String uid=dbDon.push().getKey();
            Donation don=new Donation(uid,dname,dbranch,dacc,difsc,damt);
            dbDon.child(uid).setValue(don);
            Toast.makeText(donationreg.this,"Donation sent Successfully!",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(donationreg.this,"All fields must be filled!", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(donationreg.this, firstt.class);
        startActivity(intent);
    }
}
