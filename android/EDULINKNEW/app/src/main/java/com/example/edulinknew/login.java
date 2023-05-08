package com.example.edulinknew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText eduser, edpass;

    Button bt_log;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        eduser = (EditText) findViewById(R.id.editTextTextPersonName2);
        edpass = (EditText) findViewById(R.id.editTextTextPassword1);

        bt_log = (Button) findViewById(R.id.button2);


        bt_log.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String username = eduser.getText().toString();
        final String password= edpass.getText().toString();


        if (username.length()==0){
            eduser.setError("Missing");
        }
        else if (password.length()==0){
            edpass.setError("Missing");
        }
        else{


            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String hu = sh.getString("ip", "");

            String url = "http://" + hu + ":8000/myapp/and_login/";

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
                                    SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor ed=sh.edit();
                                    ed.putString("lid",jsonObj.getString("lid"));
//                                    ed.putString("lid",jsonObj.getString("status"));
                                    ed.commit();

                                    if(jsonObj.getString("type").equalsIgnoreCase("hod")) {

                                        ed.putString("sid",jsonObj.getString("sid"));
                                        ed.putString("did",jsonObj.getString("did"));
                                        ed.commit();
                                        Intent it = new Intent(getApplicationContext(), HOD_home.class);
                                        startActivity(it);
                                    }
                                    else if(jsonObj.getString("type").equalsIgnoreCase("tutor")) {
                                        ed.putString("sid",jsonObj.getString("sid"));
                                        ed.putString("did",jsonObj.getString("did"));
                                        ed.commit();

                                        Intent it = new Intent(getApplicationContext(), Hometutuor.class);
                                        startActivity(it);
                                    }
                                    else if(jsonObj.getString("type").equalsIgnoreCase("teacher")) {
                                        ed.putString("sid",jsonObj.getString("sid"));
                                        ed.putString("did",jsonObj.getString("did"));
                                        ed.commit();

                                        Intent it = new Intent(getApplicationContext(), teacher_home.class);
                                        startActivity(it);
                                    }
                                    else if(jsonObj.getString("type").equalsIgnoreCase("student")) {
                                        ed.putString("sid",jsonObj.getString("did"));
                                        ed.commit();
                                        Intent it = new Intent(getApplicationContext(), student_home.class);
                                        startActivity(it);
                                    }


                                    else{
                                        Toast.makeText(getApplicationContext(), "Not user +++++++ check login page", Toast.LENGTH_LONG).show();

                                        Intent it = new Intent(getApplicationContext(), login.class);
                                        startActivity(it);
                                    }
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


                    params.put("username",username);
                    params.put("password",password);

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

}