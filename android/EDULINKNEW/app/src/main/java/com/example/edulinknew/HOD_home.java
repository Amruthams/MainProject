package com.example.edulinknew;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

import com.example.edulinknew.databinding.ActivityHodHomeBinding;

public class HOD_home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHodHomeBinding binding;

    CardView c1,c2,c3,c4,c5;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in=new Intent(getApplicationContext(),login.class);
        startActivity(in);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHodHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHodHome.toolbar);
//        binding.appBarHodHome.fab.setOnClickListener(new View.OnClickListener() {
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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_hod_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);

        c1=findViewById(R.id.cardView);
        c2=findViewById(R.id.cardview2);
        c3=findViewById(R.id.cardview3);
        c4=findViewById(R.id.cardview4);
        c5=findViewById(R.id.cardview5);


        c1.setOnClickListener(this);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Hod_viewsubject.class));

            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),timetable.class));
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),hod_view_students.class));
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),notification.class));
            }
        });







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.h_o_d_home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_hod_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if (R.id.hhome == id) {
            Intent in = new Intent(getApplicationContext(), HOD_home.class);
            startActivity(in);

        }


        if(R.id.profile==id){
            Intent in= new Intent(getApplicationContext(),hod_profile.class);
            startActivity(in);
        }

        if(R.id.chngep==id){
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

    @Override
    public void onClick(View v) {

        if(v==c1){
            startActivity(new Intent(getApplicationContext(),Hodviewsubjectallocation.class));
        }

//        if(v==c1){
//            startActivity(new Intent(getApplicationContext(),addsubject.class));
//        }



    }
}