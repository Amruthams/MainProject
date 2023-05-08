package com.example.edulinknew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class teacher_profile extends AppCompatActivity {
    TextView fname,lname,dob,house,place,post,pin,city,district,email,phno,certi;
    ImageView image_;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in=new Intent(getApplicationContext(),teacher_home.class);
        startActivity(in);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);


        fname=(TextView) findViewById(R.id.textView2);
//        lname=(TextView) findViewById(R.id.textView3);
        dob=(TextView) findViewById(R.id.textView4);
        house=(TextView) findViewById(R.id.textView5);
        place=(TextView) findViewById(R.id.textView6);
        post=(TextView) findViewById(R.id.textView7);
        pin=(TextView) findViewById(R.id.textView8);
//        city=(TextView) findViewById(R.id.textView10);
        district=(TextView) findViewById(R.id.textView10);
        email=(TextView) findViewById(R.id.textView11);
        phno=(TextView) findViewById(R.id.textView12);
//        certi=(TextView) findViewById(R.id.textView13);
        image_=findViewById(R.id.imageView2);



        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip= sh.getString("ip", "");
        String url = "http://" + ip + ":8000/myapp/view_profile/";
        String lid = sh.getString("lid", "");
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//                                JSONObject jj = jsonObj.getJSONObject("data");
//                                JSONObject pic = jsonObj.getJSONObject("photo");

                                fname.setText(jsonObj.getString("fname")+jsonObj.getString("lname"));
//                                lname.setText(jsonObj.getString("lname"));
                                dob.setText(jsonObj.getString("dob"));
                                house.setText(jsonObj.getString("house"));
                                place.setText(jsonObj.getString("place"));
                                post.setText(jsonObj.getString("post"));
                                pin.setText(jsonObj.getString("pin"));
                                district.setText(jsonObj.getString("district"));
                                email.setText(jsonObj.getString("email"));
                                phno.setText(jsonObj.getString("phno"));
//                                certi.setText(jsonObj.getString("certificate"));

//                                image.setText(jj.getString("certificate"));





                                String image = jsonObj.getString("photo");
                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String ip = sh.getString("ip", "");
                                String url = "http://" + ip + ":8000" + image;
                               Picasso.with(getApplicationContext()).load(url).into(image_);
//                                Picasso.with(getApplicationContext()).load(url).into(image);//circle

                            } else {
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

            //                value Passing android to python
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                params.put("lid", lid);//passing to python

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
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}