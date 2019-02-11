package com.example.nexus_max.project;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class signin extends AppCompatActivity {
    private static final String TAG="signin";
    private FirebaseAuth mAuth;
    Button btn_signin;
  EditText et_phone,et_email,et_pswd,et_conf_pswd,et_name,et_place;
    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );

     boolean validate(String checkmail,String pass,String cpass) {
        boolean temp=true;
        if(!EMAIL_ADDRESS_PATTERN.matcher(checkmail).matches()){
            Toast.makeText(signin.this,"Invalid Email Address",Toast.LENGTH_SHORT).show();
            temp=false;
        }
        else if(!pass.equals(cpass)){
            Toast.makeText(signin.this,"Password Not matching",Toast.LENGTH_SHORT).show();
            temp=false;
        }
        return temp;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();

        btn_signin=(Button)findViewById(R.id.btn_signin_signin) ;
        et_phone=(EditText)findViewById(R.id.et_signin_phone);
        et_email=(EditText)findViewById(R.id.et_signin_email);
        et_pswd=(EditText)findViewById(R.id.et_signin_pswd);
        et_conf_pswd=(EditText)findViewById(R.id.et_signin_confpswd);
        et_name=(EditText)findViewById(R.id.et_signin_name);
        et_place=(EditText)findViewById(R.id.et_signin_place);



        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=et_email.getText().toString();
                String password=et_pswd.getText().toString();
                String password2=et_conf_pswd.getText().toString();
                if(validate(email,password,password2)){
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(signin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        // updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(signin.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }

            }
        });


    }
}
