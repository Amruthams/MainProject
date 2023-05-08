package com.example.edulinknew;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class custom_teacher_view_internals extends BaseAdapter  {

    String [] id,fname,mark,date;
    Button b1;



    Context context;
    public custom_teacher_view_internals(Context appcontext,String [] id,String [] fname, String[] mark, String[]date){
        this.context = appcontext;
        this.id = id;
        this.fname = fname;
        this.mark = mark;
        this.date = date;


    }

    @Override
    public int getCount() {
        return fname.length;
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
            gridView=inflator.inflate(R.layout.custom_teacher_view_internal,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView87);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView108);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView128);
        Button b1=(Button)gridView.findViewById(R.id.button30) ;



        b1.setTag(id[i]);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Subject");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                        String hu = sh.getString("ip", "");
                        String url = "http://" + hu + ":8000/myapp/teacher_delete_internal/";


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

                                                context.startActivity(new Intent(context, teacher_view_internal.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                                Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();

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


                                params.put("inteid", v.getTag().toString());


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
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }

        });








        tv1.setText(mark[i]);
        try {
            if (date[i].length() == 0) {
                tv2.setText(date[i]);
            }else{
                tv2.setText(date[i]);
            }
        }catch (Exception e){

        }
        tv3.setText(fname[i]);





        return gridView;
    }
}
