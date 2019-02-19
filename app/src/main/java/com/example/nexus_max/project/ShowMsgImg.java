package com.example.nexus_max.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ShowMsgImg extends AppCompatActivity {
ImageView imageView;
String imgPath="";
    private StorageReference mStorageRef;
    final long ONE_MEGABYTE = 1024 * 1024;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_msg_img);
        imageView=(ImageView)findViewById(R.id.ivMsgImg);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
           imgPath= null;
        } else {
            imgPath= extras.getString("img_path");
        }

        StorageReference islandRef = mStorageRef.child(imgPath);
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, imageView.getWidth(),
                        imageView.getHeight(), false));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Toast.makeText(ShowMsgImg.this,"Image Download Failed!", Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(ShowMsgImg.this, messages.class);
        startActivity(intent);
    }
}
