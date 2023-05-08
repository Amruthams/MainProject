package com.example.edulinknew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class tutor_update_students extends AppCompatActivity implements View.OnClickListener {

    EditText ktu,fname,lname,house,place,post,pin,district,email,phno,dob;
    ImageView img;
    Button btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_update_students);


        ktu=findViewById(R.id.editTextTextPersonName8);
        fname=findViewById(R.id.editTextTextPersonName9);
        lname=findViewById(R.id.editTextTextPersonName10);
        house=findViewById(R.id.editTextTextPersonName11);
        place=findViewById(R.id.editTextTextPersonName12);
        post=findViewById(R.id.editTextTextPersonName13);
        pin=findViewById(R.id.editTextTextPersonName14);
        district=findViewById(R.id.editTextTextPersonName15);
        email=findViewById(R.id.editTextTextPersonName16);
        phno=findViewById(R.id.editTextTextPersonName17);
        dob=findViewById(R.id.editTextTextPersonName18);

        img=findViewById(R.id.imageView4);

        btn=findViewById(R.id.button8);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        final String ktu_d = ktu.getText().toString();
        final String finame = fname.getText().toString();
        final String laname = lname.getText().toString();
        final String houses = house.getText().toString();
        final String places = place.getText().toString();
        final String posts = post.getText().toString();
        final String pinn = pin.getText().toString();
        final String dis = district.getText().toString();
        final String mail = email.getText().toString();
        final String Phno = phno.getText().toString();
        final String Dob = dob.getText().toString();


        if (ktu_d.length()==0){
            ktu.setError("Missing");
            ktu.requestFocus();

        } else if (finame.length()==0) {
            fname.setError("Missing");
            fname.requestFocus();
        } else if (laname.length()==0) {
            lname.setError("Missing");
            lname.requestFocus();
        } else if (houses.length()==0) {
            house.setError("Missing");
            house.requestFocus();
        } else if (places.length()==0) {
            place.setError("Missing");
            place.requestFocus();
        } else if (posts.length()==0) {
            post.setError("Missing");
            post.requestFocus();
        } else if (pinn.length()==0) {
            pin.setError("Missing");
            pin.requestFocus();
        } else if (dis.length()==0) {
            district.setError("Missing");
            district.requestFocus();
        } else if (mail.length()==0) {
            email.setError("Missing");
            email.requestFocus();
        } else if (Phno.length()==0) {
            phno.setError("Missing");
            phno.requestFocus();
        }
        else if (Dob.length()==0) {
            dob.setError("Missing");
            dob.requestFocus();
        }
        else{


        }
    }
}