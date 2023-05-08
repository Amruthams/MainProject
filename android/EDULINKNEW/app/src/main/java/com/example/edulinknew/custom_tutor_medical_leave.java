package com.example.edulinknew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class custom_tutor_medical_leave extends BaseAdapter  {

    String [] id,name,from_date,to_date,reason,status,date,certificate;

    Button accept,reject;




    private Context context;

    public custom_tutor_medical_leave(Context appcontext, String[] id,String[] name,  String[] date, String[]  from_date, String[] to_date, String[] reason, String[] status,String [] certificate){
        this.context = appcontext;
        this.id =id ;
        this.date =date ;
        this.name =name ;
        this.from_date =from_date ;
        this.to_date=to_date;
        this.reason =reason ;
        this.status = status ;
        this.certificate = certificate ;




    }

    @Override
    public int getCount() {
        return id.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.custom_tutor_medical_leave,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView121);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView122);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView115);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView116);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView118);
        TextView tv6=(TextView)gridView.findViewById(R.id.textView120);
        Button b1=(Button) gridView.findViewById(R.id.button20);
        Button b2=(Button) gridView.findViewById(R.id.button21);
        Button b3=(Button) gridView.findViewById(R.id.button24);



        b3.setTag(certificate[i]);
        if(status[i].equalsIgnoreCase("approved")){
            b1.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);
            b3.setVisibility(View.VISIBLE);
            b3.setEnabled(!certificate[i].equalsIgnoreCase(""));
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                    String hu = sh.getString("ip", "");
                    String url = "http://" + hu + ":8000"+v.getTag().toString();
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });
        }
        else if(status[i].equalsIgnoreCase("rejected")){
            b1.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);
            b3.setVisibility(View.INVISIBLE);
        }
        else{
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);
            b3.setVisibility(View.INVISIBLE);
        }


        tv1.setText(date[i]);
        tv2.setText(name[i]);
        tv3.setText(from_date[i]);
        tv4.setText(to_date[i]);
        tv5.setText(reason[i]);
        tv6.setText(status[i]);
        b1.setTag(id[i]);
        b2.setTag(id[i]);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":8000/myapp/tutor_approve_leave/";


                RequestQueue requestQueue = Volley.newRequestQueue(context);
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(context, response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                        context.startActivity(new Intent(context, tutor_medical_leave.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                        Toast.makeText(context, "Application Approved", Toast.LENGTH_LONG).show();

                                    }


                                    // }
                                    else {
                                        Toast.makeText(context, "Not found", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("leaveid", v.getTag().toString());


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
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":8000/myapp/tutor_reject_leave/";


                RequestQueue requestQueue = Volley.newRequestQueue(context);
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(context, response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                        context.startActivity(new Intent(context, tutor_medical_leave.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                        Toast.makeText(context, "Application Rejected", Toast.LENGTH_LONG).show();

                                    }


                                    // }
                                    else {
                                        Toast.makeText(context, "Not found", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("leaveid", v.getTag().toString());


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
        });




        return gridView;
    }
}
