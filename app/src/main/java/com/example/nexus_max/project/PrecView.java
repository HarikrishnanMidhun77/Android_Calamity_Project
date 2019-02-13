package com.example.nexus_max.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class PrecView extends AppCompatActivity {
String heading,desc;
TextView txt_head;
EditText et_desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prec_view);

        txt_head=(TextView)findViewById(R.id.txt_calam_head);
        et_desc=(EditText)findViewById(R.id.et_calam_desc);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {

        } else {
            heading= extras.getString("head");
            desc=extras.getString("desc");
        }

        txt_head.setText(heading);
        et_desc.setText(desc);
    }
}
