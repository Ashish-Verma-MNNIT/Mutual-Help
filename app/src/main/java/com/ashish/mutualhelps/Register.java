package com.ashish.mutualhelps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText FullName, Email, Password,Phone;
    Button ButtonRegister;
    TextView AlreadyRegistered;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fstore;
    String userID="xyzwabldfl";
    public static final String extraMessage="skdfksjfahkds";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FullName = findViewById(R.id.FullName);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Phone=findViewById(R.id.Phone);
        ButtonRegister=findViewById(R.id.Button_Register);
        AlreadyRegistered=findViewById(R.id.NewUser);
        firebaseAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();


        if(firebaseAuth.getCurrentUser()!=null){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            String message=FirebaseAuth.getInstance().getCurrentUser().getUid();
            intent.putExtra(extraMessage,message);
            startActivity(intent);
            finish();
        }

        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=Email.getText().toString().trim();
                String password=Password.getText().toString().trim();
                String fullname=FullName.getText().toString();
                String phone=Phone.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)||password.length()<6){
                    Password.setError("Enter valid password\n(must be at least 6 characters long");
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "user created",Toast.LENGTH_SHORT).show();
                            userID=firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference=fstore.collection("users").document(userID);
                            Map<String,Object> user=new HashMap<>();
                            user.put("fname",fullname);
                            user.put("email",email);
                            user.put("phone",phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG","onSuccess: "+"user profile is created for "+userID);
                                }
                            });
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            String message=FirebaseAuth.getInstance().getCurrentUser().getUid();
                            intent.putExtra(extraMessage,message);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Register.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        AlreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}
