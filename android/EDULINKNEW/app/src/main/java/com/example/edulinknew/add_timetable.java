package com.example.edulinknew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class add_timetable extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner scourse1, ssem2, shour3, sday4, ssubject5;
    Button b1;

    String[] day = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    String[] hr = {"1", "2", "3", "4", "5"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timetable);

        scourse1=findViewById(R.id.spinner11);

        ssem2 = findViewById(R.id.spinner10);
        sday4 = findViewById(R.id.spinner12);
        shour3 = findViewById(R.id.spinner13);
        ssubject5 = findViewById(R.id.spinner14);

        b1 = findViewById(R.id.button11);
        show_courses();
        show_sem();
        show_subject();
        ArrayAdapter<String> ad1 = new ArrayAdapter<>(add_timetable.this, android.R.layout.simple_list_item_1, day);
        sday4.setAdapter(ad1);

        ArrayAdapter<String> ad2 = new ArrayAdapter<>(add_timetable.this, android.R.layout.simple_list_item_1, hr);
        shour3.setAdapter(ad2);


        b1.setOnClickListener(this);

        ssem2.setOnItemSelectedListener(this);
        scourse1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                show_subject();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }


    String [] sc_id,sc_pname;
    public void show_courses()
    {

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String maclis=sh.getString("mac_list","");
        String uid=sh.getString("uid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/show_courses/";



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

                                ArrayAdapter<String> ad=new ArrayAdapter<>(add_timetable.this,android.R.layout.simple_list_item_1,sc_pname);
                                scourse1.setAdapter(ad);

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


    int sem_count = 4;
    String[] sem_id, s_sem;

    public void show_sem() {
        sem_id = new String[sem_count];
        for (int i = 1; i <= sem_count; i++) {
            sem_id[i - 1] = i + "";
        }

        ArrayAdapter<String> ad = new ArrayAdapter<>(add_timetable.this, android.R.layout.simple_list_item_1, sem_id);
        ssem2.setAdapter(ad);


    }


    String[] sub_id, sub_name;

    public void show_subject() {
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String maclis = sh.getString("mac_list", "");
        String uid = sh.getString("uid", "");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/show_subject/";


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

                                JSONArray js = jsonObj.getJSONArray("data");
                                sub_id = new String[js.length()];
                                sub_name = new String[js.length()];

                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    sub_id[i] = u.getString("id");
                                    sub_name[i] = u.getString("sub_name");


                                }

                                ArrayAdapter<String> ad = new ArrayAdapter<>(add_timetable.this, android.R.layout.simple_list_item_1, sub_name);
                                ssubject5.setAdapter(ad);

                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
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


                params.put("lid", sh.getString("lid", ""));
                params.put("cid",sc_id[scourse1.getSelectedItemPosition()]);
                params.put("sem",ssem2.getSelectedItem().toString() );

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS = 100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);


    }


    String []name,hour,sem;


    @Override
    public void onClick(View v) {
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/add_timetable/";

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


                                Toast.makeText(add_timetable.this, "Timetable added", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), timetable.class));


                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Already Exists", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
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


                params.put("sem",ssem2.getSelectedItem().toString() );
                params.put("day", sday4.getSelectedItem().toString());
                params.put("hour",shour3.getSelectedItem().toString());
                params.put("name",sub_id[ssubject5.getSelectedItemPosition()]);
                params.put("cid",sc_id[scourse1.getSelectedItemPosition()]);

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS = 100000;

        postRequest.setRetryPolicy(new

                        DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
//

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(view==ssem2)
        {
        show_subject();

        }
        else if(view==scourse1){
            show_subject();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    };


}



