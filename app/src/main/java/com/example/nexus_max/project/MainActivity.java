package com.example.nexus_max.project;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler.postDelayed(new Thread() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, firstt.class);
                startActivity(intent);
            }
        }, 3000); // 4 seconds

    }
}
