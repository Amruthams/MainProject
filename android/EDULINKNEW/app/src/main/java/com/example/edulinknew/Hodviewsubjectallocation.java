package com.example.edulinknew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Hodviewsubjectallocation extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    String[] sid,facultyid,subject,fname,lname,sem;
    ListView lvs;
    FloatingActionButton b1;
    Spinner s1;
    String sems[]={"1","2","3","4"};

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in=new Intent(getApplicationContext(),HOD_home.class);
        startActivity(in);
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hodviewsubjectallocation);

        lvs = (ListView) findViewById(R.id.hodviewallocation);
        b1=findViewById(R.id.floatingActionButton2);
        s1=findViewById(R.id.spinner6);

        b1.setOnClickListener(this);

        ArrayAdapter<String> ad=new ArrayAdapter<String>(Hodviewsubjectallocation.this,android.R.layout.simple_list_item_1,sems);
        s1.setAdapter(ad);
        s1.setOnItemSelectedListener(this);





        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/and_view_subjectallocation/";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js= jsonObj.getJSONArray("data");
                                sid=new String[js.length()];
                                facultyid=new String[js.length()];
                                subject=new String[js.length()];
                                fname=new String[js.length()];
                                lname=new String[js.length()];
                                sem=new String[js.length()];


                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    sid[i]=u.getString("sid");
                                    facultyid[i]=u.getString("facultyid");
                                    subject[i]=u.getString("subject");
                                    fname[i]=u.getString("fname");
                                    lname[i]=u.getString("lname");
                                    sem[i]=u.getString("sem");


                                }


                                lvs.setAdapter(new hod_custom_viewsubject_allocation(getApplicationContext(),sid,facultyid,subject,fname,lname,sem));


                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                String id=sh.getString("lid","");
                params.put("lid",id);

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

    }

    @Override
    public void onClick(View v) {


        startActivity(new Intent(getApplicationContext(),subjectallocation.class));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/and_view_subjectallocation_sem/";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js= jsonObj.getJSONArray("data");
                                sid=new String[js.length()];
                                facultyid=new String[js.length()];
                                subject=new String[js.length()];
                                fname=new String[js.length()];
                                lname=new String[js.length()];
                                sem=new String[js.length()];


                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    sid[i]=u.getString("sid");
                                    facultyid[i]=u.getString("facultyid");
                                    subject[i]=u.getString("subject");
                                    fname[i]=u.getString("fname");
                                    lname[i]=u.getString("lname");
                                    sem[i]=u.getString("sem");


                                }


                                lvs.setAdapter(new hod_custom_viewsubject_allocation(getApplicationContext(),sid,facultyid,subject,fname,lname,sem));


                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                String id=sh.getString("lid","");
                params.put("lid",id);
                params.put("sem",s1.getSelectedItem().toString());

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}