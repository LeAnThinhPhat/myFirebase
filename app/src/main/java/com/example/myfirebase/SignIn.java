package com.example.myfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    private EditText etEmail, etPass;
    private Button btnSignIn;
    private FirebaseAuth auth;
    private TextView tvRegister,tvGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        auth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.etEmail_SignIn);
        etPass = findViewById(R.id.etPass_SignIn);
        btnSignIn= findViewById(R.id.btnSign_SignInGG);
        tvRegister = findViewById(R.id.tvRegister);
        tvGoogle =findViewById(R.id.tvGoogle_SignIn);
        tvGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SignIn.this,Google.class);
//                startActivity(intent);
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.myfirebase.SignIn.this,Register.class);
                startActivity(intent);
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String pass = etPass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(com.example.myfirebase.SignIn.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(com.example.myfirebase.SignIn.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(com.example.myfirebase.SignIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(!task.isSuccessful()){
                                    if(pass.length()<6){
                                        Toast.makeText(com.example.myfirebase.SignIn.this, "Enter Pass", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(com.example.myfirebase.SignIn.this, "Sign in failed,check email or password", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Intent intent = new Intent(com.example.myfirebase.SignIn.this,Finish.class);
                                    startActivity(intent);
                                }

                            }
                        }) ;

            }
        });
    }
}