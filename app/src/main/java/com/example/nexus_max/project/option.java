package com.example.nexus_max.project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class option extends AppCompatActivity {
 EditText et_loc,et_phno,et_name;
 Spinner sp_calam;
 Button btn_send,btn_show,btn_call;
 DatabaseReference dbMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);


        et_loc=(EditText)findViewById(R.id.et_msg_loc);
        et_phno=(EditText)findViewById(R.id.et_msg_phno);
        et_name=(EditText)findViewById(R.id.et_msg_name);

        sp_calam=(Spinner)findViewById(R.id.sp_calam);

        btn_send=(Button)findViewById(R.id.btn_msg_send) ;
        btn_show=(Button)findViewById(R.id.btn_msg_show) ;
        btn_call=(Button)findViewById(R.id.btn_msg_call) ;

        dbMsg= FirebaseDatabase.getInstance().getReference("Messages");
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg();
            }
        });
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to_phno=et_phno.getText().toString();
                if(notEmpty(to_phno)){
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + to_phno));
                    try{
                        startActivity(intent);
                    }
                    catch(SecurityException e){
                        Toast.makeText(option.this,"Permission denied to call", Toast.LENGTH_LONG).show();
                    }
                    catch(Exception e){
                        Toast.makeText(option.this,"Permission denied to call", Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(option.this,"Please enter a valid phone number", Toast.LENGTH_LONG).show();
                }


            }
        });
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(option.this, messages.class);
                startActivity(intent);
            }
        });
    }
    public String getMyEmail(){
        String email="";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            email = user.getEmail();


            // Check if user's email is verified
           // boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
        return email;
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
    String loc=et_loc.getText().toString();
    String to_phno=et_phno.getText().toString();
    String name=et_name.getText().toString();
    String calam= sp_calam.getSelectedItem().toString();

        if(notEmpty(loc) && notEmpty(to_phno) && notEmpty(name) && notEmpty(calam) ){
            String uid=dbMsg.push().getKey();
            Message msg=new Message(uid,loc,to_phno,name,calam,getMyEmail());
            dbMsg.child(uid).setValue(msg);
            Toast.makeText(option.this,"Message sent !",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(option.this,"All fields must be filled!", Toast.LENGTH_LONG).show();
        }
    }


}
