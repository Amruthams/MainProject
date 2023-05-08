package com.example.edulinknew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    Button b1,b2,b3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        b1=(Button) findViewById(R.id.button3);
        b2=(Button) findViewById(R.id.button4);
        b3=(Button) findViewById(R.id.button9);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == b1){
            Intent in=new Intent(getApplicationContext(),teacher_profile.class);
            startActivity(in);

        }
        if (v == b2){
            Intent in=new Intent(getApplicationContext(),changepassword_teacher.class);
            startActivity(in);

        }
        if (v == b3){
            Intent in=new Intent(getApplicationContext(),teacher_view_students.class);
            startActivity(in);

        }

    }
}