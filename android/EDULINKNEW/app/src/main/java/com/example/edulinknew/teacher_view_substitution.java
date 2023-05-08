package com.example.edulinknew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class teacher_view_substitution extends AppCompatActivity implements View.OnClickListener {

    ListView lv1;
    FloatingActionButton b1;
    EditText sdate;

    String [] pname,semester,dates,hour,name;

    final Calendar myCalendar= Calendar.getInstance();
    Button b2;

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
        setContentView(R.layout.activity_teacher_view_substitution);

        lv1=findViewById(R.id.listview26);
        b1=findViewById(R.id.floatingActionButton6);
        b2=findViewById(R.id.button17);
        sdate=findViewById(R.id.editTextTextPersonName19);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);



        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };


        sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dod= new DatePickerDialog(teacher_view_substitution.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
                Calendar c = Calendar.getInstance();
                c.set(1996, 0, 1);
                dod.getDatePicker().setMinDate(c.getTimeInMillis());
                Calendar c1 = Calendar.getInstance();
                c1.set(2030, 0, 1);
                dod.getDatePicker().setMaxDate(c1.getTimeInMillis());
                dod.show();
            }
        });





        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/teacher_view_substitution/";



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
                                pname=new String[js.length()];
                                semester=new String[js.length()];
                                dates=new String[js.length()];
                                hour=new String[js.length()];
                                name=new String[js.length()];
//
//
                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    pname[i]=u.getString("pname");
                                    semester[i]=u.getString("semester");
                                    dates[i]=u.getString("date");
                                    hour[i]=u.getString("hour");
                                    name[i]=u.getString("name");


                                }


                                lv1.setAdapter(new custom_teacher_view_substitution(getApplicationContext(),pname,semester,dates,hour,name));


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
        if (v == b1) {
            startActivity(new Intent(getApplicationContext(), teacher_add_substitution.class));
        }
        else {


            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":8000/myapp/teacher_view_substitution_search/";



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
                                    pname=new String[js.length()];
                                    semester=new String[js.length()];
                                    dates=new String[js.length()];
                                    hour=new String[js.length()];
                                    name=new String[js.length()];
//
//
                                    for(int i=0;i<js.length();i++)
                                    {
                                        JSONObject u=js.getJSONObject(i);
                                        pname[i]=u.getString("pname");
                                        semester[i]=u.getString("semester");
                                        dates[i]=u.getString("date");
                                        hour[i]=u.getString("hour");
                                        name[i]=u.getString("name");


                                    }


                                    lv1.setAdapter(new custom_teacher_view_substitution(getApplicationContext(),pname,semester,dates,hour,name));


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
                    params.put("date", sdate.getText().toString());


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


    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        sdate.setText(dateFormat.format(myCalendar.getTime()));
    }
}