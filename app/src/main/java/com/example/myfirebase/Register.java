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

public class Register extends AppCompatActivity {
    private EditText etEmail, etPassword_1,etPassword_2,etName;
    private FirebaseAuth auth;
    private Button btnRegister;
    private TextView tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail_Register);
        etPassword_1 = findViewById(R.id.etPass_1_Register);
        etPassword_2 = findViewById(R.id.etPass_2_Register);
        btnRegister = findViewById(R.id.btnRegister_Register);
        tvSignIn = findViewById(R.id.tvSignIn);
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.myfirebase.Register.this,SignIn.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =etName.getText().toString().trim();
                String email =etEmail.getText().toString().trim();
                String pass_1 =etPassword_1.getText().toString().trim();
                String pass_2 =etPassword_2.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getApplication(),"Enter name!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplication(),"Enter email address !",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass_1.length()<6){
                    Toast.makeText(getApplication(),"Enter Password !",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass_1.equals(pass_2)==false){
                    Toast.makeText(getApplication(),"Enter Password!",Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email,pass_1)
                        .addOnCompleteListener(com.example.myfirebase.Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(com.example.myfirebase.Register.this, "createUserWithEmail:onComplete: "+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                if(task.isSuccessful()==false){
                                    Toast.makeText(com.example.myfirebase.Register.this, "Authentication failed."+task.getException(), Toast.LENGTH_SHORT).show();
                                }else {
                                    Intent intent = new Intent(com.example.myfirebase.Register.this,Finish.class);
                                    startActivity(intent);

                                }
                            }
                        });
            }
        });
    }
}