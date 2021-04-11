package com.ashish.mutualhelps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.ashish.mutualhelps.MainActivity.extraMessage2;

public class NeedyForm extends AppCompatActivity {
    EditText ProblemBrief,ProblemDis;
    Button ProblemSubmission;
    FirebaseFirestore fstore1;
    String userID1="ljgkkhlkjlg";
    FirebaseAuth firebaseAuth1;

    public static final String extraMessage3="skdfksjfahkds";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needy_form);
        Intent intent=getIntent();
        userID1=intent.getStringExtra(extraMessage2);
        //Toast.makeText(this,userID1,Toast.LENGTH_LONG).show();
        ProblemBrief=findViewById(R.id.ProblemBrief);
        ProblemDis=findViewById(R.id.ProblemDis);
        ProblemSubmission=findViewById(R.id.ButtonProblemSubmit);
        firebaseAuth1=FirebaseAuth.getInstance();
        fstore1=FirebaseFirestore.getInstance();

        ProblemSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Brief= ProblemBrief.getText().toString();
                String Description=ProblemDis.getText().toString();
                DocumentReference documentReference1=fstore1.collection("Problems").document(userID1);
                Map<String,Object> problem=new HashMap<>();
                problem.put("brief",Brief);
                problem.put("description",Description);
                documentReference1.set(problem).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_LONG).show();
                        Intent intent3=new Intent(getApplicationContext(),ProblemSubmitted.class);
                        intent3.putExtra(extraMessage3,userID1);
                        startActivity(intent3);
                    }
                });
                documentReference1.set(problem).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Some error occured.\nPlease try again later",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}