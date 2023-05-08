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

public class teacher_view_students extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String [] id,ktu_id,fname,lname,dob,house,place,post,pin,district,email,phno,photo,semester,LOGIN_id;
    ListView lvs;
//    FloatingActionButton b1;


    Spinner s1,s2;
    String sems[]={"1","2","3","4"};


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in=new Intent(getApplicationContext(),teacher_home.class);
        startActivity(in);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view_students);

        lvs = (ListView) findViewById(R.id.teacherviewstudents1);
        s1 = findViewById(R.id.spinner7);
        s2 = findViewById(R.id.spinner8);

        show_dept();
        ArrayAdapter<String> ad=new ArrayAdapter<String>(teacher_view_students.this,android.R.layout.simple_list_item_1,sems);
        s2.setAdapter(ad);

//        s2.setOnItemSelectedListener(this);
        s1.setOnItemSelectedListener(this);


        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long sid) {



                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":8000/myapp/teacher_view_all_student_post/";


                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                          Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                        JSONArray js = jsonObj.getJSONArray("data");
                                        id = new String[js.length()];
                                        ktu_id = new String[js.length()];
                                        fname = new String[js.length()];
                                        lname = new String[js.length()];
                                        dob = new String[js.length()];
                                        house = new String[js.length()];
                                        place = new String[js.length()];
                                        post = new String[js.length()];
                                        pin = new String[js.length()];
                                        district = new String[js.length()];
                                        email = new String[js.length()];
                                        phno = new String[js.length()];
                                        semester = new String[js.length()];
                                        photo = new String[js.length()];


                                        for (int i = 0; i < js.length(); i++) {
                                            JSONObject u = js.getJSONObject(i);
                                            id[i] = u.getString("id");
                                            ktu_id[i] = u.getString("ktuid");
                                            fname[i] = u.getString("fname");
                                            lname[i] = u.getString("lname");
                                            dob[i] = u.getString("dob");
                                            house[i] = u.getString("house");
                                            place[i] = u.getString("place");
                                            post[i] = u.getString("post");
                                            pin[i] = u.getString("pin");
                                            district[i] = u.getString("district");
                                            email[i] = u.getString("email");
                                            phno[i] = u.getString("phno");
                                            semester[i] = u.getString("sem");
                                            photo[i] = u.getString("photo");


                                        }


                                        lvs.setAdapter(new custom_teacher_viewstudents(getApplicationContext(), id, ktu_id, fname, lname, dob, house, place, post, pin, district, email, phno, photo, semester));


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

                        String id = sh.getString("lid", "");
                        params.put("lid", id);
                        params.put("did", sc_id[s1.getSelectedItemPosition()]);
                        params.put("sem", s2.getSelectedItem().toString());

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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":8000/myapp/teacher_view_all_student/";



                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                          Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                        JSONArray js= jsonObj.getJSONArray("data");
                                        id=new String[js.length()];
                                        ktu_id=new String[js.length()];
                                        fname=new String[js.length()];
                                        lname=new String[js.length()];
                                        dob=new String[js.length()];
                                        house=new String[js.length()];
                                        place=new String[js.length()];
                                        post=new String[js.length()];
                                        pin=new String[js.length()];
                                        district=new String[js.length()];
                                        email=new String[js.length()];
                                        phno=new String[js.length()];
                                        semester=new String[js.length()];
                                        photo=new String[js.length()];


                                        for(int i=0;i<js.length();i++)
                                        {
                                            JSONObject u=js.getJSONObject(i);
                                            id[i]=u.getString("id");
                                            ktu_id[i]=u.getString("ktuid");
                                            fname[i]=u.getString("fname");
                                            lname[i]=u.getString("lname");
                                            dob[i]=u.getString("dob");
                                            house[i]=u.getString("house");
                                            place[i]=u.getString("place");
                                            post[i]=u.getString("post");
                                            pin[i]=u.getString("pin");
                                            district[i]=u.getString("district");
                                            email[i]=u.getString("email");
                                            phno[i]=u.getString("phno");
                                            semester[i]=u.getString("sem");
                                            photo[i]=u.getString("photo");


                                        }


                                        lvs.setAdapter(new custom_teacher_viewstudents(getApplicationContext(),id,ktu_id,fname,lname,dob,house,place,post,pin,district,email,phno,photo,semester));


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
        });





        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/teacher_view_all_student/";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                          Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js= jsonObj.getJSONArray("data");
                                id=new String[js.length()];
                                ktu_id=new String[js.length()];
                                fname=new String[js.length()];
                                lname=new String[js.length()];
                                dob=new String[js.length()];
                                house=new String[js.length()];
                                place=new String[js.length()];
                                post=new String[js.length()];
                                pin=new String[js.length()];
                                district=new String[js.length()];
                                email=new String[js.length()];
                                phno=new String[js.length()];
                                semester=new String[js.length()];
                                photo=new String[js.length()];


                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    id[i]=u.getString("id");
                                    ktu_id[i]=u.getString("ktuid");
                                    fname[i]=u.getString("fname");
                                    lname[i]=u.getString("lname");
                                    dob[i]=u.getString("dob");
                                    house[i]=u.getString("house");
                                    place[i]=u.getString("place");
                                    post[i]=u.getString("post");
                                    pin[i]=u.getString("pin");
                                    district[i]=u.getString("district");
                                    email[i]=u.getString("email");
                                    phno[i]=u.getString("phno");
                                    semester[i]=u.getString("sem");
                                    photo[i]=u.getString("photo");


                                }


                                lvs.setAdapter(new custom_teacher_viewstudents(getApplicationContext(),id,ktu_id,fname,lname,dob,house,place,post,pin,district,email,phno,photo,semester));


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

    String [] sc_id,sc_pname;
    public void show_dept()
    {

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String maclis=sh.getString("mac_list","");
        String uid=sh.getString("uid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/show_dept/";



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
                                    sc_pname[i]=u.getString("department");

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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long sid) {



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
