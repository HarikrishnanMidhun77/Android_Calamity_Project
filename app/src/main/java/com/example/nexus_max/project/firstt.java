package com.example.nexus_max.project;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class firstt extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button user_login_btn;
    Button btn_signup;
    Button btn_rel_camp;
    Button btn_don;
    Button btn_forg_pswd;
    EditText user_name_login;
    EditText user_password_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstt);

        mAuth = FirebaseAuth.getInstance();
        btn_signup=(Button)findViewById(R.id.btn_cr8acc) ;
        btn_rel_camp=(Button)findViewById(R.id.btn_rel_camp) ;
        btn_don=(Button)findViewById(R.id.btn_don) ;
        btn_forg_pswd=(Button)findViewById(R.id.btn_forg_pswd) ;
        user_login_btn= (Button)findViewById(R.id.btn_user_login);
        user_name_login=(EditText)findViewById(R.id.et_uname_login);
        user_password_login=(EditText)findViewById(R.id.et_upswd_login);

btn_signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(firstt.this, signin.class);
        startActivity(intent);
    }
});
btn_rel_camp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
btn_don.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
btn_forg_pswd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
        user_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=user_name_login.getText().toString();
                String password=user_password_login.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(firstt.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                  //  Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                   // updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("first", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(firstt.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
}
