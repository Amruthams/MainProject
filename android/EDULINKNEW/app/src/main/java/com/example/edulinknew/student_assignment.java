package com.example.edulinknew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class student_assignment extends AppCompatActivity implements View.OnClickListener {
    Spinner sem;
    ListView lv1;
    Button b1;


    String [] sems={"1","2","3","4"};

    String [] subject,topic,sdate,aid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_assignment);

        sem=findViewById(R.id.spinner24);
        b1=findViewById(R.id.button14);

        lv1=findViewById(R.id.lv1);

        ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, sems);
        sem.setAdapter(ad);

        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/student_assignment/";


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
                                subject = new String[js.length()];
                                topic = new String[js.length()];
                                sdate = new String[js.length()];
                                aid = new String[js.length()];




                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    subject[i] = u.getString("subject");
                                    topic[i] = u.getString("topic");
                                    sdate[i] = u.getString("date");
                                    aid[i] = u.getString("aid");




                                }

                                lv1.setAdapter(new custom_students_assignment(getApplicationContext(),subject,topic,sdate,aid));


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
                params.put("semester", sem.getSelectedItem().toString());


//                params.put("pid", sc_id[spgm.getSelectedItemPosition()]);

//                params.put("hour", shour.getSelectedItem().toString());

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


