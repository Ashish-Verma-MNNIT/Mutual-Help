package com.ashish.mutualhelps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.ashish.mutualhelps.NeedyForm.extraMessage3;

public class ProblemSubmitted extends AppCompatActivity {
    Button OK;
    String userID;
    public static final String extraMessage="skdfksjfahkds";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_submitted);
        Intent j=getIntent();
        userID=j.getStringExtra(extraMessage3);


        OK=findViewById(R.id.OKbutton);
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(getApplicationContext(),MainActivity.class);
                x.putExtra(extraMessage,userID);
                startActivity(x);
            }
        });
    }
}