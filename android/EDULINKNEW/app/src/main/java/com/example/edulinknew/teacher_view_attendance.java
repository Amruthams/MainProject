package com.example.edulinknew;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class teacher_view_attendance extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {


    Spinner spgm,ssem,shour;
    ListView lv1;

    EditText sdate;
    String[] id,fname,lname,photo,statusat;
    String[] attendance;


    String [] sems={"1","2","3","4"};
    String []hour=new String[]{"1","2","3","4","5"};

    final Calendar myCalendar= Calendar.getInstance();
    Button b1;


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent in=new Intent(getApplicationContext(),attendance_home.class);
//        startActivity(in);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view_attendance);

        spgm=findViewById(R.id.spinner20);
        ssem=findViewById(R.id.spinner21);
        shour=findViewById(R.id.spinner23);
        sdate=findViewById(R.id.spinner22);

        lv1=findViewById(R.id.listview);

        b1=findViewById(R.id.button12);
        b1.setOnClickListener(this);


        ArrayAdapter<String> ah = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, hour);
        shour.setAdapter(ah);
//        shour.setOnItemSelectedListener(this);


        ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, sems);
        ssem.setAdapter(ad);

        show_courses();



        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };




        ssem.setOnItemSelectedListener(this);
        sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dod= new DatePickerDialog(teacher_view_attendance.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
                Calendar c = Calendar.getInstance();
                c.set(1996, 0, 1);
                dod.getDatePicker().setMinDate(c.getTimeInMillis());
                Calendar c1 = Calendar.getInstance();
                c1.set(2030, 0, 1);
                dod.getDatePicker().setMaxDate(c1.getTimeInMillis());
                dod.show();
            }
        });



        ssem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long sid) {


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

                                ArrayAdapter<String> ad2=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,sc_pname);
                                spgm.setAdapter(ad2);

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




    }




    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        sdate.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {


        SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/teacher_view_attendance/";


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
                                photo = new String[js.length()];
                                statusat = new String[js.length()];



                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    id[i] = u.getString("id");
                                    fname[i] = u.getString("fname")+" "+u.getString("lname");
                                    lname[i] = u.getString("lname");
                                    photo[i]=u.getString("photo");
                                    statusat[i]=u.getString("status");



                                }

                                lv1.setAdapter(new custom_teacher_view_attendance(getApplicationContext(),fname,lname,photo,statusat));


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
                params.put("date", sdate.getText().toString());
                params.put("pid", sc_id[spgm.getSelectedItemPosition()]);
                params.put("semester", ssem.getSelectedItem().toString());
                params.put("hour", shour.getSelectedItem().toString());

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
}