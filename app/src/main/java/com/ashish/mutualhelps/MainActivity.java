package com.ashish.mutualhelps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static com.ashish.mutualhelps.Register.extraMessage;

public class MainActivity extends AppCompatActivity {
    String userID2="fjkhglhgk";
    public static final String extraMessage2="skdfksjfahkds";

    Button Needy, Helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        userID2=intent.getStringExtra(extraMessage);
       // Toast.makeText(getApplicationContext(),userID2, Toast.LENGTH_LONG).show();
        Needy=findViewById(R.id.Needy);
        Helper=findViewById(R.id.Helper);

        Needy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),NeedyForm.class);
                intent1.putExtra(extraMessage2,userID2);
                startActivity(intent1);

            }
        });

        Helper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Helper.class);
                startActivity(i);
            }
        });
    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

}