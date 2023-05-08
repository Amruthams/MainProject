package com.example.edulinknew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
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

public class changepassword_teacher extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    EditText e1,e2,e3;
    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword_teacher);
        e1=findViewById(R.id.editTextTextPersonName4);
        e2=findViewById(R.id.editTextTextPersonName5);
        e3=findViewById(R.id.editTextTextPersonName6);
        e3.addTextChangedListener(this);
        b1=findViewById(R.id.button5);

        b1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        final String op=e1.getText().toString();
        final String np=e2.getText().toString();
        final String cp=e3.getText().toString();


        if (op.length()==0) {
            e1.setError("Missing");
        }
        else if (np.length()==0){
            e2.setError("Missing");
        }
        else if (cp.length()==0){
            e3.setError("Missing");
        }
        else{



            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":8000/myapp/changepassword/";



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

                                    Toast.makeText(getApplicationContext(), "changed successfully", Toast.LENGTH_LONG).show();
                                    Intent i =new Intent(getApplicationContext(),login.class);
                                    startActivity(i);

                                }


                                // }
                                else {
//                                    Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                    e1.setError("old password does not match");
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
                    params.put("op",op);
                    params.put("np",np);


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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(!e3.getText().toString().equalsIgnoreCase(e2.getText().toString()))
        {
            e3.setError("Passwords Missmatch");
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
