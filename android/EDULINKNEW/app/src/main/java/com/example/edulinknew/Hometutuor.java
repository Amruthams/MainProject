package com.example.edulinknew;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.edulinknew.databinding.ActivityHometutuorBinding;

public class Hometutuor extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHometutuorBinding binding;

    CardView c1,c2,c3,c4,c5,c6,c7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHometutuorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        c1=findViewById(R.id.cardView);
        c2=findViewById(R.id.cardView2);
        c3=findViewById(R.id.cardview3);
        c4=findViewById(R.id.cardview4);
        c5=findViewById(R.id.cardview5);
        c6=findViewById(R.id.cardview6);
        c7=findViewById(R.id.cardview7);



        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),tutor_view_class.class);
                startActivity(in);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),tutor_view_students.class);
                startActivity(in);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),tutor_view_timetable.class);
                startActivity(in);

            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),teacher_view_overallattendance.class);
                startActivity(in);

            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),tutor_medical_leave.class);
                startActivity(in);

            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),tutor_chat_students.class);
                startActivity(in);

            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),tutor_notification.class);
                startActivity(in);

            }
        });


        setSupportActionBar(binding.appBarHometutuor.toolbar);
//        binding.appBarHometutuor.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_hometutuor);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hometutuor, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_hometutuor);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (R.id.thome==id){

            Intent in = new Intent(getApplicationContext(), Hometutuor.class);
            startActivity(in);
        }
        if (R.id.tpro==id){
            Intent in=new Intent(getApplicationContext(),tutor_profile.class);
            startActivity(in);
        }

        if(R.id.changep==id){
            Intent in= new Intent(getApplicationContext(),changepassword.class);
            startActivity(in);
        }

        if(R.id.myclass==id){
            Intent in= new Intent(getApplicationContext(),teacher_home.class);
            startActivity(in);
        }
        if(R.id.logout==id){
            Intent in= new Intent(getApplicationContext(),login.class);
            startActivity(in);
        }


        return false;
    }
//    @Override
//    public void onClick(View v) {
//
//        if(v==c1){
//            startActivity(new Intent(getApplicationContext(),tutor_view_class.class));
//        }
//
//    }

}