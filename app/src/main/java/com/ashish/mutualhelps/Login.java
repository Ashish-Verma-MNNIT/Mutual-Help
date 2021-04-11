package com.ashish.mutualhelps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText Email,Password;
    Button ButtonLogin;
    TextView NewUser;
    FirebaseAuth firebaseAuth;
    public static final String extraMessage="hkhlkjhk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email=findViewById(R.id.Email);
        Password=findViewById(R.id.Password);
        ButtonLogin=findViewById(R.id.Button_Login);
        NewUser=findViewById(R.id.NewUser);
        firebaseAuth=FirebaseAuth.getInstance();

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=Email.getText().toString().trim();
                String password=Password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)||password.length()<6){
                    Password.setError("Enter valid password\n(must be at least 6 characters long");
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(),MainActivity.class);
                            i.putExtra(extraMessage,FirebaseAuth.getInstance().getCurrentUser().getUid());
                            startActivity(i);
                        }else{
                            Toast.makeText(Login.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });
        NewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
    }
}