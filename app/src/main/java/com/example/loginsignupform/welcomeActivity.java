package com.example.loginsignupform;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;
import android.service.voice.AlwaysOnHotwordDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class welcomeActivity extends AppCompatActivity {

    Button upload,show;
    ImageView image;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("image");
    StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        image = findViewById(R.id.image_view);
        upload = findViewById(R.id.btn_upload);
        show = findViewById(R.id.btn_show);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent  = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/^");
                startActivityForResult(galleryIntent, 2);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri!=null){
                    uploadInFirebase(imageUri);
                }
                else{
                    Toast.makeText(welcomeActivity.this, "Please select image!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2 && resultCode==RESULT_OK && data!=null){
            imageUri = data.getData();
            image.setImageURI(imageUri);
        }
    }

    private void uploadInFirebase(Uri uri){

        StorageReference fileref = reference.child(System.currentTimeMillis() + "." + "jpg");
        fileref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Toast.makeText(welcomeActivity.this, "uploaded successfully!", Toast.LENGTH_SHORT).show();
                    }

        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                Toast.makeText(welcomeActivity.this, "uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(welcomeActivity.this, "uploading fail!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btn_signout(View view) {

        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}