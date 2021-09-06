package com.example.loginsignupform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailed,passworded;
    Button loginbtn;
    FirebaseAuth auth1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login Form");

        emailed = findViewById(R.id.email1);
        passworded = findViewById(R.id.password1);
        loginbtn = findViewById(R.id.log);
        auth1 = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }



    public void btn_signupForm(View view) {
        startActivity(new Intent(getApplicationContext(), signupActivity.class));
    }

    public void  loginUser(){

        String stremailed = emailed.getText().toString();
        String strpassworded = passworded.getText().toString();
        if (stremailed.isEmpty()){
            emailed.setError("Enter Email!");
        }
        else if(strpassworded.isEmpty()){
            passworded.setError("Enter Password!");
        }
        else {
            auth1.signInWithEmailAndPassword(stremailed, strpassworded)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(LoginActivity.this, "Loged in!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), welcomeActivity.class));
                            finish();
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(LoginActivity.this, "Not Loged in!", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}