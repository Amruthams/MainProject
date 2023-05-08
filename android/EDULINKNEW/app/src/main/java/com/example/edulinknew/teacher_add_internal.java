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
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class teacher_add_internal extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner pname, semester, subject;
//    Button b1;
    ListView sname;

    String[] sems = {"1", "2", "3", "4"};
    final Calendar myCalendar = Calendar.getInstance();
    SharedPreferences sh;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add_internal);


        pname = findViewById(R.id.spinner31);
        semester = findViewById(R.id.spinner32);
        subject = findViewById(R.id.spinner33);
        sname = findViewById(R.id.spinner34);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        b1 = findViewById(R.id.button18);

        show_courses();
//        show_subject();


        pname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                show_subject();
                show_students();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("subject", sub_id[subject.getSelectedItemPosition()]);
                ed.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                String hu = sh.getString("ip", "");
//                String url = "http://" + hu + ":8000/myapp/teacher_add_internal/";
//
//                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//
//                                // response
//                                try {
//                                    JSONObject jsonObj = new JSONObject(response);
//                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//
//
//                                        Toast.makeText(teacher_add_internal.this, "Added", Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(getApplicationContext(), teacher_view_internal.class));
//
//
//                                    }
//
//
//                                    // }
//                                    else {
//                                        Toast.makeText(getApplicationContext(), "Already Exists", Toast.LENGTH_LONG).show();
//                                    }
//
//                                } catch (Exception e) {
//                                    Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                // error
//                                Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                ) {
//                    @Override
//                    protected Map<String, String> getParams() {
//                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                        Map<String, String> params = new HashMap<String, String>();
//
//
//                        params.put("sid",id[sname.getSelectedItemPosition()]);
//                          params.put("sub_id",sub_id[subject.getSelectedItemPosition()]);
//                        params.put("mark",sh.getString("mark",""));
//
////                       params.put("mark",marks.getSelectedItem().toString());
////                        params.put("subid", id[ssub.getSelectedItemPosition()]);
////                      params.put("mark",mark.getString("sid",""));
//
//
//                        return params;
//                    }
//                };
//
//                int MY_SOCKET_TIMEOUT_MS = 100000;
//
//                postRequest.setRetryPolicy(new
//
//                        DefaultRetryPolicy(
//                        MY_SOCKET_TIMEOUT_MS,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                requestQueue.add(postRequest);
//
//            }
//        });
        ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, sems);
        semester.setAdapter(ad);

    }



    String[] sc_id, sc_pname;

    public void show_courses() {

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String maclis = sh.getString("mac_list", "");
        String uid = sh.getString("uid", "");
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

                                JSONArray js = jsonObj.getJSONArray("data");
                                sc_id = new String[js.length()];
                                sc_pname = new String[js.length()];

                                for (int i = 0; i < js.length(); i++) {

                                    JSONObject u = js.getJSONObject(i);
                                    sc_id[i] = u.getString("id");
                                    sc_pname[i] = u.getString("pname");

                                }

                                ArrayAdapter<String> ad2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, sc_pname);
                                pname.setAdapter(ad2);

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
                params.put("did", sh.getString("did", ""));


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

    String [] id,fname,lname;

    public void show_students() {

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String maclis = sh.getString("mac_list", "");
        String uid = sh.getString("uid", "");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/show_students/";


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
                                fname = new String[js.length()];
                                lname = new String[js.length()];

                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    id[i] = u.getString("id");
                                    fname[i] = u.getString("fname")+" "+ u.getString("lname");
                                    lname[i] = u.getString("lname");

                                }

//                                ArrayAdapter<String> ad2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, fname);
//                                sname.setAdapter(ad2);

                                sname.setAdapter(new custom_teacher_add_internal(getApplicationContext(),fname,id));



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


                params.put("pid", sc_id[pname.getSelectedItemPosition()]);
                params.put("semester", semester.getSelectedItem().toString());


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
//                          Toast.makeText(getApplicationContext(), pname.getSelectedItemPosition()+"", Toast.LENGTH_LONG).show();

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

//                                    Toast.makeText(teacher_add_internal.this, ""+u.getString("id"), Toast.LENGTH_SHORT).show();


                                }


                                ArrayAdapter<String> ad = new ArrayAdapter<>(teacher_add_internal.this, android.R.layout.simple_list_item_1, sub_name);
                                subject.setAdapter(ad);






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
                params.put("cid", sc_id[pname.getSelectedItemPosition()]);
                params.put("sem", semester.getSelectedItem().toString());

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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}