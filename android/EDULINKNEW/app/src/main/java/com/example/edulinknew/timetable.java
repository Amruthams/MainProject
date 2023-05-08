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
import android.widget.GridView;
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

public class timetable extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] day,hour,SUBJECT_ID,name,semester;
    GridView timetable;
    FloatingActionButton b1;



    Spinner s1,s2;

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
        setContentView(R.layout.activity_timetable);


        timetable = findViewById(R.id.gdv);
        s1 = findViewById(R.id.spinner17);
        s2 = findViewById(R.id.spinner19);
        b1=findViewById(R.id.floatingActionButton4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),add_timetable.class));
            }
        });

        show_courses();
        show_sem();

//
//        ArrayAdapter<String> ad1 = new ArrayAdapter<>(timetable.this, android.R.layout.simple_spinner_item.);
//        s1.setAdapter(ad1);

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                Toast.makeText(this, "hlooooo", Toast.LENGTH_SHORT).show();
//                Toast.makeText(timetable.this, "hiiiiiiiiiiiiiiiiii", Toast.LENGTH_SHORT).show();
                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":8000/myapp/hod_view_timetable/";

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                        JSONArray js= jsonObj.getJSONArray("timetabl");
                                        int index=0;
                                        String [] sub= new String[25];
                                      //  Toast.makeText(timetable.this, ""+js.length(), Toast.LENGTH_SHORT).show();

                                        for(int i=0;i<js.length();i++)
                                        {
                                            JSONArray jb= js.getJSONArray(i);

                                            for(int m=0;m<5;m++) {




                                                sub[index] = jb.getJSONObject(m).getString("s");
                                                index = index + 1;

//                                                Toast.makeText(getApplicationContext(), i + "====" +toString(), Toast.LENGTH_LONG).show();
                                            }

                                        }



                                        timetable.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,sub));







//                                        day=new String[js.length()];
//                                        hour=new String[js.length()];
//                                        SUBJECT_ID=new String[js.length()];
//                                        name=new String[js.length()];
//                                        semester=new String[js.length()];
//
//
//                                        for(int i=0;i<js.length();i++)
//                                        {
//                                            JSONObject u=js.getJSONObject(i);
//                                            day[i]=u.getString("day");
//                                            hour[i]=u.getString("hour");
//                                            SUBJECT_ID[i]=u.getString("SUBJECT_ID");
//                                            name[i]=u.getString("name");
//                                            semester[i]=u.getString("sem");
//
//
//                                        }
//
//                                        timetable.setAdapter(new view_timetable(getApplicationContext(),day,hour,SUBJECT_ID,name,semester));

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
                        params.put("program",sc_id[s1.getSelectedItemPosition()]);
                        params.put("select2",s2.getSelectedItem().toString());

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


//                Toast.makeText(this, "hlooooo", Toast.LENGTH_SHORT).show();
//                Toast.makeText(timetable.this, "hloooooooooooooooo", Toast.LENGTH_SHORT).show();
                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":8000/myapp/hod_view_timetable/";

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                        JSONArray js= jsonObj.getJSONArray("timetabl");
                                        day=new String[js.length()];
                                        hour=new String[js.length()];
                                        SUBJECT_ID=new String[js.length()];
                                        name=new String[js.length()];
                                        semester=new String[js.length()];


                                        for(int i=0;i<js.length();i++)
                                        {
                                            JSONObject u=js.getJSONObject(i);
                                            day[i]=u.getString("day");
                                            hour[i]=u.getString("hour");
                                            SUBJECT_ID[i]=u.getString("SUBJECT_ID");
                                            name[i]=u.getString("name");
                                            semester[i]=u.getString("sem");


                                        }

                                        timetable.setAdapter(new view_timetable(getApplicationContext(),day,hour,SUBJECT_ID,name,semester));

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
                        params.put("program",sc_id[s1.getSelectedItemPosition()]);
                        params.put("select2",s2.getSelectedItem().toString());

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
        });


    }

    int sem_count = 4;
    String[] sem_id, s_sem;

    public void show_sem() {
        sem_id = new String[sem_count];
        for (int i = 1; i <= sem_count; i++) {
            sem_id[i - 1] = i + "";
        }

        ArrayAdapter<String> ad = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, sem_id);
        s2.setAdapter(ad);


    }





    String [] sc_id,sc_pname;
    public void show_courses()
    {

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String maclis=sh.getString("mac_list","");
        String uid=sh.getString("uid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/show_program/";



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
                                sc_id=new String[js.length()];
                                sc_pname=new String[js.length()];

                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    sc_id[i]=u.getString("id");
                                    sc_pname[i]=u.getString("pname");

                                }

                                ArrayAdapter<String> ad=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,sc_pname);
                                s1.setAdapter(ad);

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


                params.put("lid",sh.getString("lid",""));
                params.put("did",sh.getString("did",""));


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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(view==s2)
        {





        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


//        Toast.makeText(this, "hiiiiiiii", Toast.LENGTH_SHORT).show();
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/hod_view_timetable/";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js= jsonObj.getJSONArray("timetabl");
                                day=new String[js.length()];
                                hour=new String[js.length()];
                                SUBJECT_ID=new String[js.length()];
                                name=new String[js.length()];
                                semester=new String[js.length()];


                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    day[i]=u.getString("day");
                                    hour[i]=u.getString("hour");
                                    SUBJECT_ID[i]=u.getString("SUBJECT_ID");
                                    name[i]=u.getString("name");
                                    semester[i]=u.getString("sem");


                                }

                                timetable.setAdapter(new view_timetable(getApplicationContext(),day,hour,SUBJECT_ID,name,semester));

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
                params.put("program",s1.getSelectedItem().toString());
                params.put("select2",s2.getSelectedItem().toString());

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
}



