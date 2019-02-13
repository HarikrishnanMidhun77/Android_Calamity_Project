package com.example.nexus_max.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Reply extends AppCompatActivity {
    DatabaseReference dbrep;
    Button btn_send;
    EditText et_reply;
    String to_mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            to_mail= null;
        } else {
            to_mail= extras.getString("STRING_I_NEED");
        }
        dbrep= FirebaseDatabase.getInstance().getReference("Replies");
        et_reply=(EditText)findViewById(R.id.et_reply);
        btn_send=(Button)findViewById(R.id.btn_reply_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg();
                Intent intent = new Intent(Reply.this, messages.class);
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
    private void sendMsg(){
        String rep=et_reply.getText().toString();

        if(notEmpty(rep) ){
            String uid=dbrep.push().getKey();
            ReplyClass msg=new ReplyClass(uid,to_mail,getMyEmail(),rep);
            dbrep.child(uid).setValue(msg);
            Toast.makeText(Reply.this,"Message sent !",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(Reply.this,"All fields must be filled!", Toast.LENGTH_LONG).show();
        }
    }
}
