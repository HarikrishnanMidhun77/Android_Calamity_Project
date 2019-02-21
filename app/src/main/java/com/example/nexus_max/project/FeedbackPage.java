package com.example.nexus_max.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class feedback extends AppCompatActivity {
Button btnFb;
EditText etFb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        btnFb=(Button)findViewById(R.id.btn_fb_sub);
        etFb=(EditText)findViewById(R.id.et_fb_fb);

        btnFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            String uid = user.getUid();
        }
        return email;
    }
}
