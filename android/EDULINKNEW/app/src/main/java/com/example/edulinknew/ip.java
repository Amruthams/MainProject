package com.example.edulinknew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ip extends AppCompatActivity implements View.OnClickListener {

    EditText edip;
    Button bt_connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        edip = (EditText) findViewById(R.id.editTextTextPersonName);
        bt_connect = (Button) findViewById(R.id.button);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        edip.setText(sh.getString("ip",""));
        bt_connect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String ipadrs = edip.getText().toString();


        if (ipadrs.length()==0){
            edip.setError("Missing");
        }
        else{
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed = sh.edit();
            ed.putString("ip", ipadrs);
            ed.commit();
            Intent it = new Intent(getApplicationContext(), login.class);
            startActivity(it);
        }
    }
}