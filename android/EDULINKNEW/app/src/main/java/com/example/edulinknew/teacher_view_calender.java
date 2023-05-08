package com.example.edulinknew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.function.Predicate;

public class teacher_view_calender extends AppCompatActivity {

    WebView w1;

    SharedPreferences sh;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view_calender);

        w1=findViewById(R.id.w1);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");

        w1.loadUrl("http://" + hu + ":5000/");

        // this will enable the javascript.
        w1.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        w1.setWebViewClient(new WebViewClient());


    }
}