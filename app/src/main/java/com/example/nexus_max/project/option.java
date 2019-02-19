package com.example.nexus_max.project;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class option extends AppCompatActivity {
 EditText et_loc,et_phno,et_name;
 Spinner sp_calam;
 Button btn_send,btn_show,btn_call;
 ImageButton loc,cam;
 DatabaseReference dbMsg;
    Geocoder geocoder;
 String imgName="123",locFind="Thrissur",imgPath="images/noimg.jpg";
    String mCurrentPhotoPath="";
    Uri pic;
    List<Address> addresses;
    private StorageReference mStorageRef;
    private FusedLocationProviderClient mFusedLocationClient;

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);



        geocoder = new Geocoder(this, Locale.getDefault());




        et_loc=(EditText)findViewById(R.id.et_msg_loc);
        et_phno=(EditText)findViewById(R.id.et_msg_phno);
        et_name=(EditText)findViewById(R.id.et_msg_name);
        sp_calam=(Spinner)findViewById(R.id.sp_calam);
        btn_send=(Button)findViewById(R.id.btn_msg_send) ;
        btn_show=(Button)findViewById(R.id.btn_msg_show) ;
        btn_call=(Button)findViewById(R.id.btn_msg_call) ;
        loc=(ImageButton) findViewById(R.id.imgLocShare) ;
        cam=(ImageButton) findViewById(R.id.imgCam) ;


        try {


            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    locFind = addresses.get(0).getLocality();
                                }catch(IOException e){
                                    Toast.makeText(option.this,"Device GPS error!", Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(option.this,"Device GPS error!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }
        catch (SecurityException e){
             Toast.makeText(option.this,"Permission Denied for location!", Toast.LENGTH_LONG).show();
        }


        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_loc.setText(locFind);
            }
        });

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dispatchTakePictureIntent();
            //uploadPic();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                      //  uploadPic();
                    }
                }, 3000);
            }
        });

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
            Message msg=new Message(uid,loc,to_phno,name,calam,getMyEmail(),imgPath);
            dbMsg.child(uid).setValue(msg);
            Toast.makeText(option.this,"Message sent !",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(option.this,"All fields must be filled!", Toast.LENGTH_LONG).show();
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        imgName=imageFileName;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();

            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.nexus_max.project",
                        photoFile);
                pic=photoURI;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

            }

        }
    }
    private void uploadPic(){

        //Uri file = Uri.fromFile(new File(pic.toString()));
        Uri file = Uri.fromFile(new File(mCurrentPhotoPath));
        StorageReference riversRef = mStorageRef.child("images/"+imgName);
        imgPath="images/"+imgName;
        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                      //  Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(option.this,"Upload Success!", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(option.this,"Upload failed!"+mCurrentPhotoPath, Toast.LENGTH_LONG).show();
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
         uploadPic();
        }
    }
    public static Location getLastKnownLoaction(boolean enabledProvidersOnly, Context context){
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location utilLocation = null;

        try {
            List<String> providers = manager.getProviders(enabledProvidersOnly);

            for (String provider : providers) {


                utilLocation = manager.getLastKnownLocation(provider);
                if (utilLocation != null) return utilLocation;
            }
        }
        catch (SecurityException e){
           // Toast.makeText(option.this,"Permission Denied!", Toast.LENGTH_LONG).show();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(option.this, Main_menu.class);
        startActivity(intent);
    }
}
