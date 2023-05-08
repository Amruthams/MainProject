package com.example.edulinknew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
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

public class tutor_chat_students extends AppCompatActivity {
    String [] id,ktu_id,fname,lname,dob,house,place,post,pin,district,email,phno,photo,semester,LOGIN_id;
    ListView lv1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_chat_students);

        lv1=findViewById(R.id.listview);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/tutor_viewstudents/";



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


                                lv1.setAdapter(new custom_tutor_chat_students(getApplicationContext(),id,ktu_id,fname,lname,dob,house,place,post,pin,district,email,phno,photo,semester));


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

}
