package com.example.loginsignupform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.SecureRandom;

public class signupActivity extends AppCompatActivity {

    EditText  name,username,email,password,cn_pass;
    //RadioButton male,female;
    Button  register,login;
    FirebaseAuth auth;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase root;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Sign Up");

        auth = FirebaseAuth.getInstance();
        name = (EditText)findViewById(R.id.fulname);
        username = (EditText)findViewById(R.id.user);
        email = (EditText)findViewById(R.id.eml);
        password = (EditText)findViewById(R.id.pass);
        cn_pass = (EditText)findViewById(R.id.cn);
       // male  = (RadioButton) findViewById(R.id.mal);
        //female = (RadioButton)findViewById(R.id.fmal);
        register  = (Button)findViewById(R.id.reg);
        login = (Button)findViewById(R.id.log);


        //firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateUser();

                String strname = name.getText().toString();
               String struser = username.getText().toString();
                String stremail= email.getText().toString();
                String strpass = password.getText().toString();
                String strcn = cn_pass.getText().toString();
              //  String strmale = male.getText().toString();
                //String strfemale = female.getText().toString();

                root = FirebaseDatabase.getInstance();
                reference = root.getReference("users");

                dummy helper = new dummy(strcn,stremail,strname,strpass,struser);
                reference.child("Post").push().setValue(helper);
                Toast.makeText(signupActivity.this, "Data Stored in DataBase!", Toast.LENGTH_SHORT).show();
                
            }
        });



    }

    public void btn_loginForm(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class ));
    }

    public void CreateUser(){
        String stremail= email.getText().toString();
        String strpass = password.getText().toString();
        if (stremail.isEmpty()){
            email.setError("Enter Email!");
        }
        else if(strpass.isEmpty()){
            password.setError("Enter Password!");
        }
        else
        {
            auth.createUserWithEmailAndPassword(stremail,strpass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            Toast.makeText(signupActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(signupActivity.this, "Registration Error!", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}